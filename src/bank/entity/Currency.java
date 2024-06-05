package bank.entity;

public abstract class Currency extends Entity {
   protected String name;
   protected String symbol;
   protected Double value;

   public Currency(String name, String symbol, Double value) {
      this.name = name;
      this.symbol = symbol;
      this.value = value;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSymbol() {
      return symbol;
   }

   public void setSymbol(String symbol) {
      this.symbol = symbol;
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
