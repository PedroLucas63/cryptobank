package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.Career;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

/**
 * Career Data Access Object (DAO) class. This class provides methods to
 * interact with the Career entity in the database.
 */
public class CareerDAO implements DAO<Career> {
   private Database database = Database.getInstance();

   /**
    * Saves a Career entity in the database.
    *
    * @param entity The Career entity to be saved.
    * @throws DAOException If an error occurs while saving the entity.
    */
   @Override
   public void save(Career entity) throws DAOException {
      try {
         database.save(Career.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na inserção do cargo.", e);
      }
   }

   /**
    * Finds a Career entity by its ID in the database.
    *
    * @param id The ID of the Career entity to be found.
    * @return An Optional containing the found Career entity, or an empty
    *         Optional if not found.
    * @throws DAOException If an error occurs while finding the entity.
    */
   @SuppressWarnings("unchecked")
   @Override
   public Optional<Career> findById(Long id) throws DAOException {
      try {
         return (Optional<Career>) database.findById(Career.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca pelo cargo.", e);
      }
   }

   /**
    * Retrieves all Career entities from the database.
    *
    * @return A List of all Career entities.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Career> findAll() throws DAOException {
      try {
         return (List<Career>) database.findAll(Career.class);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos cargos.", e);
      }
   }

   /**
    * Retrieves all Career entities from the database that satisfy a given
    * filter.
    *
    * @param filter The filter to be applied.
    * @return A List of Career entities that satisfy the filter.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Career> findAll(Predicate<Career> filter) throws DAOException {
      try {
         List<Career> list = (List<Career>) database.findAll(Career.class);
         return list.stream().filter(filter).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos cargos.", e);
      }
   }

   /**
    * Retrieves all Career entities from the database and sorts them according
    * to a given comparator.
    *
    * @param comparator The comparator to be applied.
    * @return A List of Career entities sorted according to the comparator.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Career> findAll(Comparator<Career> comparator)
         throws DAOException {
      try {
         List<Career> list = (List<Career>) database.findAll(Career.class);
         return list.stream().sorted(comparator).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca dos cargos.", e);
      }
   }

   /**
    * Updates a Career entity in the database.
    *
    * @param id     The ID of the Career entity to be updated.
    * @param entity The updated Career entity.
    * @throws DAOException If an error occurs while updating the entity.
    */
   @Override
   public void update(Long id, Career entity) throws DAOException {
      try {
         database.update(Career.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na atualização do cargo.", e);
      }
   }

   /**
    * Deletes a Career entity from the database by its ID.
    *
    * @param id The ID of the Career entity to be deleted.
    * @throws DAOException If an error occurs while deleting the entity.
    */
   @Override
   public void delete(Long id) throws DAOException {
      try {
         database.delete(Career.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na remoção do cargo.", e);
      }
   }
}