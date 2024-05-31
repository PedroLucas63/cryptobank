package bank.entity;

public class CurrentAccount extends Account {
   @Override
   public boolean debit(Currency currency, Double amount) {
      if (currency instanceof FiatCurrency) {
         return super.debit(currency, amount);
      }

      return false;
   }

   @Override
   public boolean credit(Currency currency, Double amount) {
      if (currency instanceof FiatCurrency) {
         return super.credit(currency, amount);
      }

      return false;
   }
}
