package com.mararok.epiccore.misc;

public class StringUtils {

  public static boolean isNumber(String value) {
    return value.matches("-?\\d+(\\.\\d+)?");
  }

  public static boolean isChar(String value) {
    return value != null && value.length() == 1;
  }

  public static boolean isBoolean(String value) {
    return value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"));
  }

  public static String join(Object[] pieces, String separator) {
    String output = "";
    int lastIndex = pieces.length - 1;
    for (int i = 0; i < lastIndex; ++i) {
      output += pieces[i] + separator;
    }

    return output + pieces[lastIndex];
  }
}
