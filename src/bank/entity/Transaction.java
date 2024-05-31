package bank.entity;

import java.time.LocalDateTime;

public class Transaction extends Entity {
   private Currency currency;
   private Double actualValue;
   private Double amount;
   private LocalDateTime timestamp = LocalDateTime.now();

   public Transaction(Currency currency, Double amount) {
      this.currency = currency;
      this.actualValue = currency.getValue();
      this.amount = amount;
   }

   public Currency getCurrency() {
      return currency;
   }

   public void setCurrency(Currency currency) {
      this.currency = currency;
   }

   public Double getActualValue() {
      return actualValue;
   }

   public void setActualValue(Double actualValue) {
      this.actualValue = actualValue;
   }

   public Double getAmount() {
      return amount;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }

   public LocalDateTime getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
   }
}
