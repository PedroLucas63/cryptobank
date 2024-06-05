package bank.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CryptoAccount extends Account {
   private List<Transaction> pendingTransactions = new ArrayList<>();

   public void updatePendencies() {
      for (Transaction tx : pendingTransactions) {
         if (tx.getTimestamp().isBefore(LocalDateTime.now())) {
            if (tx.getAmount() > 0) {
               credit(tx.getCurrency(), tx.getAmount());
            }

            pendingTransactions.remove(tx);
         }
      }
   }

   public List<Transaction> getPendingTransactions() {
      return pendingTransactions;
   }

   public void addPendingTransaction(Transaction transaction) {
      pendingTransactions.add(transaction);
   }

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
