package com.mutants.api.service;

import reactor.core.publisher.Mono;

public interface CerebroService {
  Mono<Boolean> isMutant(byte[][] dna);
}
