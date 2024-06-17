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

   public static String getCareer() {
      String str = scanner.nextLine();
      return str.toUpperCase();
   }

   public static String getLine() {
      String line = scanner.nextLine();
      return line;
   }

   public static Float getSalary() {
      try {
         Float salary = Float.parseFloat(scanner.next());

         scanner.nextLine();
         
         return salary;
      } catch (NumberFormatException e) {
         return null;
      }
   }

   public static String getPassword() {
      return String.copyValueOf(console.readPassword());
   }

   public static Boolean getYesOrNo(){
      try {
         String yesOrNo = scanner.next();
         if(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n")){
            System.out.print("Não foi possível validar a entrada (digite Y/N para responder).");
            return null;
         } else if (yesOrNo.equalsIgnoreCase("y")){
            return true;
         } else { 
            return false;
         }
      } catch (Exception e) {
         System.out.print(e);
         return null;
      }
   }
}
