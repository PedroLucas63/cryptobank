package bank.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import bank.entity.Account;
import bank.entity.Transaction;

public class HistoryService {
   private static List<Transaction> getAllWithFilter(
         Predicate<Transaction> filter) {
      List<Transaction> transactions = new ArrayList<>();

      for (Account account : AccountService.getAccounts()) {
         Stream<Transaction> filteredTransactions = account.getTransactions()
               .stream().filter(filter);

         transactions.addAll(filteredTransactions.collect(Collectors.toList()));
      }

      return transactions;
   }

   public static List<Transaction> getAll() {
      return getAllWithFilter(tx -> true);
   }

   public static List<Transaction> getAllWithMinimumDate(
         LocalDateTime datetime) {
      return getAllWithFilter(tx -> tx.getTimestamp().isAfter(datetime));
   }

   public static List<Transaction> getCreditHistory() {
      return getAllWithFilter(tx -> tx.getAmount() > 0);
   }

   public static List<Transaction> getDebitHistory() {
      return getAllWithFilter(tx -> tx.getAmount() < 0);
   }
}
