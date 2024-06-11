package bank.service;

import java.util.Optional;
import at.favre.lib.crypto.bcrypt.BCrypt;
import bank.dao.UserDAO;
import bank.entity.User;
import bank.exception.DAOException;
import bank.utils.DocumentTransformer;

public class AuthService {
   private UserDAO userDAO = new UserDAO();
   private static User user = null;

   public Boolean login(String document, String password) {
      try {
         document = DocumentTransformer.transform(document);
         Optional<User> user = userDAO.findById(document.hashCode());

         if (!user.isPresent()) {
            return false;
         }

         BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),
               user.get().getPassword().toCharArray());

         if (result.verified) {
            this.user = user.get();
            return true;
         } else {
            return false;
         }
      } catch (DAOException e) {
         return false;
      }
   }

   public void logout() {
      user = null;
   }

   public static User getUser() {
      return user;
   }
}
