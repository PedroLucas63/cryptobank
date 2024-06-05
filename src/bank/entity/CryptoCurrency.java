package bank.entity;

public class CryptoCurrency extends Currency {
   private Double originalValue;
   private Integer maxSupply;
   private Integer inUseSupply = 0;
   private Double growthRate;

   public CryptoCurrency(String name, String symbol, Double value,
         Integer maxSupply) {
      super(name, symbol, value);
      this.originalValue = value;
      this.maxSupply = maxSupply;
      this.growthRate = Math.pow(Math.cbrt(maxSupply.doubleValue()), 2) + 1;
   }

   private void updateValue() {
      if (this.inUseSupply == 0) {
         this.value = this.originalValue;
      } else if (this.inUseSupply == this.maxSupply) {
         this.value = Double.POSITIVE_INFINITY;
      } else {
         this.value = this.originalValue
               * (this.growthRate * ((double) this.inUseSupply
                     / (this.maxSupply - this.inUseSupply)));
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

   public Double getOriginalValue() {
      return originalValue;
   }

   public Double getGrowthRate() {
      return growthRate;
   }
}
