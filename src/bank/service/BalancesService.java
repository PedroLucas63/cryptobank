package bank.service;

import java.util.HashMap;
import java.util.Map;

import bank.entity.Account;
import bank.entity.CryptoAccount;
import bank.entity.CryptoCurrency;
import bank.entity.Currency;
import bank.entity.CurrentAccount;
import bank.entity.FiatCurrency;

public class BalancesService {
   public static Map<Currency, Double> getAllBalances() {
      Map<Currency, Double> balances = new HashMap<Currency, Double>();

      for (Account account : AccountService.getAccounts()) {
         balances.putAll(account.getBalances());
      }

      return balances;
   }

   public static Map<Currency, Double> getFiatBalances() {
      for (Account account : AccountService.getAccounts()) {
         if (account instanceof CurrentAccount) {
            return account.getBalances();
         }
      }

      return null;
   }

   public static Map<Currency, Double> getCryptoBalances() {
      for (Account account : AccountService.getAccounts()) {
         if (account instanceof CryptoAccount) {
            return account.getBalances();
         }
      }

      return null;
   }

   public static void updateFiatBalance(Currency currency, Double amount) {
      for (Account account : AccountService.getAccounts()) {
         if (account instanceof CurrentAccount) {
            if (amount > 0) {
               account.credit(currency, amount);
            } else {
               account.debit(currency, Math.abs(amount));
            }

            account.addTransaction(currency, amount);

            UserService.updateLogged();
            break;
         }
      }
   }

   public static void updateCryptoBalance(Currency currency, Double amount) {
      for (Account account : AccountService.getAccounts()) {
         if (account instanceof CryptoAccount) {
            CryptoCurrency cryptoCurrency = (CryptoCurrency) currency;

            if (amount > 0) {
               cryptoCurrency.buy(amount);
               account.credit(currency, amount);
            } else {
               cryptoCurrency.sell(amount);
               account.debit(currency, Math.abs(amount));
            }

            account.addTransaction(currency, amount);

            UserService.updateLogged();
            CurrencyService.updateCurrency(cryptoCurrency);
            break;
         }
      }
   }

   public static void updateBalance(Currency currency, Double amount) {
      if (currency instanceof FiatCurrency) {
         updateFiatBalance(currency, amount);
      } else if (currency instanceof CryptoCurrency) {
         updateCryptoBalance(currency, amount);
      }
   }
}
