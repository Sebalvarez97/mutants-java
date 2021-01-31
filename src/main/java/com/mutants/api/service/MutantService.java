package com.mutants.api.service;

import com.mutants.api.dto.Stats;
import reactor.core.publisher.Mono;

public interface MutantService {
  Mono<Boolean> isMutant(String[] dna);

  Mono<Stats> getStats();
}
