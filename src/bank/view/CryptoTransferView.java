package bank.view;

import java.util.Map;

import bank.entity.Currency;
import bank.service.BalancesService;
import bank.utils.DocumentValidator;
import bank.utils.InputValidator;

/// TODO: Atualizar dados.
public class CryptoTransferView extends AbstractView {
   enum State {
      BEGIN, MENU, ENTRY_ACCOUNT, ENTRY_CURRENCY, ENTRY_AMOUNT, EXECUTE,
      END,
   };

   private State state = State.BEGIN;
   private Integer entryOption;
   private String accountEntry;
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

   private void getAccountKey() {
      accountEntry = InputValidator.getString();

      if (accountEntry == null) {
         warning = "Informe um CPF não nulo!";
      } else if (!DocumentValidator.isValidCPF(accountEntry)
            && !DocumentValidator.isValidCNPJ(accountEntry)) {
         warning = "CPF inválido!";
      }
   }

   private void getCurrencyEntry() {
      currencyEntry = InputValidator.getInteger();

      if (currencyEntry == null) {
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

   private void executeTransfer() {
      // if (!CryptoTransferService.transfer(documentEntry, currency, amountEntry)) {
      // warning = "Transferência não concluída, verifique a chave!";
      // }
   }

   private void validateEntry() {
      if (entryOption == null) {
         return;
      } else if (entryOption == 1) {
         state = State.ENTRY_ACCOUNT;
      } else if (entryOption == 0) {
         state = State.END;
      } else {
         warning = "Opção inválida!";
      }
   }

   private void menu() {
      title();

      System.out.println("1. Transferência direta");
      System.out.println("0. Voltar");
      System.out.print("\nSelecione uma opção: ");
   }

   private void availableCurrencies() {
      Integer i = 0;

      for (Map.Entry<Currency, Double> entry : BalancesService.getCryptoBalances()
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
      case ENTRY_ACCOUNT:
      getAccountKey();
         break;
      case ENTRY_CURRENCY:
         getCurrencyEntry();
         break;
      case ENTRY_AMOUNT:
         getAmountEntry();
         break;
      case EXECUTE:
         executeTransfer();
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
      case ENTRY_ACCOUNT:
         state = State.ENTRY_CURRENCY;
         break;
      case ENTRY_CURRENCY:
         state = State.ENTRY_AMOUNT;
         break;
      case ENTRY_AMOUNT:
         state = State.EXECUTE;
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
      case ENTRY_ACCOUNT:
         System.out.print("\nDigite o número da conta: ");
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
