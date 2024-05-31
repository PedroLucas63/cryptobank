package bank.entity;

public abstract class Currency extends Entity {
   protected String name;
   protected Double value;

   public Currency(String name, Double value) {
      this.name = name;
      this.value = value;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Double getValue() {
      return value;
   }

   public void setValue(Double value) {
      this.value = value;
   }

   @Override
   public int hashCode() {
      return name.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Currency) {
         return ((Currency) obj).getName().equals(name);
      }

      return false;
   }
}
