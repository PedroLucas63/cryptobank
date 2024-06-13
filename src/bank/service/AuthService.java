package bank.service;

import java.util.Optional;
import bank.dao.UserDAO;
import bank.entity.User;
import bank.exception.DAOException;
import bank.utils.BCryptUtils;
import bank.utils.DocumentTransformer;

public class AuthService {
   private static UserDAO userDAO = new UserDAO();
   private static User user = null;

   public static Boolean login(String document, String password) {
      try {
         document = DocumentTransformer.transform(document);

         Optional<User> searchedUser = userDAO
               .findById((long) document.hashCode());

         if (!searchedUser.isPresent()) {
            return false;
         }

         if (BCryptUtils.verify(password, searchedUser.get().getPassword())) {
            user = searchedUser.get();
            return true;
         } else {
            return false;
         }
      } catch (DAOException e) {
         return false;
      }
   }

   public static void logout() {
      user = null;
   }

   public static User getUser() {
      return user;
   }
}
