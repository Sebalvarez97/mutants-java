package com.mutants.api.dto.validation;

import com.mutants.api.dto.RequestIsMutant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequestIsMutantValidator
    implements ConstraintValidator<ValidRequestIsMutant, RequestIsMutant> {

  private static final char[] VALID_CHARS = {'A', 'T', 'C', 'G'};

  @Override
  public void initialize(ValidRequestIsMutant constraintAnnotation) {}

  @Override
  public boolean isValid(RequestIsMutant request, ConstraintValidatorContext context) {
    if (request == null) {
      return false;
    }
    String[] dna = request.getDna();
    if (dna == null) {
      return false;
    }
    int size = dna.length;
    if (size < 4) {
      return false;
    }
    for (String s : dna) {
      if (s.length() != size) {
        return false;
      }
      for (char c : s.toCharArray()) {
        if (!isValidChar(c)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isValidChar(char c) {
    boolean valid = false;
    for (char vc : VALID_CHARS) {
      if (c == vc) {
        valid = true;
        break;
      }
    }
    return valid;
  }
}
