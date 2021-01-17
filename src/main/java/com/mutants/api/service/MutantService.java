package com.mutants.api.service;

import reactor.core.publisher.Mono;

public interface MutantService {
  Mono<Boolean> isMutant(String[] dna);
}
