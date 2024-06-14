package bank.entity;

public class Employee extends User {
   private Float salary;
   private Career career;

   public Employee(String name, String document, String password, Integer age,
         String email, Float salary, Career career) {
      super(name, document, password, age, email);
      this.salary = salary;
      this.career = career;
   }

    public Employee(User user, Career career, Float salary) {
        super(user.getName(), user.getDocument(), user.getPassword(), user.getAge(), user.getEmail());
        this.career = career;
        this.salary = salary;
    }

   public Float getSalary() {
      return salary;
   }

   public void setSalary(Float salary) {
      this.salary = salary;
   }

   public Career getCareer() {
      return career;
   }

   public void setCareer(Career career) {
      this.career = career;
   }
}
