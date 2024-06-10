package bank.view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class MainView implements View {
   enum MainViewState {
      BEGIN, MENU, LOGIN, CREATE_USER, END,
   };

   private MainViewState state = MainViewState.BEGIN;

   private Scanner scanner = new Scanner(System.in);

   private Integer entry;
   private String entryWarning;

   private LoginView loginView = new LoginView();
   private CreateUserView createUserView = new CreateUserView();

   private void getEntry() {
      try {
         entry = scanner.nextInt();
      } catch (InputMismatchException e) {
         entryWarning = e.getMessage();
      }
   }

   private void validateEntry() {
      switch (entry) {
      case 1:
         state = MainViewState.LOGIN;
         break;
      case 2:
         state = MainViewState.CREATE_USER;
         break;
      case 3:
         state = MainViewState.END;
         break;
      default:
         break;
      }
   }

   private void menu() {
      System.out.println("====================================");
      System.out.println("         Cryptobank - O seu banco seguro");
      System.out.println("====================================");

      if (entryWarning != null) {
         System.out.println("\nAviso: " + entryWarning + "\n");
      }

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
      entryWarning = null;

      switch (state) {
      case MENU:
         getEntry();
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
         state = MainViewState.MENU;
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
      return state == MainViewState.END;
   }
}
