package bank.utils;

import java.util.Scanner;
import java.io.Console;

public class InputValidator {
   private static Scanner scanner = new Scanner(System.in);
   private static Console console = System.console();

   public static Integer getInteger() {
      try {
         Integer number = Integer.parseInt(scanner.next());

         scanner.nextLine();

         return number;
      } catch (NumberFormatException e) {
         return null;
      }
   }

   public static Double getDouble() {
      try {
         Double number = Double.parseDouble(scanner.next());

         scanner.nextLine();

         return number;
      } catch (NumberFormatException e) {
         return null;
      }
   }

   public static String getString() {
      String str = scanner.next();
      return str;
   }

   public static String getLine() {
      String line = scanner.nextLine();
      return line;
   }

   public static String getPassword() {
      return String.copyValueOf(console.readPassword());
   }
}
