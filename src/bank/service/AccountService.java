package bank.service;

import java.util.List;
import bank.dao.UserDAO;
import bank.entity.Account;
import bank.entity.CryptoAccount;
import bank.entity.CurrentAccount;
import bank.entity.User;

public class AccountService {
   private static UserDAO userDAO = new UserDAO();
   private static Account activeAccount = null;

   private static final Integer accountDigits = 4;

   private static void createAccount(Class<? extends Account> accountType) {
      try {
         Account newAccount = accountType.getDeclaredConstructor()
               .newInstance();

         User loggedUser = AuthService.getUser();
         Long userId = loggedUser.getId();
         Integer amountAccount = loggedUser.getAccounts().size();

         String accountId = userId
               + String.format("%0" + accountDigits + "d", amountAccount);

         newAccount.setId(Long.parseLong(accountId));
         loggedUser.addAccount(newAccount);

         userDAO.update(loggedUser.getId(), loggedUser);
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

   public static void setActiveAccount(Account account) {
      activeAccount = account;
   }

   public static Account getOpenAccount() {
      return activeAccount;
   }

   public static void closeAccount() {
      activeAccount = null;
   }

   public static List<Account> getAccounts() {
      return AuthService.getUser().getAccounts();
   }
}
