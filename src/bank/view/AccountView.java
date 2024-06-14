package bank.view;

import bank.utils.InputValidator;

public class AccountView extends AbstractView {
   enum State {
      BEGIN, MENU, DEPOSIT, WITHDRAWN, CLOSE_ACCOUNT, END
   };

   State state = State.BEGIN;

   Integer entry;

   private void getEntry() {
      entry = InputValidator.getInteger();

      if (entry == null) {
         warning = "Opção inválida!";
      }
   }

   private void validateEntry() {
      if (entry == null) {
         return;
      } else if (entry == 0) {
         state = State.END;
      } else if (entry == 1) {
         state = State.DEPOSIT;
      } else if (entry == 2) {
         state = State.WITHDRAWN;
      }
   }

   private void menu() {
      title();

      System.out.println("1 - Depositar");
      System.out.println("2 - Sacar");
      System.out.println("3 - Transferir");
      System.out.println("4 - Compras e vendas");
      System.out.println("5 - Histórico");
      System.out.println("0 - Voltar");

      System.out.print("\nSelecione uma opção: ");
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case MENU:
         getEntry();
         break;
      default:
         break;
      }
   }

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

   @Override
   public Boolean exit() {
      return state == State.END;
   }
}