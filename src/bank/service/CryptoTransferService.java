package bank.service;

import java.util.Optional;

import bank.dao.UserDAO;
import bank.entity.Account;
import bank.entity.CryptoAccount;
import bank.entity.Currency;
import bank.entity.User;

public class CryptoTransferService {
   private static UserDAO userDAO = new UserDAO();

   public static Boolean transfer(String accountId, Currency currencyEntry,
         Double amountEntry) {
      try {
         Long userId = AccountService
               .getUserByAccount(Long.parseLong(accountId));
         Long accountIndex = AccountService.getAccountIndexByAccount(userId,
               Long.parseLong(accountId));

         Optional<User> searchedUser = userDAO
               .findById((long) userId.hashCode());

         if (!searchedUser.isPresent()
               || searchedUser.get().getAccounts().size() < accountIndex) {
            return false;
         }

         Account receiveAccount = searchedUser.get().getAccounts()
               .get(accountIndex.intValue());
         if (!(receiveAccount instanceof CryptoAccount)) {
            return false;
         }

         receiveAccount.credit(currencyEntry, amountEntry);
         receiveAccount.addTransaction(currencyEntry, amountEntry);

         BalancesService.updateCryptoBalance(currencyEntry, -amountEntry);
         userDAO.update(searchedUser.get().getId(), searchedUser.get());

         return true;
      } catch (Exception e) {
         return false;
      }
   }

}
