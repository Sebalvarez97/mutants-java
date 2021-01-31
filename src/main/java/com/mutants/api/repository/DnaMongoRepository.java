package com.mutants.api.repository;

import com.mutants.api.domain.mongo.DnaMongo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DnaMongoRepository extends ReactiveCrudRepository<DnaMongo, String> {

  Mono<DnaMongo> findByDnaHash(String hash);

  Flux<DnaMongo> findAllByMutantIs(boolean mutant);
}
