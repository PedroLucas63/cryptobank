package bank.view;

import bank.entity.Currency;
import bank.service.MoneyService;
import bank.utils.InputValidator;
import bank.utils.UserValidator;

public class DepositView extends AbstractView {
   enum State {
      BEGIN, ENTRY_CURRENCY, ENTRY_AMOUNT, END
   };

   private State state = State.BEGIN;
   private Integer currencyEntry;
   private Double amountEntry;
   private Currency currency;

   private void getCurrencyEntry() {
      currencyEntry = InputValidator.getInteger();

      /// if (currencyEntry == null || currencyEntry < 1 || currencyEntry >
      /// CurrencyService.getFiatCurrencies().length) {
      if (currencyEntry == null || currencyEntry < 1 || currencyEntry > 10) {
         warning = "A moeda informada não existe.";
      } else {
         /// currency = CurrencyService.getFiatCurrencies().get(currencyEntry)
      }
   }

   private void getAmountEntry() {
      amountEntry = InputValidator.getDouble();

      if (amountEntry == null || amountEntry <= 0) {
         warning = "Deposite um valor maior.";
      } else {
         MoneyService.transaction(currency, amountEntry);
      }
   }

   private void currenciesList() {
      /// List<Currency> currencies = CurrencyService.getFiatCurrencies();

      System.out.println("Moedas:\n");
      /// for (Integer i = 0; i < currencies.size(); i++) {
      /// System.out.println(i + 1 + ". " + currencies.get(i).getName());
      /// }
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
         System.out.print("Digite o valor: " + currency.getSymbol() + " ");
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
