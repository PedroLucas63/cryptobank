package bank.view;

import bank.service.AccountService;
import bank.service.AuthService;
import bank.service.UserService;
import bank.utils.InputValidator;

public class UserView extends AbstractView {
   enum State {
      BEGIN, MENU, ACCOUNTS_VIEW, OPEN_ACCOUNTS, EMPLOYEE_VIEW, LOGOUT, END,
   };

   private State state = State.BEGIN;

   private Integer entryOption;

   private AbstractView accountView = new AccountView();
   private AbstractView employeeView = new EmployeeView();

   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void openAccounts() {
      AccountService.createCurrentAccount();
      AccountService.createCryptoAccount();

      warning = "Contas criadas com sucesso!";
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 0) {
         state = State.LOGOUT;
      } else if (entryOption == 1) {
         if (AccountService.hasAccounts()) {
            state = State.ACCOUNTS_VIEW;
         } else {
            state = State.OPEN_ACCOUNTS;
         }
      } else if (entryOption == 2 && UserService.isEmployee()) {
         state = State.EMPLOYEE_VIEW;
      } else {
         warning = "Opção inválida!";
      }
   }

   private void menu() {
      title();

      Integer options = 1;

      if (AccountService.hasAccounts()) {
         System.out.println(options++ + ". Acessar conta");
      } else {
         System.out.println(options++ + ". Abrir uma conta");
      }

      if (UserService.isEmployee()) {
         System.out.println(options++ + ". Funcionário");
      }

      System.out.println("0. Logout");
      System.out.print("\nSelecione uma opção: ");
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
      case OPEN_ACCOUNTS:
         openAccounts();
         break;
      case ACCOUNTS_VIEW:
         warning = accountView.getWarning();
         break;
      case EMPLOYEE_VIEW:
         warning = employeeView.getWarning();
         break;
      case LOGOUT:
         AuthService.logout();
         warning = "Saída com sucesso!";
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
      case ACCOUNTS_VIEW:
         accountView.startView();
         break;
      case EMPLOYEE_VIEW:
         employeeView.startView();
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
