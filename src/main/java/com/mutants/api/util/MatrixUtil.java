package com.mutants.api.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MatrixUtil {

  protected static final String HASH_ALGORITHM = "SHA-256";

  /**
   * Transpose a NxN byte matrix.
   * @param a the byte matrix
   * @return a byte matrix transposed
   */
  public static byte[][] transpose(byte[][] a) {
    byte[][] transposed = new byte[a.length][a.length];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        transposed[j][i] = a[i][j];
      }
    }
    return transposed;
  }

  /**
   * Transform a string NxN matrix to a byte matrix (byte[][]).
   * @param array the string array
   * @return the byte matrix
   */
  public static byte[][] stringNxNMatrixToByteArray(String[] array) {
    byte[][] bytes = new byte[array.length][array.length];
    for (int i = 0; i < array.length; i++) {
      bytes[i] = array[i].getBytes();
    }
    return bytes;
  }

  /**
   * Generate hash for matrix. Warning: MessageDigest is not thread-safe.
   *
   * @param array string array matrix
   * @return the hash
   * @throws NoSuchAlgorithmException when the algorithm is not found
   */
  public static String generateHashForMatrix(String[] array) throws NoSuchAlgorithmException {
    StringBuilder toHash = new StringBuilder();
    for (String s : array) {
      toHash.append(s);
    }
    MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
    byte[] hash = digest.digest(toHash.toString().getBytes(StandardCharsets.UTF_8));
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
