package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.User;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

public class UserDAO implements DAO<User> {
   Database database = Database.getInstance();

   @Override
   public void save(User entity) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.save(User.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public Optional<User> findById(Integer id) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findById(User.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<User> findAll() throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(User.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<User> findAll(Predicate<User> filter) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(User.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<User> findAll(Comparator<User> comparator) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(User.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public void update(Integer id, User entity) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.update(User.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public void delete(Integer id) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.delete(User.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }
}
