package bank.service;

import java.util.List;
import bank.dao.UserDAO;
import bank.entity.Account;

public class AccountService {
   private static UserDAO userDAO = new UserDAO();

   public static List<Account> getAccounts() {
      if (AuthService.getUser() == null) {
         throw new IllegalStateException("O usuário não está autenticado.");
      }
      return AuthService.getUser().getAccounts();
   }
}
