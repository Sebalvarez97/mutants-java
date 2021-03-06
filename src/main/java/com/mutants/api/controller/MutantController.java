package com.mutants.api.controller;

import com.mutants.api.dto.IsMutantRequest;
import com.mutants.api.dto.Stats;
import com.mutants.api.exception.NotMutantException;
import com.mutants.api.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
  @PreAuthorize("hasRole('MAGNETO')")
  public Mono<Void> isMutant(@Valid @RequestBody IsMutantRequest isMutantRequest) {
    return service.isMutant(isMutantRequest.getDna()).then();
  }

  @GetMapping("/stats")
  @PreAuthorize("hasRole('MAGNETO')")
  public Mono<Stats> getStats() {
    return service.getStats();
  }

  @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not Mutant")
  @ExceptionHandler(NotMutantException.class)
  public void notMutantExceptionHandler() {}
}
