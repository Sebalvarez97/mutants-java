package com.mutants.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mutants.api.dto.validation.ValidRequestIsMutant;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ValidRequestIsMutant
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class IsMutantRequest implements Serializable {
  private static final long serialVersionUID = -1512717777276216840L;

  private String[] dna;
}
