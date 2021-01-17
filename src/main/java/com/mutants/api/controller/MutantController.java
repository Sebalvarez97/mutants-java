package com.mutants.api.controller;

import com.mutants.api.dto.RequestIsMutant;
import com.mutants.api.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/mutant")
public class MutantController {

  @Autowired private MutantService service;

  @GetMapping("/test")
  public Mono<String> testMutantController() {
    return Mono.just("Mutant test endpoint working");
  }

  @PostMapping
  public Mono<ServerResponse> isMutant(@Valid @RequestBody RequestIsMutant requestIsMutant) {
    return service
        .isMutant(requestIsMutant.getDna())
        .flatMap(
            aBoolean -> {
              if (aBoolean) {
                return ServerResponse.ok().build();
              }
              return ServerResponse.status(403).build();
            });
  }
}
