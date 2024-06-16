package bank.view;

import bank.utils.InputValidator;

public class AccountView extends AbstractView {
   enum State {
      BEGIN, MENU, WALLET, DEPOSIT, WITHDRAWN, TRANSFER, BUY_AND_SELL, HISTORY,
      CLOSE_ACCOUNT, END
   };

   State state = State.BEGIN;

   Integer entry;

   private AbstractView walletView = new WalletView();
   private AbstractView depositView = new DepositView();
   private AbstractView withdrawnView = new WithdrawnView();
   private AbstractView buyAndSellView = new BuyAndSellView();

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
         state = State.WALLET;
      } else if (entry == 2) {
         state = State.DEPOSIT;
      } else if (entry == 3) {
         state = State.WITHDRAWN;
      } else if (entry == 4) {
         state = State.TRANSFER;
      } else if (entry == 5) {
         state = State.BUY_AND_SELL;
      } else if (entry == 6) {
         state = State.HISTORY;
      }
   }

   private void menu() {
      title();

      System.out.println("1 - Ver carteira");
      System.out.println("2 - Depositar");
      System.out.println("3 - Sacar");
      System.out.println("4 - Transferir");
      System.out.println("5 - Compras e vendas");
      System.out.println("6 - Histórico");
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
      case DEPOSIT:
         warning = depositView.getWarning();
         break;
      case WITHDRAWN:
         warning = withdrawnView.getWarning();
         break;
      case BUY_AND_SELL:
         warning = buyAndSellView.getWarning();
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
      case WALLET:
         walletView.startView();
         break;
      case DEPOSIT:
         depositView.startView();
         break;
      case WITHDRAWN:
         withdrawnView.startView();
         break;
      case BUY_AND_SELL:
         buyAndSellView.startView();

      default:
         break;
      }
   }

   @Override
   public Boolean exit() {
      return state == State.END;
   }
}