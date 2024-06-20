package bank.service;

import bank.dao.UserDAO;
import bank.entity.Account;
import bank.entity.Currency;
import bank.entity.CurrentAccount;

import java.util.Optional;
import bank.entity.User;
import bank.utils.DocumentTransformer;

public class PIXService {
    private static UserDAO userDAO = new UserDAO();

    public static Boolean transfer(String entryDocument, Currency currencyEntry,
            Double amountEntry) {
        try {
            entryDocument = DocumentTransformer.transform(entryDocument);
            Optional<User> receiver = userDAO
                    .findById((long) entryDocument.hashCode());

            if (!receiver.isPresent()) {
                return false;
            }

            for (Account receiverAccount : receiver.get().getAccounts()) {
                if (receiverAccount instanceof CurrentAccount) {
                    receiverAccount.credit(currencyEntry, amountEntry);
                    receiverAccount.addTransaction(currencyEntry, amountEntry);

                    BalancesService.updateFiatBalance(currencyEntry,
                            -amountEntry);
                    userDAO.update(receiver.get().getId(), receiver.get());
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
