package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.Role;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

public class RoleDAO implements DAO<Role> {
   Database database = Database.getInstance();

   @Override
   public void save(Role entity) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.save(Role.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public Optional<Role> findById(Integer id) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findById(Role.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<Role> findAll() throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(Role.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<Role> findAll(Predicate<Role> filter) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(Role.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public List<Role> findAll(Comparator<Role> comparator) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         return database.findAll(Role.class);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public void update(Integer id, Role entity) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.update(Role.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }

   @Override
   public void delete(Integer id) throws DAOException {
      // TODO: Auto-generated method stub

      try {
         database.delete(Role.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("", e);
      }
   }
}
