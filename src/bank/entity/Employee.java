package bank.entity;

public class Employee extends User {
   private Float salary;
   private Role role;

   public Employee(String name, String document, String password, Integer age,
         String email, Float salary, Role role) {
      super(name, document, password, age, email);
      this.salary = salary;
      this.role = role;
   }

   public Float getSalary() {
      return salary;
   }

   public void setSalary(Float salary) {
      this.salary = salary;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }
}
