package bank.service;

import bank.dao.UserDAO;
import bank.entity.Account;
import bank.entity.Currency;
import java.util.Optional;
import bank.entity.User;

public class PIXService {

private static UserDAO userDAO = new UserDAO();

private static void debit(Currency currencyEntry, Double amountEntry){
    Long accountId = AccountService.getCurrentAccountId(AuthService.getUser().getDocument());
    for(Account account: AuthService.getUser().getAccounts()){
        if(account.getId().equals(accountId)){
            account.debit(currencyEntry, amountEntry);
        }
    }
}

public static Boolean transfer(String entryDocument, Currency currencyEntry, Double amountEntry){
    try {
        Optional<User> receiver = userDAO.findById((long) entryDocument.hashCode());
        for(Account account : receiver.get().getAccounts()){
            if(account.getId().equals(AccountService.getCurrentAccountId(entryDocument))){
                account.credit(currencyEntry, amountEntry);
                debit(currencyEntry, amountEntry);
                account.addTransaction(currencyEntry, amountEntry);
                AccountService.getCurrentAccountId(AuthService.getUser().getDocument());
                return true;
            }
        }
        System.out.print("Não foi encontrada uma conta relacionada a esse usuário. ");
        return false;
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage() + ". Não foi possível realizar depositar o valor na conta. ");
        return false;
    }
}

}
