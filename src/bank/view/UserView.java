package bank.view;

import java.util.List;
import bank.entity.Account;
import bank.entity.CurrentAccount;
import bank.service.AccountService;
import bank.service.AuthService;
import bank.service.UserService;
import bank.utils.InputValidator;

public class UserView extends ViewAbstract {
   enum State {
      BEGIN, MENU, ACCOUNT, CREATE_ACCOUNT, EMPLOYEE, LOGOUT, END,
   };

   private State state = State.BEGIN;

   private Integer entryOption;

   private View createAccountView = new CreateAccountView();
   private View createEmployeeView = new EmployeeView();

   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void validateActiveChoice() {
      List<Account> accounts = AccountService.getAccounts();
      Integer i = 0;

      for (Account account : accounts) {
         if (++i == entryOption) {
            AccountService.setActiveAccount(account);
            state = State.ACCOUNT;
         }
      }

      if (UserService.isEmployee() && ++i == entryOption) {
         state = State.EMPLOYEE;
      } else if (++i == entryOption) {
         state = State.CREATE_ACCOUNT;
      }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 0) {
         state = State.LOGOUT;
      } else {
         validateActiveChoice();
      }
   }

   private void printAccounts() {
      List<Account> accounts = AccountService.getAccounts();

      Integer i = 0;

      for (Account account : accounts) {
         System.out.print(++i + ". " + account.getId() + " | ");

         if (account instanceof CurrentAccount) {
            System.out.println("Conta corrente");
         } else {
            System.out.println("Conta cripto");
         }
      }
   }

   private void menu() {
      title();

      printAccounts();

      Integer accountsAmount = AccountService.getAccounts().size();

      if (UserService.isEmployee()) {
         System.out.println(++accountsAmount + ". Funcionário");
      }

      System.out.println("\n" + ++accountsAmount + ". Criar nova conta");

      System.out.println("\n0. Logout");
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
         createEmployeeView.startView();
         break;
      case LOGOUT:
         AuthService.logout();
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
      case LOGOUT:
         state = State.END;
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
         break;
      case CREATE_ACCOUNT:
         createAccountView.startView();
         break;
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
