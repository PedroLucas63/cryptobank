package bank.view;

import bank.utils.InputValidator;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class MainView extends ViewAbstract {
   enum State {
      BEGIN, MENU, LOGIN, CREATE_USER, END,
   };

   private State state = State.BEGIN;

   private Integer entryOption;

   private LoginView loginView = new LoginView();
   private CreateUserView createUserView = new CreateUserView();

   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      }

      switch (entryOption) {
      case 1:
         state = State.LOGIN;
         break;
      case 2:
         state = State.CREATE_USER;
         break;
      case 3:
         state = State.END;
         break;
      default:
         warning = "Opção inválida!";
         break;
      }
   }

   private void menu() {
      title();

      System.out.println("1. Entrar");
      System.out.println("2. Criar conta");
      System.out.println("3. Sair");
      System.out.print("Selecione uma opção: ");
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
      case LOGIN:
         warning = loginView.getWarning();
         break;
      case CREATE_USER:
         warning = createUserView.getWarning();
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
      case LOGIN:
         loginView.startView();
         break;
      case CREATE_USER:
         createUserView.startView();
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
