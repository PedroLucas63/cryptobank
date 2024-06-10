package bank.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
   private static Boolean validateStringSize(String str, Integer min,
         Integer max) {
      return str != null && str.length() >= min && str.length() <= max;
   }

   public static Boolean name(String name) {
      final Integer minLength = 3;
      final Integer maxLength = 16;

      return validateStringSize(name, minLength, maxLength);
   }

   public static Boolean document(String document) {
      return DocumentValidator.isValidCPF(document)
            || DocumentValidator.isValidCNPJ(document);
   }

   public static Boolean password(String password) {
      final Integer minLength = 8;
      final Integer maxLength = 24;

      return validateStringSize(password, minLength, maxLength);
   }

   public static Boolean age(Integer age) {
      final Integer minAge = 16;
      final Integer maxAge = 120;

      return age != null && age >= minAge && age <= maxAge;
   }

   public static Boolean email(String email) {
      if (email == null) {
         return false;
      }

      String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

      Pattern emailPattern = Pattern.compile(emailRegex);
      Matcher matcher = emailPattern.matcher(email);

      return matcher.matches();
   }

   public static Boolean isValid(String name, String document, String password,
         Integer age, String email) {
      return name(name) && document(document) && password(password) && age(age)
            && email(email);
   }
}
