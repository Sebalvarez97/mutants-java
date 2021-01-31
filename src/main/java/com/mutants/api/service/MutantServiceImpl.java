package com.mutants.api.service;

import com.mutants.api.domain.mongo.DnaMongo;
import com.mutants.api.dto.Stats;
import com.mutants.api.exception.NotMutantException;
import com.mutants.api.repository.DnaMongoRepository;
import com.mutants.api.util.MatrixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.mutants.api.util.MatrixUtil.stringNxNMatrixToByteArray;

@Service
public class MutantServiceImpl implements MutantService {

  @Autowired private CerebroService cerebroService;

  @Autowired private DnaMongoRepository dnaMongoRepository;

  @Override
  public Mono<Boolean> isMutant(String[] dna) {
    return Mono.zip(
            Mono.fromCallable(() -> MatrixUtil.generateHashForMatrix(dna)),
            cerebroService.isMutant(stringNxNMatrixToByteArray(dna)))
        .zipWhen(
            objects -> {
              String hash = objects.getT1();
              return dnaMongoRepository.findByDnaHash(hash).defaultIfEmpty(new DnaMongo());
            })
        .doOnSuccess(
            objects -> {
              DnaMongo dnaMongo = objects.getT2();
              dnaMongo.setDnaHash(objects.getT1().getT1());
              dnaMongo.setMutant(objects.getT1().getT2());
              dnaMongo.setSequences(objects.getT1().getT2() ? 2 : 0);
              dnaMongoRepository.save(dnaMongo).subscribe();
            })
        .flatMap(
            objects -> {
              boolean aBoolean = objects.getT1().getT2();
              if (!aBoolean) {
                return Mono.error(new NotMutantException());
              }
              return Mono.just(true);
            });
  }

  @Override
  public Mono<Stats> getStats() {
    return Mono.zip(
            dnaMongoRepository.findAll().collectList().map(List::size),
            dnaMongoRepository.findAllByMutantIs(true).collectList().map(List::size))
        .map(
            objects -> {
              int m = objects.getT2();
              int h = objects.getT1() - objects.getT2();

              return Stats.builder()
                  .countMutantDna(m)
                  .countHumanDna(h)
                  .ratio(h != 0 ? (double) m / (double) h : 1)
                  .build();
            });
  }
}
