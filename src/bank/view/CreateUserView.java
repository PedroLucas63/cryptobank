package bank.view;

import bank.service.UserService;
import bank.utils.InputValidator;
import bank.utils.UserValidator;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class CreateUserView implements View {
   enum State {
      BEGIN, TITLE, ENTRY_NAME, ENTRY_DOCUMENT, ENTRY_PASSWORD, ENTRY_AGE,
      ENTRY_EMAIL, END,
   };

   private State state = State.BEGIN;

   private String userName, userDocument, userPassword, userEmail;
   private Integer userAge;

   private String warning;

   private void getUserName() {
      userName = InputValidator.getLine();

      if (!UserValidator.name(userName)) {
         warning = "Nome não validado.";
      }
   }

   private void getUserDocument() {
      userDocument = InputValidator.getString();

      if (!UserValidator.document(userDocument)) {
         warning = "Documento não validado.";
      }
   }

   private void getUserPassword() {
      userPassword = InputValidator.getPassword();

      if (!UserValidator.password(userPassword)) {
         warning = "Senha não validada.";
      }
   }

   private void getUserAge() {
      userAge = InputValidator.getInteger();

      if (userAge == null) {
         warning = "A idade precisa ser um número.";
      } else if (!UserValidator.age(userAge)) {
         warning = "Idade não validada.";
      }
   }

   private void getUserEmail() {
      userEmail = InputValidator.getString();

      if (!UserValidator.email(userEmail)) {
         warning = "Email não validado.";
      }
   }

   private void validateUser() {
      if (UserService.create(userName, userDocument, userPassword, userAge,
            userEmail)) {
         warning = "O usuário criado com sucesso!";
      } else {
         warning = "O usuário não foi criado! Revise os dados.";
      }

      state = State.END;
   }

   private void title() {
      System.out.println("=====================================");
      System.out.println("   Cryptobank - O seu banco seguro   ");
      System.out.println("=====================================");
      System.out.println("\n=========== CRIAR USUÁRIO ===========");
   }

   public String getWarning() {
      return warning;
   }

   /**
    * Process user input or perform necessary actions. This method is currently
    * not implemented and serves as a placeholder for future functionality.
    */
   @Override
   public void process() {
      warning = null;

      switch (state) {
      case ENTRY_NAME:
         getUserName();
         break;
      case ENTRY_DOCUMENT:
         getUserDocument();
         break;
      case ENTRY_PASSWORD:
         getUserPassword();
         break;
      case ENTRY_AGE:
         getUserAge();
         break;
      case ENTRY_EMAIL:
         getUserEmail();
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
      if (warning != null) {
         state = State.END;
         return;
      }

      switch (state) {
      case BEGIN:
         state = State.TITLE;
         break;
      case TITLE:
         state = State.ENTRY_NAME;
         break;
      case ENTRY_NAME:
         state = State.ENTRY_DOCUMENT;
         break;
      case ENTRY_DOCUMENT:
         state = State.ENTRY_PASSWORD;
         break;
      case ENTRY_PASSWORD:
         state = State.ENTRY_AGE;
         break;
      case ENTRY_AGE:
         state = State.ENTRY_EMAIL;
         break;
      case ENTRY_EMAIL:
         validateUser();
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
      return state == State.END;
   }
}
