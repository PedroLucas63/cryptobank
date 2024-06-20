package bank.service;

import java.util.List;
import java.util.Optional;

import bank.dao.UserDAO;
import bank.entity.Account;
import bank.entity.CryptoAccount;
import bank.entity.CurrentAccount;
import bank.entity.User;
import bank.utils.DocumentTransformer;

public class AccountService {
   private static final Integer accountDigits = 4;
   private static UserDAO userDAO = new UserDAO();

   private static Long getNewAccountId() {
      User loggedUser = AuthService.getUser();
      Long userId = loggedUser.getId();
      Integer amountAccount = loggedUser.getAccounts().size();

      String accountId = userId
            + String.format("%0" + accountDigits + "d", amountAccount);

      return Long.parseLong(accountId);
   }

   private static Long getNewAccountId(User user, Integer accountNumber) {
      Long userId = user.getId();

      String accountId = userId
            + String.format("%0" + accountDigits + "d", accountNumber);

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

   public static Long getCurrentAccountId(String userDocument) {
      Long userId = (long) userDocument.hashCode();
      Long accountId = 0L;
      try {
         Optional<User> receiver = userDAO.findById(userId);
         accountId = getNewAccountId(receiver.get(), 0);
         return accountId;
      } catch (Exception e) {
         System.out.print(e.getMessage() + "A conta especificada não foi encontrada. ");
         return null;
      }
   }

    public static Boolean validUser(String document){
      document = DocumentTransformer.transform(document);
      try {
         Optional<User> searchedUser = userDAO
               .findById((long) document.hashCode());
         if(!searchedUser.isPresent()){
            return false;
         }
         else{
            return true;
         }  
      } catch (Exception e) {
         return false;
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
}
