package bank.utils;

public class EmployeeValidator {
   public static Boolean salary(Float salary) {
      final Float minSalary = 0f;
      return salary != null && salary >= minSalary;
   }

   public static Boolean isValid(String name, String document, String password,
         Integer age, String email, Float salary) {
      return UserValidator.isValid(name, document, password, age, email)
            && salary(salary);
   }
}
