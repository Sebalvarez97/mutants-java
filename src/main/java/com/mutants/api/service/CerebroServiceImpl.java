package com.mutants.api.service;

import com.mutants.api.util.MatrixUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CerebroServiceImpl implements CerebroService {

  @Override
  public Mono<Boolean> isMutant(byte[][] dna) {
    return Mono.fromCallable(
        () -> {
          byte[][] and = MatrixUtil.transpose(dna);
          int ms = 0;
          for (int ri = 0; ri < dna.length; ri++) {
            if (ms > 1) {
              break;
            }
            byte[] row1 = dna[ri];
            byte[] row2 = and[ri];
            for (int ci = 0; ci < row1.length; ci++) {
              int result1 = checkNext(ci, row1[ci], row1);
              if (result1 >= 3) {
                ms++;
              }
              if (ms > 1) {
                break;
              }
              int result2 = checkNext(ci, row2[ci], row2);
              if (result2 >= 3) {
                ms++;
              }
              if (ms > 1) {
                break;
              }
              int result3 = checkDiagonalUpRight(ri, ci, row1[ci], dna);
              if (result3 >= 3) {
                ms++;
              }
              if (ms > 1) {
                break;
              }
              int result4 = checkDiagonalDownLeft(ri, ci, row1[ci], dna);
              if (result4 >= 3) {
                ms++;
              }
            }
          }
          return ms > 1;
        });
  }

  private int checkNext(int x, byte v, byte[] row) {
    int y = x + 1;
    if (y >= row.length) {
      return 0;
    }
    if (v == row[y]) {
      int cn = checkNext(y, row[y], row);
      if (cn <= 3) {
        return cn + 1;
      }
    }
    return 0;
  }

  private int checkDiagonalUpRight(int x, int y, byte v, byte[][] a) {
    int xi = x + 1;
    int yi = y + 1;
    if (xi >= a.length) {
      return 0;
    }
    byte[] row = a[xi];
    if (yi >= row.length) {
      return 0;
    }
    byte vi = row[yi];
    if (v == vi) {
      int cd = checkDiagonalUpRight(xi, yi, vi, a);
      if (cd <= 3) {
        return cd + 1;
      }
    }
    return 0;
  }

  private int checkDiagonalDownLeft(int x, int y, byte v, byte[][] a) {
    int xi = x + 1;
    int yi = y - 1;
    if (xi >= a.length) {
      return 0;
    }
    byte[] row = a[xi];
    if (yi < 0) {
      return 0;
    }
    byte vi = row[yi];
    if (v == vi) {
      int cd = checkDiagonalDownLeft(xi, yi, vi, a);
      if (cd <= 3) {
        return cd + 1;
      }
    }
    return 0;
  }
}
