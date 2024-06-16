package bank.view;

import java.util.Map;

import bank.entity.Currency;
import bank.service.BalancesService;
import bank.service.MoneyService;
import bank.utils.InputValidator;

public class WithdrawnView extends AbstractView {
   enum State {
      BEGIN, ENTRY_CURRENCY, ENTRY_AMOUNT, END
   };

   private State state = State.BEGIN;
   private Integer currencyEntry;
   private Double amountEntry;
   private Map.Entry<Currency, Double> withdrawnOption;

   private void getCurrencyEntry() {
      currencyEntry = InputValidator.getInteger();

      Map<Currency, Double> balances = BalancesService.getFiatBalances();

      if (currencyEntry == null || currencyEntry < 1
            || currencyEntry > balances.size()) {
         warning = "A moeda informada não existe.";
      } else {
         Integer i = 0;

         for (Map.Entry<Currency, Double> entry : balances.entrySet()) {
            if (++i == currencyEntry) {
               withdrawnOption = entry;
               break;
            }
         }
      }
   }

   private void getAmountEntry() {
      amountEntry = InputValidator.getDouble();

      if (amountEntry == null || amountEntry <= 0
            || amountEntry > withdrawnOption.getValue()) {
         warning = "Saque um valor válido.";
      } else {
         MoneyService.transaction(withdrawnOption.getKey(), -amountEntry);
      }
   }

   private void currenciesList() {
      System.out.println("\nMoedas disponíveis:\n");

      Map<Currency, Double> balances = BalancesService.getFiatBalances();

      Integer i = 0;
      for (Map.Entry<Currency, Double> entry : balances.entrySet()) {
         Currency currency = entry.getKey();
         Double amount = entry.getValue();

         System.out.println(++i + ". " + currency.getName() + " - Disponível: "
               + currency.getSymbol() + amount);
      }

      System.out.print("\nSelecione uma opção: ");
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case ENTRY_CURRENCY:
         getCurrencyEntry();
         break;
      case ENTRY_AMOUNT:
         getAmountEntry();
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
         state = State.ENTRY_CURRENCY;
         break;
      case ENTRY_CURRENCY:
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
      case ENTRY_CURRENCY:
         currenciesList();
         break;
      case ENTRY_AMOUNT:
         System.out.print(
               "Digite o valor: " + withdrawnOption.getKey().getSymbol() + " ");
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
