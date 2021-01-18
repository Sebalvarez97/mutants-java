package com.mutants.api.service;

import com.mutants.api.exception.NotMutantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.mutants.api.util.MatrixUtil.stringNxNMatrixToByteArray;

@Service
public class MutantServiceImpl implements MutantService {

  @Autowired private CerebroService cerebroService;

  @Override
  public Mono<Boolean> isMutant(String[] dna) {
    return Mono.justOrEmpty(cerebroService.isMutant(stringNxNMatrixToByteArray(dna)))
        .flatMap(
            aBoolean -> {
              if (aBoolean == null || !aBoolean) {
                return Mono.error(new NotMutantException());
              }
              return Mono.just(aBoolean);
            });
  }
}
