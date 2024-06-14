package bank.service;

import bank.dao.UserDAO;
import bank.entity.Employee;
import bank.entity.User;
import bank.exception.DAOException;
//import bank.utils.BCryptUtils;
import bank.utils.DocumentTransformer;

public class UserService {
   private static UserDAO userDAO = new UserDAO();

   public static Boolean create(String name, String document, String password,
         Integer age, String email) {
      try {
         document = DocumentTransformer.transform(document);
         //password = BCryptUtils.encrypt(password);

         userDAO.save(new User(name, document, password, age, email));

         return true;
      } catch (DAOException e) {
         return false;
      }

   }

   public static Boolean isClient() {
      return AuthService.getUser() != null;
   }

   public static Boolean isEmployee() {
      if (AuthService.getUser() != null) {
         return AuthService.getUser() instanceof Employee;
      }

      return false;
   }
}
