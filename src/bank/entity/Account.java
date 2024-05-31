package bank.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Account extends Entity {
   Map<Currency, Double> balances = new HashMap<>();
   List<Transaction> transactions = new ArrayList<>();
   List<Transaction> pendingTransactions = new ArrayList<>();

   public boolean debit(Currency currency, Double amount) {
      if (amount < 0) {
         return false;
      }

      Double newAmount = balances.getOrDefault(currency, 0.d) - amount;

      if (newAmount >= 0) {
         balances.put(currency, newAmount);
         transactions.add(new Transaction(currency, -amount));

         return true;
      }

      return false;
   }

   public boolean credit(Currency currency, Double amount) {
      if (amount < 0) {
         return false;
      }

      Double newAmount = balances.getOrDefault(currency, 0.d) + amount;

      balances.put(currency, newAmount);
      transactions.add(new Transaction(currency, amount));

      return true;
   }

   public void updatePendencies() {
      for (Transaction tx : pendingTransactions) {
         if (tx.getTimestamp().isBefore(LocalDateTime.now())) {
            if (tx.getAmount() > 0) {
               credit(tx.getCurrency(), tx.getAmount());
            }
         }
      }

      pendingTransactions.clear();
   }

   public Map<Currency, Double> getBalances() {
      return balances;
   }

   public List<Transaction> getTransactions() {
      return transactions;
   }

   public List<Transaction> getPendingTransactions() {
      return pendingTransactions;
   }

   public void addPendingTransaction(Transaction transaction) {
      pendingTransactions.add(transaction);
   }
}
