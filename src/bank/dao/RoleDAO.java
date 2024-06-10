package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.Role;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

/**
 * Role Data Access Object (DAO) class. This class provides methods to interact
 * with the Role entity in the database.
 */
public class RoleDAO implements DAO<Role> {
   private Database database = Database.getInstance();

   /**
    * Saves a Role entity in the database.
    *
    * @param entity The Role entity to be saved.
    * @throws DAOException If an error occurs while saving the entity.
    */
   @Override
   public void save(Role entity) throws DAOException {
      try {
         database.save(Role.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na inserção do cargo.", e);
      }
   }

   /**
    * Finds a Role entity by its ID in the database.
    *
    * @param id The ID of the Role entity to be found.
    * @return An Optional containing the found Role entity, or an empty Optional
    *         if not found.
    * @throws DAOException If an error occurs while finding the entity.
    */
   @SuppressWarnings("unchecked")
   @Override
   public Optional<Role> findById(Integer id) throws DAOException {
      try {
         return (Optional<Role>) database.findById(Role.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca pelo cargo.", e);
      }
   }

   /**
    * Retrieves all Role entities from the database.
    *
    * @return A List of all Role entities.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Role> findAll() throws DAOException {
      try {
         return (List<Role>) database.findAll(Role.class);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos cargos.", e);
      }
   }

   /**
    * Retrieves all Role entities from the database that satisfy a given filter.
    *
    * @param filter The filter to be applied.
    * @return A List of Role entities that satisfy the filter.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Role> findAll(Predicate<Role> filter) throws DAOException {
      try {
         List<Role> list = (List<Role>) database.findAll(Role.class);
         return list.stream().filter(filter).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos cargos.", e);
      }
   }

   /**
    * Retrieves all Role entities from the database and sorts them according to
    * a given comparator.
    *
    * @param comparator The comparator to be applied.
    * @return A List of Role entities sorted according to the comparator.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Role> findAll(Comparator<Role> comparator) throws DAOException {
      try {
         List<Role> list = (List<Role>) database.findAll(Role.class);
         return list.stream().sorted(comparator).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos cargos.", e);
      }
   }

   /**
    * Updates a Role entity in the database.
    *
    * @param id     The ID of the Role entity to be updated.
    * @param entity The updated Role entity.
    * @throws DAOException If an error occurs while updating the entity.
    */
   @Override
   public void update(Integer id, Role entity) throws DAOException {
      try {
         database.update(Role.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na atualização do cargo.", e);
      }
   }

   /**
    * Deletes a Role entity from the database by its ID.
    *
    * @param id The ID of the Role entity to be deleted.
    * @throws DAOException If an error occurs while deleting the entity.
    */
   @Override
   public void delete(Integer id) throws DAOException {
      try {
         database.delete(Role.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na remoção do cargo.", e);
      }
   }
}