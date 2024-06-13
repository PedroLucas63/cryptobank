package bank.view;

import bank.service.AuthService;
import bank.utils.InputValidator;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class LoginView extends ViewAbstract {
   enum State {
      BEGIN, TITLE, ENTRY_DOCUMENT, ENTRY_PASSWORD, LOGGED, END,
   };

   private State state = State.BEGIN;

   private String userDocument, userPassword;

   private UserView userView = new UserView();

   private void validateLogin() {
      if (AuthService.login(userDocument, userPassword)) {
         state = State.LOGGED;
      } else {
         warning = "O usuário não foi autenticado! Revise os dados.";
         state = State.END;
      }
   }

   /**
    * Process user input or perform necessary actions. This method is currently
    * not implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void process() {
      warning = null;

      switch (state) {
      case ENTRY_DOCUMENT:
         userDocument = InputValidator.getString();
         break;
      case ENTRY_PASSWORD:
         userPassword = InputValidator.getPassword();
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
      case BEGIN:
         state = State.TITLE;
         break;
      case TITLE:
         state = State.ENTRY_DOCUMENT;
         break;
      case ENTRY_DOCUMENT:
         state = State.ENTRY_PASSWORD;
         break;
      case ENTRY_PASSWORD:
         validateLogin();
         break;
      case LOGGED:
         state = State.END;
         break;
      case END:
         state = State.BEGIN;
         break;
      default:
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
      case TITLE:
         title();
         break;
      case ENTRY_DOCUMENT:
         System.out.print("Documento: ");
         break;
      case ENTRY_PASSWORD:
         System.out.print("Senha: ");
         break;
      case LOGGED:
         userView.startView();
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
