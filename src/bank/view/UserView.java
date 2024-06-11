package bank.view;

import java.util.List;
import bank.entity.Account;
import bank.service.AccountService;
import bank.service.UserService;
import bank.utils.InputValidator;

public class UserView implements View {
   enum State {
      BEGIN, MENU, ACCOUNT, CREATE_ACCOUNT, EMPLOYEE, END,
   };

   private State state = State.BEGIN;

   private Integer entryOption;
   private String warning;

   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 0) {
         state = State.END;
      }

      List<Account> accounts = AccountService.getAccounts();
      Integer i = 0;

      for (Account account : accounts) {
         if (++i == entryOption) {
            /// AccountService.setActiveAccount(account)
            state = State.ACCOUNT;
         }
      }

      if (++i == entryOption) {
         state = State.CREATE_ACCOUNT;
      } else if (++i == entryOption && UserService.isEmployee()) {
         state = State.EMPLOYEE;
      }
   }

   private void menu() {
      System.out.println("=====================================");
      System.out.println("   Cryptobank - O seu banco seguro   ");
      System.out.println("=====================================");
      System.out.println("\n=========== ACESSAR CONTA ===========");

      if (warning != null) {
         System.out.println("\nAviso: " + warning + "\n");
      }

      List<Account> accounts = AccountService.getAccounts();

      Integer i = 0;

      for (Account account : accounts) {
         System.out.println(
               ++i + ". " + account.getId() + " | " + account.getClass());
      }

      System.out.println(++i + ". Criar conta");

      if (UserService.isEmployee()) {
         System.out.println(++i + ". Funcionário");
      }

      System.out.println("\n0. Voltar");
      System.out.print("Selecione uma conta: ");
   }

   /**
    * Process user input or perform necessary actions. This method is currently
    * not implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void process() {
      warning = null;

      switch (state) {
      case MENU:
         getEntryOption();
         break;
      case ACCOUNT:
      case CREATE_ACCOUNT:
      case EMPLOYEE:
         break;
      default:
         break;
      }
   }

   /**
    * Update the view based on any changes. This method is currently not
    * implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void update() {
      switch (state) {
      case MENU:
         validateEntry();
         break;
      case END:
         state = State.BEGIN;
         break;
      default:
         state = State.MENU;
         break;
      }
   }

   /**
    * Render the view and display data to the user. This method is currently not
    * implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void view() {
      switch (state) {
      case MENU:
         menu();
         break;
      case ACCOUNT:
      case CREATE_ACCOUNT:
      case EMPLOYEE:
         break;
      default:
         break;
      }
   }

   /**
    * Check if the view should exit. This method is currently not implemented
    * and always returns false.
    * 
    * @return always returns false.
    */
   @Override
   public Boolean exit() {
      return state == State.END;
   }
}
