package bank.view;

import bank.utils.InputValidator;

public class TransferView extends AbstractView {
   enum State {
      BEGIN, MENU, FIAT_TRANSFER, CRYPTO_TRANSFER, END,
   };

   private State state = State.BEGIN;
   private Integer entryOption;

   private AbstractView fiatTransfer = new FiatTransferView();
   private AbstractView cryptoTransfer = new CryptoTransferView();

   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 1) {
         state = State.FIAT_TRANSFER;
      } else if (entryOption == 2) {
         state = State.CRYPTO_TRANSFER;
      } else if (entryOption == 0) {
         state = State.END;
      } else {
         warning = "Opção inválida!";
      }
   }

   private void menu() {
      title();

      System.out.println("1. Transferência de fiduciária");
      System.out.println("2. Transferência de criptomoedas");
      System.out.println("0. Voltar");
      System.out.print("\nSelecione uma opção: ");
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case MENU:
         getEntryOption();
         break;
      case FIAT_TRANSFER:
         warning = fiatTransfer.getWarning();
         break;
      case CRYPTO_TRANSFER:
         warning = cryptoTransfer.getWarning();
         break;
      default:
         break;
      }
   }

   @Override
   public void update() {
      switch (state) {
      case END:
         state = State.BEGIN;
         break;
      case MENU:
         validateEntry();
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
      case FIAT_TRANSFER:
         fiatTransfer.startView();
         break;
      case CRYPTO_TRANSFER:
         cryptoTransfer.startView();
         break;
      default:
         break;
      }
   }

   @Override
   public Boolean exit() {
      return state == State.END;
   };
}
