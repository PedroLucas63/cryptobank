package bank.view;

import bank.entity.Account;
import bank.service.AccountService;
import bank.service.UserService;
import bank.utils.InputValidator;
import bank.utils.UserValidator;

/**
 * Singleton class representing the main view of the bank application. This
 * class implements the View interface and provides methods to process user
 * input and update the view.
 */
public class CreateAccountView implements View {
   enum State {
      BEGIN, MENU, CREATE_CURRENT, CREATE_CRYPTO, END,
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
      }

      switch (entryOption) {
      case 1:
         state = State.CREATE_CURRENT;
         break;
      case 2:
         state = State.CREATE_CRYPTO;
         break;
      case 3:
         state = State.END;
         break;
      default:
         warning = "Opção inválida!";
         break;
      }
   }

   private void createCurrentAccount() {
      AccountService.createCurrentAccount();
      warning = "Conta corrente criada com sucesso!";
   }

   private void createCryptoAccount() {
      AccountService.createCryptoAccount();
      warning = "Conta cripto criada com sucesso!";
   }

   private void menu() {
      System.out.println("=====================================");
      System.out.println("   Cryptobank - O seu banco seguro   ");
      System.out.println("=====================================");
      System.out.println("\n============ CRIAR CONTA ============");

      if (warning != null) {
         System.out.println("\nAviso: " + warning + "\n");
      }

      System.out.println("1. Conta Corrente");
      System.out.println("2. Conta Cripto");
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
      case CREATE_CURRENT:
         createCurrentAccount();
         break;
      case CREATE_CRYPTO:
         createCryptoAccount();
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
