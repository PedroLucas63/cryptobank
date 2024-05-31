package bank.entity;

public class CryptoCurrency extends Currency {
   private Double originalValue;
   private Integer maxSupply;
   private Integer inUseSupply;

   public CryptoCurrency(String name, Double value, Integer maxSupply,
         Integer inUseSupply) {
      super(name, value);
      this.originalValue = value;
      this.maxSupply = maxSupply;
      this.inUseSupply = inUseSupply;
   }

   private void updateValue() {
      if (inUseSupply == 0) {
         this.value = this.originalValue;
      } else {
         double multiply = 1 / ((maxSupply - inUseSupply) / maxSupply) * 4;
         this.value = this.originalValue * multiply;
      }
   }

   public boolean buy(Integer amount) {
      if (inUseSupply + amount <= maxSupply) {
         inUseSupply += amount;
         updateValue();

         return true;
      }

      return false;
   }

   public boolean sell(Integer amount) {
      if (inUseSupply - amount >= 0) {
         inUseSupply -= amount;
         updateValue();

         return true;
      }

      return false;
   }

   public Integer getMaxSupply() {
      return maxSupply;
   }

   public void setMaxSupply(Integer maxSupply) {
      this.maxSupply = maxSupply;
   }

   public Integer getInUseSupply() {
      return inUseSupply;
   }

   public void setInUseSupply(Integer inUseSupply) {
      this.inUseSupply = inUseSupply;
   }

}
