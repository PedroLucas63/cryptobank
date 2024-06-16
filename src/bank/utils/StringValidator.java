package bank.utils;

public class StringValidator {
   public static Boolean size(String str, Integer min, Integer max) {
      return str != null && str.length() >= min && str.length() <= max;
   }
}
