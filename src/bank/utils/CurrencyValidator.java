package bank.utils;

public class CurrencyValidator {
   public static Boolean name(String name) {
      final Integer minLength = 2;
      final Integer maxLength = 16;

      return StringValidator.size(name, minLength, maxLength);
   }

   public static Boolean symbol(String symbol) {
      final Integer minLength = 1;
      final Integer maxLength = 4;

      return StringValidator.size(symbol, minLength, maxLength);
   }

   public static Boolean value(Double value) {
      final Double minValue = 0d;

      return value != null && value > minValue;
   }

   public static Boolean supplyMaximum(Double supplyMaximum) {
      final Integer minSupplyMaximum = 0;

      return supplyMaximum != null && supplyMaximum > minSupplyMaximum;
   }

   public static Boolean fiatIsValid(String name, String symbol, Double value) {
      return name(name) && symbol(symbol) && value(value);
   }

   public static Boolean cryptoIsValid(String name, String symbol, Double value,
         Double supplyMaximum) {
      return name(name) && symbol(symbol) && value(value)
            && supplyMaximum(supplyMaximum);
   }
}
