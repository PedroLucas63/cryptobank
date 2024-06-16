package bank.service;

import java.util.Map;

import bank.entity.Currency;
import bank.entity.FiatCurrency;

public class MoneyService {
   public static Boolean transaction(Currency currency, Double amount) {
      if (currency instanceof FiatCurrency) {
         if (validate(currency, amount)) {
            BalancesService.updateFiatBalance(currency, amount);
         }
      }

      return false;
   }

   protected static Boolean validate(Currency currency, Double amount) {
      if (amount < 0) {
         Map<Currency, Double> balances = BalancesService.getFiatBalances();
         return balances.getOrDefault(currency, 0d) > Math.abs(amount);
      } else {
         return true;
      }
   }

}
