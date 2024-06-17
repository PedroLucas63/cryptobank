package bank.view;

import java.time.LocalDateTime;
import java.util.List;

import bank.entity.Currency;
import bank.entity.Transaction;
import bank.service.HistoryService;
import bank.utils.InputValidator;

/// BUG: Duplicação nas transações.
/// BUG: Erro na entrada do DateTime.
public class HistoryView extends AbstractView {
   enum State {
      BEGIN, MENU, ALL, ENTRY_DATETIME, ALL_WITH_TIME, CREDIT, DEBIT, END
   };

   State state = State.BEGIN;
   private Integer entryOption;
   private LocalDateTime startDate;

   void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   void getDateTime() {
      startDate = InputValidator.getLocalDateTime();

      if (startDate == null) {
         warning = "Data incorreta!";
      }
   }

   void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 0) {
         state = State.END;
      } else if (entryOption == 1) {
         state = State.ALL;
      } else if (entryOption == 2) {
         state = State.ENTRY_DATETIME;
      } else if (entryOption == 3) {
         state = State.CREDIT;
      } else if (entryOption == 4) {
         state = State.DEBIT;
      }
   }

   void validateDateTime() {
      if (entryOption == null || startDate.isAfter(LocalDateTime.now())) {
         state = State.MENU;
      } else {
         state = State.ALL_WITH_TIME;
      }
   }

   private void viewHistory(List<Transaction> history) {
      System.out.println();

      for (Transaction tx : history) {
         Currency currency = tx.getCurrency();
         Double total = tx.getAmount() * tx.getActualValue();
         Double totalToday = tx.getAmount() * currency.getValue();

         System.out.println(currency.getName() + ": " + currency.getSymbol()
               + total + "(hoje: " + currency.getSymbol() + totalToday + ")");
      }

      System.out.println();
   }

   public void menu() {
      title();

      System.out.println("1 - Todas");
      System.out.println("2 - Desde de ...");
      System.out.println("3 - Credito");
      System.out.println("4 - Débito");
      System.out.println("0 - Voltar");

      System.out.print("\nSelecione uma opção: ");
   }

   public void allHistory() {
      System.out.println("Todas as transações: ");
      viewHistory(HistoryService.getAll());
   }

   public void withMinimumDate() {
      System.out.println("Transações até " + startDate + ": ");
      viewHistory(HistoryService.getAllWithMinimumDate(startDate));
   }

   public void viewCredit() {
      System.out.println("Transações de crédito: ");
      viewHistory(HistoryService.getCreditHistory());
   }

   public void viewDeposit() {
      System.out.println("Transações de crédito: ");
      viewHistory(HistoryService.getDebitHistory());
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case MENU:
         getEntryOption();
         break;
      case ALL:
         allHistory();
         break;
      case ENTRY_DATETIME:
         getDateTime();
         break;
      case ALL_WITH_TIME:
         withMinimumDate();
         break;
      case CREDIT:
         viewCredit();
         break;
      case DEBIT:
         viewDeposit();
         break;
      default:
         break;
      }
   }

   @Override
   public void update() {
      switch (state) {
      case END:
         state = State.BEGIN;
         break;
      case MENU:
         validateEntry();
         break;
      case ENTRY_DATETIME:
         validateDateTime();
         break;
      default:
         state = State.MENU;
         break;
      }
   }

   @Override
   public void view() {
      switch (state) {
      case MENU:
         menu();
         break;
      case ENTRY_DATETIME:
         System.out.print("\nDigite a data mínima: ");
         break;
      default:
         break;
      }
   }

   @Override
   public Boolean exit() {
      return state == State.END;
   }
}
