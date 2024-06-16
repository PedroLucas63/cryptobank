package bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bank.dao.CurrencyDAO;
import bank.entity.CryptoCurrency;
import bank.entity.Currency;
import bank.entity.FiatCurrency;
import bank.exception.DAOException;
import bank.utils.CurrencyValidator;

public class CurrencyService {
   private static CurrencyDAO currencyDAO = new CurrencyDAO();

   public static Boolean addFiatCurrency(String name, String symbol,
         Double value) {
      try {
         if (!CurrencyValidator.fiatIsValid(name, symbol, value)) {
            return false;
         }

         FiatCurrency newCurrency = new FiatCurrency(name, symbol, value);
         currencyDAO.save(newCurrency);
         return true;
      } catch (

      Exception e) {
         return false;
      }
   }

   public static Boolean addCryptoCurrency(String name, String symbol,
         Double value, Double supplyMaximum) {
      try {
         if (!CurrencyValidator.cryptoIsValid(name, symbol, value,
               supplyMaximum)) {
            return false;
         }
         CryptoCurrency newCurrency = new CryptoCurrency(name, symbol, value,
               supplyMaximum);
         currencyDAO.save(newCurrency);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public static List<Currency> getAll() {
      try {
         return currencyDAO.findAll();
      } catch (DAOException e) {
         return new ArrayList<Currency>();
      }
   }

   public static List<Currency> getAllDifferent(Currency different) {
      try {
         return currencyDAO.findAll(currency -> currency != different);
      } catch (DAOException e) {
         return new ArrayList<Currency>();
      }
   }

   public static List<Currency> getFiatCurrencies() {
      try {
         return currencyDAO
               .findAll(currency -> currency instanceof FiatCurrency);
      } catch (DAOException e) {
         return new ArrayList<Currency>();
      }
   }

   public static List<Currency> getCryptoCurrencies() {
      try {
         return currencyDAO
               .findAll(currency -> currency instanceof CryptoCurrency);
      } catch (DAOException e) {
         return new ArrayList<Currency>();
      }
   }

   public static void updateCurrency(Currency currency) {
      try {
         currencyDAO.update(currency.getId(), currency);
      } catch (DAOException e) {
      }
   }

   public static Boolean searchCurrency(String name) {
      try {
         Optional<Currency> searchedCurrency = currencyDAO
               .findById((long) name.hashCode());
         if (!searchedCurrency.isPresent()
               || !(searchedCurrency.get() instanceof Currency)) {
            return false;
         } else {
            return true;
         }
      } catch (Exception e) {
         return false;
      }
   }

   public static Boolean removeCurrency(String name) {
      try {
         Optional<Currency> searchedCurrency = currencyDAO
               .findById((long) name.hashCode());
         if (!searchedCurrency.isPresent()) {
            return false;
         } else if (searchedCurrency.get() instanceof CryptoCurrency) {
            CryptoCurrency temp = (CryptoCurrency) searchedCurrency.get();
            if (temp.getSupplyInUse() != 0) {
               System.out.print(
                     "Não é possível remover criptomoedas com fornecimento em uso. ");
               return false;
            }
         }
         currencyDAO.delete((long) name.hashCode());
         return true;

      } catch (Exception e) {
         return false;
      }
   }

}
