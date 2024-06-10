package bank.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bank.dao.UserDAO;
import bank.entity.Employee;
import bank.entity.User;
import bank.exception.DAOException;
import bank.utils.UserValidator;

public class UserService {
   private UserDAO userDAO = new UserDAO();

   public Boolean create(String name, String document, String password,
         Integer age, String email) {
      /// TODO: Remover pontuação do documento
      if (UserValidator.isValid(name, document, password, age, email)) {
         try {
            password = BCrypt.withDefaults().hashToString(12,
                  password.toCharArray());
            userDAO.save(new User(name, document, password, age, email));
            return true;
         } catch (DAOException e) {
            return false;
         }
      } else {
         return false;
      }
   }

   public Boolean isClient() {
      return AuthService.getUser() != null;
   }

   public Boolean isEmployee() {
      if (AuthService.getUser() != null) {
         return AuthService.getUser() instanceof Employee;
      }

      return false;
   }
}
