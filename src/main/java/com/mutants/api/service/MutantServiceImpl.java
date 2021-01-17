package com.mutants.api.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MutantServiceImpl implements MutantService {
  @Override
  public Mono<Boolean> isMutant(String[] dna) {
    return Mono.justOrEmpty(true);
  }
}
