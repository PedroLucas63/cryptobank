package bank.view;

import java.util.Map;

import bank.entity.Currency;
import bank.service.AccountService;
import bank.service.BalancesService;
import bank.service.PIXService;
import bank.utils.InputValidator;

public class FiatTransferView extends AbstractView {
   enum State {
      BEGIN, MENU, ENTRY_DOCUMENT, ENTRY_CURRENCY, ENTRY_AMOUNT, EXECUTE_PIX,
      END,
   };

   private State state = State.BEGIN;
   private Integer entryOption;
   private String entryDocument;
   private Integer currencyEntry;
   private Double amountEntry;
   private Currency currency;
   private Double maxAmount;

   private void getEntryOption() {
      entryOption = InputValidator.getInteger();

      if (entryOption == null) {
         warning = "Opção inválida!";
      }
   }

   private void getDocumentKey() {
      entryDocument = InputValidator.getString();

      if (entryDocument == null) {
         warning = "Informe um CPF não nulo!";
      } else if (!AccountService.validUser(entryDocument)) {
         warning = "O usuário não foi encontrado.";
      } 
   }

   private void getCurrencyEntry() {
      currencyEntry = InputValidator.getInteger();

      if (currencyEntry == null) {
         warning = "Opção inválida!";
         return;
      }

      if(currencyEntry > BalancesService.getFiatBalances().size()){
         warning = "Opção inválida!";
         return;
      }

      Integer i = 0;

      for (Map.Entry<Currency, Double> entry : BalancesService.getFiatBalances()
            .entrySet()) {
         if (++i == currencyEntry) {
            currency = entry.getKey();
            maxAmount = entry.getValue();
            break;
         }
      }
   }

   private void getAmountEntry() {
      amountEntry = InputValidator.getDouble();

      if (amountEntry == null || amountEntry <= 0 || amountEntry > maxAmount) {
         warning = "Valor inválido!";
      }
   }

   private void executePix() {
      if (!PIXService.transfer(entryDocument, currency, amountEntry)) {
         warning = "Transferência não concluída, verifique a chave!";
      } else{
         System.out.println("Transferência concluída com êxito!");
      }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 1) {
         state = State.ENTRY_DOCUMENT;
      } else if (entryOption == 0) {
         state = State.END;
      } else {
         warning = "Opção inválida!";
      }
   }

   private void menu() {
      title();

      System.out.println("1. PIX");
      System.out.println("0. Voltar");
      System.out.print("\nSelecione uma opção: ");
   }

   private void availableCurrencies() {
      Integer i = 0;

      for (Map.Entry<Currency, Double> entry : BalancesService.getFiatBalances()
            .entrySet()) {
         Currency currency = entry.getKey();
         Double amount = entry.getValue();

         System.out.println(++i + ". " + currency.getName() + ", disponível: "
               + currency.getSymbol() + " " + amount);
      }

      System.out.print("\nEscolha sua moeda: ");
   }

   @Override
   public void process() {
      warning = null;

      switch (state) {
      case MENU:
         getEntryOption();
         break;
      case ENTRY_DOCUMENT:
         getDocumentKey();
         break;
      case ENTRY_CURRENCY:
         getCurrencyEntry();
         break;
      case ENTRY_AMOUNT:
         getAmountEntry();
         break;
      case EXECUTE_PIX:
         executePix();
         break;
      default:
         break;
      }
   }

   @Override
   public void update() {
      if (warning != null) {
         state = State.MENU;
         return;
      }

      switch (state) {
      case END:
         state = State.BEGIN;
         break;
      case MENU:
         validateEntry();
         break;
      case ENTRY_DOCUMENT:
         state = State.ENTRY_CURRENCY;
         break;
      case ENTRY_CURRENCY:
         state = State.ENTRY_AMOUNT;
         break;
      case ENTRY_AMOUNT:
         state = State.EXECUTE_PIX;
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
      case ENTRY_DOCUMENT:
         System.out.print("\nDigite a chave em documento (CPF/CNPJ): ");
         break;
      case ENTRY_CURRENCY:
         availableCurrencies();
         break;
      case ENTRY_AMOUNT:
         System.out.print("\nDigite o valor da transferência (máx: " + maxAmount
               + "): " + currency.getSymbol());
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
