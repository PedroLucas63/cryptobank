package bank.service;

import java.util.List;
import java.util.Map;

import bank.dao.UserDAO;
import bank.entity.Account;
import bank.entity.CryptoAccount;
import bank.entity.Currency;
import bank.entity.CurrentAccount;
import bank.entity.User;

public class AccountService {
   private static final Integer accountDigits = 4;

   private static Long getNewAccountId() {
      User loggedUser = AuthService.getUser();
      Long userId = loggedUser.getId();
      Integer amountAccount = loggedUser.getAccounts().size();

      String accountId = userId
            + String.format("%0" + accountDigits + "d", amountAccount);

      return Long.parseLong(accountId);
   }

   private static void createAccount(Class<? extends Account> accountType) {
      try {
         Account newAccount = accountType.getDeclaredConstructor()
               .newInstance();
         newAccount.setId(getNewAccountId());

         User loggedUser = AuthService.getUser();
         loggedUser.addAccount(newAccount);

         UserService.updateLogged();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   public static void createCurrentAccount() {
      createAccount(CurrentAccount.class);
   }

   public static void createCryptoAccount() {
      createAccount(CryptoAccount.class);
   }

   public static Boolean hasAccounts() {
      return !AuthService.getUser().getAccounts().isEmpty();
   }

   public static List<Account> getAccounts() {
      return AuthService.getUser().getAccounts();
   }

   protected static Map<Currency, Double> getFiatBalances() {
      for (Account account : getAccounts()) {
         if (account instanceof CurrentAccount) {
            return account.getBalances();
         }
      }

      return null;
   }

   protected static Map<Currency, Double> getCryptoBalances() {
      for (Account account : getAccounts()) {
         if (account instanceof CryptoAccount) {
            return account.getBalances();
         }
      }

      return null;
   }

   public static void updateFiatBalance(Currency currency, Double amount) {
      for (Account account : getAccounts()) {
         if (account instanceof CurrentAccount) {
            if (amount > 0) {
               account.credit(currency, amount);
            } else {
               account.debit(currency, Math.abs(amount));
            }

            UserService.updateLogged();
            break;
         }
      }
   }

   public static void updateCryptoBalance(Currency currency, Double amount) {
      for (Account account : getAccounts()) {
         if (account instanceof CryptoAccount) {
            if (amount > 0) {
               account.credit(currency, amount);
            } else {
               account.debit(currency, Math.abs(amount));
            }

            UserService.updateLogged();
            break;
         }
      }
   }
}
