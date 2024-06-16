package bank.service;

import java.util.Optional;

import bank.dao.CurrencyDAO;
import bank.entity.CryptoCurrency;
import bank.entity.Currency;
import bank.entity.FiatCurrency;

public class UpdateCurrencyService {
    private static CurrencyDAO currencyDAO = new CurrencyDAO();
    public static Boolean addFiatCurrency(String name, String symbol, Double value){
      try {
         if(symbol.length() > 4 || name.length() > 15 || value < 0){
            return false;
         }
         FiatCurrency newCurrency = new FiatCurrency(name,symbol, value);
         currencyDAO.save(newCurrency);
         return true;
      } catch (Exception e) {
         return false;
      }
    }

    public static Boolean addCryptoCurrency(String name, String symbol, Double value, Integer suplyMaximum){
      try {
         if(symbol.length() > 4 || name.length() > 15 || suplyMaximum < 0 || value < 0){
            return false;
         }
         CryptoCurrency newCurrency = new CryptoCurrency(name,symbol, value, suplyMaximum);
         currencyDAO.save(newCurrency);
         return true;
      } catch (Exception e) {
         return false;
      }
    }

    public static Boolean searchCurrency (String name){
      try {
         Optional<Currency> searchedCurrency = currencyDAO.findById((long) name.hashCode());
         if(!searchedCurrency.isPresent() || !(searchedCurrency.get() instanceof Currency)){
            return false;
         }
         else{
            return true;
         }  
      } catch (Exception e) {
         return false;
      }
    }

    public static Boolean removeCurrency (String name){
        try {
            Optional<Currency> searchedCurrency = currencyDAO.findById((long) name.hashCode());
            if(!searchedCurrency.isPresent()){
                return false;
            } else if(searchedCurrency.get() instanceof CryptoCurrency){
                CryptoCurrency temp = (CryptoCurrency) searchedCurrency.get();
                if(temp.getSupplyInUse() != 0){
                    System.out.print("Não é possível remover criptomoedas com fornecimento em uso. ");
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
