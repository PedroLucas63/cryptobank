package bank.entity;

public class CryptoAccount extends Account {
   @Override
   public boolean debit(Currency currency, Double amount) {
      if (currency instanceof CryptoCurrency) {
         return super.debit(currency, amount);
      }

      return false;
   }

   @Override
   public boolean credit(Currency currency, Double amount) {
      if (currency instanceof CryptoCurrency) {
         return super.credit(currency, amount);
      }

      return false;
   }
}
