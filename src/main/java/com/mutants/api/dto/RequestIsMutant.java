package com.mutants.api.dto;

import com.mutants.api.dto.validation.ValidRequestIsMutant;
import lombok.Data;

import java.io.Serializable;

@Data
@ValidRequestIsMutant
public class RequestIsMutant implements Serializable {
  private static final long serialVersionUID = -1512717777276216840L;

  private String[] dna;
}
