package bank.view;

import java.util.Scanner;
import bank.service.UserService;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class CreateUserView implements View {
   enum CreateUserViewState {
      BEGIN, TITLE, ENTRY_NAME, ENTRY_DOCUMENT, ENTRY_PASSWORD, ENTRY_AGE,
      ENTRY_EMAIL, END,
   };

   private CreateUserViewState state = CreateUserViewState.BEGIN;
   private Scanner scanner = new Scanner(System.in);
   private UserService userService = new UserService();
   private String name, document, password, email;
   private Integer age;

   private void validateUser() {
      if (userService.create(name, document, password, age, email)) {
         System.out.println("Usuário criado com sucesso!");
      }

      state = CreateUserViewState.END;
   }

   private void title() {
      System.out.println("=====================================");
      System.out.println("   Cryptobank - O seu banco seguro   ");
      System.out.println("=====================================");
      System.out.println("=========== CRIAR USUÁRIO ===========");
   }

   /**
    * Process user input or perform necessary actions. This method is currently
    * not implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void process() {
      switch (state) {
      case ENTRY_NAME:
         name = scanner.next();
         break;
      case ENTRY_DOCUMENT:
         document = scanner.next();
         break;
      case ENTRY_PASSWORD:
         password = scanner.next();
         break;
      case ENTRY_AGE:
         /// TODO: Try Catch
         age = scanner.nextInt();
         break;
      case ENTRY_EMAIL:
         email = scanner.next();
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
         state = CreateUserViewState.TITLE;
         break;
      case TITLE:
         state = CreateUserViewState.ENTRY_NAME;
         break;
      case ENTRY_NAME:
         state = CreateUserViewState.ENTRY_DOCUMENT;
         break;
      case ENTRY_DOCUMENT:
         state = CreateUserViewState.ENTRY_PASSWORD;
         break;
      case ENTRY_PASSWORD:
         state = CreateUserViewState.ENTRY_AGE;
         break;
      case ENTRY_AGE:
         state = CreateUserViewState.ENTRY_EMAIL;
         break;
      case ENTRY_EMAIL:
         validateUser();
         break;
      case END:
         state = CreateUserViewState.BEGIN;
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
      case ENTRY_NAME:
         System.out.print("Nome: ");
         break;
      case ENTRY_DOCUMENT:
         System.out.print("Documento: ");
         break;
      case ENTRY_PASSWORD:
         System.out.print("Senha: ");
         break;
      case ENTRY_AGE:
         System.out.print("Idade: ");
         break;
      case ENTRY_EMAIL:
         System.out.print("Email: ");
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
      return state == CreateUserViewState.END;
   }
}
