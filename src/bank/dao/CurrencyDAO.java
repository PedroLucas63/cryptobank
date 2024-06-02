package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.Currency;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

public class CurrencyDAO implements DAO<Currency> {
   Database database = Database.getInstance();

   @Override
   public void save(Currency entity) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.save(Currency.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public Optional<Currency> findById(Integer id) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findById(Currency.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<Currency> findAll() throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(Currency.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<Currency> findAll(Predicate<Currency> filter)
         throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(Currency.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<Currency> findAll(Comparator<Currency> comparator)
         throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(Currency.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public void update(Integer id, Currency entity) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.update(Currency.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public void delete(Integer id) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.delete(Currency.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }
}
