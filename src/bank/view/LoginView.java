package bank.view;

import java.util.Scanner;
import bank.service.AuthService;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class LoginView implements View {
   enum LoginViewState {
      BEGIN, TITLE, ENTRY_DOCUMENT, ENTRY_PASSWORD, LOGGED, END,
   };

   private LoginViewState state = LoginViewState.BEGIN;
   private Scanner scanner = new Scanner(System.in);
   private AuthService authService = new AuthService();
   private String document, password;
   private String loginWarning;

   private void title() {
      System.out.println("=====================================");
      System.out.println("   Cryptobank - O seu banco seguro   ");
      System.out.println("=====================================");
      System.out.println("\n=============== LOGIN ===============");
   }

   private void validateLogin() {
      if (authService.login(document, password)) {
         state = LoginViewState.LOGGED;
      } else {
         loginWarning = "O usuário não foi autenticado! Revise os dados.";
         state = LoginViewState.END;
      }
   }

   public String getWarning() {
      return loginWarning;
   }

   /**
    * Process user input or perform necessary actions. This method is currently
    * not implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void process() {
      loginWarning = null;

      switch (state) {
      case ENTRY_DOCUMENT:
         document = scanner.next();
         break;
      case ENTRY_PASSWORD:
         password = scanner.next();
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
         state = LoginViewState.TITLE;
         break;
      case TITLE:
         state = LoginViewState.ENTRY_DOCUMENT;
         break;
      case ENTRY_DOCUMENT:
         state = LoginViewState.ENTRY_PASSWORD;
         break;
      case ENTRY_PASSWORD:
         validateLogin();
         break;
      case END:
         state = LoginViewState.BEGIN;
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
         System.out.println("Login efetuado com sucesso!");
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
      return state == LoginViewState.END;
   }
}
