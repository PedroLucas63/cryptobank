package bank.entity;

public class CryptoCurrency extends Currency {
   private Double originalValue;
   private Integer supplyMaximum;
   private Integer supplyInUse = 0;
   private Double growthRate;

   public CryptoCurrency(String name, String symbol, Double value,
         Integer supplyMaximum) {
      super(name, symbol, value);
      this.originalValue = value;
      this.supplyMaximum = supplyMaximum;
      this.growthRate = Math.pow(Math.cbrt(supplyMaximum.doubleValue()), 2) + 1;
   }

   private void autoUpdateValue() {
      if (this.supplyInUse == 0) {
         this.value = this.originalValue;
      } else if (this.supplyInUse == this.supplyMaximum) {
         this.value = Double.POSITIVE_INFINITY;
      } else {
         this.value = this.originalValue
               * (this.growthRate * ((double) this.supplyInUse
                     / (this.supplyMaximum - this.supplyInUse)));
      }
   }

   public boolean buy(Integer amount) {
      if (supplyInUse + amount <= supplyMaximum) {
         supplyInUse += amount;
         autoUpdateValue();

         return true;
      }

      return false;
   }

   public boolean sell(Integer amount) {
      if (supplyInUse - amount >= 0) {
         supplyInUse -= amount;
         autoUpdateValue();

         return true;
      }

      return false;
   }

   public Integer getSupplyMaximum() {
      return supplyMaximum;
   }

   public void setSupplyMaximum(Integer supplyMaximum) {
      this.supplyMaximum = supplyMaximum;
   }

   public Integer getSupplyInUse() {
      return supplyInUse;
   }

   public void setSupplyInUse(Integer supplyInUse) {
      this.supplyInUse = supplyInUse;
   }

   public Double getOriginalValue() {
      return originalValue;
   }

   public Double getGrowthRate() {
      return growthRate;
   }
}
