package bank.view;

import java.util.List;
import java.util.Map;

import bank.entity.CryptoCurrency;
import bank.entity.Currency;
import bank.service.BalancesService;
import bank.service.CurrencyService;
import bank.utils.InputValidator;

public class BuyAndSellView extends AbstractView {
   enum State {
      BEGIN, ENTRY_SELL, ENTRY_BUY, ENTRY_AMOUNT, END
   };

   private State state = State.BEGIN;
   private Integer sellEntry;
   private Map.Entry<Currency, Double> sellOption;
   private Integer buyEntry;
   private Currency buyOption;
   private Double amountEntry;

   private void getSellEntry() {
      sellEntry = InputValidator.getInteger();

      Map<Currency, Double> balances = BalancesService.getAllBalances();

      if (sellEntry == null || sellEntry < 1 || sellEntry > balances.size()) {
         warning = "A moeda informada não está disponível para venda.";
      } else {
         Integer i = 0;

         for (Map.Entry<Currency, Double> entry : balances.entrySet()) {
            if (++i == sellEntry) {
               sellOption = entry;
               break;
            }
         }
      }
   }

   private void getBuyEntry() {
      buyEntry = InputValidator.getInteger();

      List<Currency> currencies = CurrencyService
            .getAllDifferent(sellOption.getKey());

      if (buyEntry == null || buyEntry < 1 || buyEntry > currencies.size()) {
         warning = "A moeda informada não está disponível para compra.";
      } else {
         Integer i = 0;

         for (Currency currency : currencies) {
            if (++i == buyEntry) {
               buyOption = currency;
               break;
            }
         }
      }
   }

   private void getAmount() {
      amountEntry = InputValidator.getDouble();

      Double amountMax = sellOption.getKey().getValue() * sellOption.getValue()
            / buyOption.getValue();

      if (amountEntry == null || amountEntry <= 0 || amountEntry > amountMax) {
         warning = "Valor para compra não disponível.";
      } else {
         if (buyOption instanceof CryptoCurrency) {
            CryptoCurrency aux = ((CryptoCurrency) buyOption);
            Double supplyAvailable = aux.getSupplyMaximum()
                  - aux.getSupplyInUse();

            if (supplyAvailable < amountEntry) {
               warning = "Não há moedas suficientes para compra.";
               return;
            }
         }

         Double totalCost = amountEntry * buyOption.getValue()
               / sellOption.getKey().getValue();

         BalancesService.updateBalance(sellOption.getKey(), -totalCost);
         BalancesService.updateBalance(buyOption, amountEntry);
      }
   }

   private void viewSellOptions() {
      System.out.println("\nMoedas disponíveis para venda: ");

      Map<Currency, Double> balances = BalancesService.getAllBalances();

      Integer i = 0;
      for (Map.Entry<Currency, Double> entry : balances.entrySet()) {
         Currency currency = entry.getKey();
         Double amount = entry.getValue();

         System.out.println(
               ++i + ". " + currency.getName() + " - " + currency.getSymbol()
                     + amount + "($ " + currency.getValue() * +")");
      }

      System.out.print("\nEscolha sua moeda de venda: ");
   }

   private void viewBuyOptions() {
      System.out.println("\nMoedas disponíveis para compra: ");

      List<Currency> currencies = CurrencyService
            .getAllDifferent(sellOption.getKey());

      Integer i = 0;
      for (Currency currency : currencies) {
         System.out.print(++i + ". " + currency.getName() + " ("
               + sellOption.getKey().getSymbol()
               + currency.getValue() / sellOption.getKey().getValue() + ")");

         if (currency instanceof CryptoCurrency) {
            CryptoCurrency aux = ((CryptoCurrency) currency);
            Double supplyAvailable = aux.getSupplyMaximum()
                  - aux.getSupplyInUse();

            System.out.print(" - Fornecimento disponível: " + supplyAvailable);
         }

         System.out.println();
      }

      System.out.print("\nEscolha sua moeda de venda: ");
   }

   private void viewAmountOptions() {
      System.out.print("\nDigite a quantidade de " + buyOption.getName()
            + " que deseja comprar: " + buyOption.getSymbol());
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case ENTRY_SELL:
         getSellEntry();
         break;
      case ENTRY_BUY:
         getBuyEntry();
         break;
      case ENTRY_AMOUNT:
         getAmount();
         break;
      default:
         break;
      }
   }

   @Override
   public void update() {
      if (warning != null) {
         state = State.END;
         return;
      }

      switch (state) {
      case BEGIN:
         state = State.ENTRY_SELL;
         break;
      case ENTRY_SELL:
         state = State.ENTRY_BUY;
         break;
      case ENTRY_BUY:
         state = State.ENTRY_AMOUNT;
         break;
      case ENTRY_AMOUNT:
         state = State.END;
         break;
      case END:
         state = State.BEGIN;
         break;
      default:
         break;
      }
   }

   @Override
   public void view() {
      switch (state) {
      case ENTRY_SELL:
         viewSellOptions();
         break;
      case ENTRY_BUY:
         viewBuyOptions();
         break;
      case ENTRY_AMOUNT:
         viewAmountOptions();
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
