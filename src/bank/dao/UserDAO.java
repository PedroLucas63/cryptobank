package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.User;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

/**
 * User Data Access Object (DAO) class for managing User entities in the
 * database. Implements the DAO interface for User entities.
 */
public class UserDAO implements DAO<User> {
   private Database database = Database.getInstance();

   /**
    * Saves a User entity in the database.
    *
    * @param entity The User entity to be saved.
    * @throws DAOException If an error occurs while saving the entity.
    */
   @Override
   public void save(User entity) throws DAOException {
      try {
         database.save(User.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na inserção do usuário.", e);
      }
   }

   /**
    * Finds a User entity by its ID in the database.
    *
    * @param id The ID of the User entity to be found.
    * @return An Optional containing the found User entity, or an empty Optional
    *         if not found.
    * @throws DAOException If an error occurs while finding the entity.
    */
   @SuppressWarnings("unchecked")
   @Override
   public Optional<User> findById(Integer id) throws DAOException {
      try {
         return (Optional<User>) database.findById(User.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca pelo usuário.", e);
      }
   }

   /**
    * Retrieves all User entities from the database.
    *
    * @return A List of all User entities.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<User> findAll() throws DAOException {
      try {
         return (List<User>) database.findAll(User.class);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos usuários.", e);
      }
   }

   /**
    * Retrieves all User entities from the database that satisfy a given filter.
    *
    * @param filter The Predicate to filter the entities.
    * @return A List of User entities that satisfy the filter.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<User> findAll(Predicate<User> filter) throws DAOException {
      try {
         List<User> list = (List<User>) database.findAll(User.class);
         return list.stream().filter(filter).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos usuários.", e);
      }
   }

   /**
    * Retrieves all User entities from the database and sorts them according to
    * a given comparator.
    *
    * @param comparator The Comparator to sort the entities.
    * @return A List of User entities sorted according to the comparator.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<User> findAll(Comparator<User> comparator) throws DAOException {
      try {
         List<User> list = (List<User>) database.findAll(User.class);
         return list.stream().sorted(comparator).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos usuários.", e);
      }
   }

   /**
    * Updates a User entity in the database.
    *
    * @param id     The ID of the User entity to be updated.
    * @param entity The updated User entity.
    * @throws DAOException If an error occurs while updating the entity.
    */
   @Override
   public void update(Integer id, User entity) throws DAOException {
      try {
         database.update(User.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na atualização do usuário.", e);
      }
   }

   /**
    * Deletes a User entity from the database by its ID.
    *
    * @param id The ID of the User entity to be deleted.
    * @throws DAOException If an error occurs while deleting the entity.
    */
   @Override
   public void delete(Integer id) throws DAOException {
      try {
         database.delete(User.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na remoção do usuário.", e);
      }
   }
}