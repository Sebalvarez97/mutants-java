package com.mutants.api.util;

public class MatrixUtil {

  public static byte[][] transpose(byte[][] a) {
    byte[][] transposed = new byte[a.length][a.length];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        transposed[j][i] = a[i][j];
      }
    }
    return transposed;
  }

  public static byte[][] stringNxNMatrixToByteArray(String[] array) {
    byte[][] bytes = new byte[array.length][array.length];
    for (int i = 0; i < array.length; i++) {
      bytes[i] = array[i].getBytes();
    }
    return bytes;
  }
}
