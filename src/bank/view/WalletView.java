package bank.view;

import bank.entity.Account;
import bank.entity.CurrentAccount;
import bank.service.AccountService;
import bank.service.BalancesService;
import bank.utils.InputValidator;

/// TODO: Visualizar números das contas.
public class WalletView extends AbstractView {
   enum State {
      BEGIN, MENU, VIEW_ALL, VIEW_FIAT, VIEW_CRYPTO, END
   }

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
         state = State.VIEW_ALL;
      } else if (entry == 2) {
         state = State.VIEW_FIAT;
      } else if (entry == 3) {
         state = State.VIEW_CRYPTO;
      }
   }

   private void menu() {
      title();

      System.out.println();
      for (Account account : AccountService.getAccounts()) {
         if (account instanceof CurrentAccount) {
            System.out.print("Conta corrente - ");
         } else {
            System.out.print("Conta cripto - ");
         }

         System.out.println(account.getId());
      }

      System.out.println("\n1 - Todas");
      System.out.println("2 - Fiduciárias");
      System.out.println("3 - Cripto");
      System.out.println("0 - Voltar");

      System.out.print("\nSelecione uma opção: ");
   }

   private void viewAll() {
      System.out.println("\nTodas as suas moedas:");

      BalancesService.getAllBalances().forEach((currency, amount) -> {
         System.out.println(currency.getName() + ": " + currency.getSymbol()
               + amount + ", $" + currency.getValue() * amount);
      });

      System.out.println();
   }

   private void viewFiats() {
      System.out.println("\nTodas as suas moedas fiduciárias:");

      BalancesService.getFiatBalances().forEach((currency, amount) -> {
         System.out.println(currency.getName() + ": " + currency.getSymbol()
               + amount + ", $" + currency.getValue() * amount);
      });

      System.out.println();

   }

   private void viewCryptos() {
      System.out.println("\nTodas as suas moedas cripto:");

      BalancesService.getCryptoBalances().forEach((currency, amount) -> {
         System.out.println(currency.getName() + ": " + currency.getSymbol()
               + amount + ", $" + currency.getValue() * amount);
      });

      System.out.println();
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
      case VIEW_ALL:
         viewAll();
         break;
      case VIEW_FIAT:
         viewFiats();
         break;
      case VIEW_CRYPTO:
         viewCryptos();
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
