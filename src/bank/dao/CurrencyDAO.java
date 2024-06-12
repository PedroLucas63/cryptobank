package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.database.Database;
import bank.entity.Currency;
import bank.exception.DAOException;
import bank.exception.DatabaseException;

/**
 * CurrencyDAO class is responsible for managing Currency data in the database.
 * It implements the DAO interface and provides methods for CRUD operations.
 */
public class CurrencyDAO implements DAO<Currency> {
   private Database database = Database.getInstance();

   /**
    * Saves a Currency entity in the database.
    *
    * @param entity The Currency entity to be saved.
    * @throws DAOException If an error occurs while saving the entity.
    */
   @Override
   public void save(Currency entity) throws DAOException {
      try {
         database.save(Currency.class, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na inserção da moeda.", e);
      }
   }

   /**
    * Finds a Currency entity by its ID in the database.
    *
    * @param id The ID of the Currency entity to be found.
    * @return An Optional containing the Currency entity if found, otherwise an
    *         empty Optional.
    * @throws DAOException If an error occurs while finding the entity.
    */
   @SuppressWarnings("unchecked")
   @Override
   public Optional<Currency> findById(Long id) throws DAOException {
      try {
         return (Optional<Currency>) database.findById(Currency.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca pela moeda.", e);
      }
   }

   /**
    * Retrieves all Currency entities from the database.
    *
    * @return A List of Currency entities.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Currency> findAll() throws DAOException {
      try {
         return (List<Currency>) database.findAll(Currency.class);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca das moedas.", e);
      }
   }

   /**
    * Retrieves Currency entities from the database based on a filter.
    *
    * @param filter The Predicate to filter the Currency entities.
    * @return A List of Currency entities that satisfy the filter.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Currency> findAll(Predicate<Currency> filter)
         throws DAOException {
      try {
         List<Currency> list = (List<Currency>) database
               .findAll(Currency.class);
         return list.stream().filter(filter).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca das moedas.", e);
      }
   }

   /**
    * Retrieves Currency entities from the database and sorts them based on a
    * comparator.
    *
    * @param comparator The Comparator to sort the Currency entities.
    * @return A List of Currency entities sorted by the comparator.
    * @throws DAOException If an error occurs while retrieving the entities.
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Currency> findAll(Comparator<Currency> comparator)
         throws DAOException {
      try {
         List<Currency> list = (List<Currency>) database
               .findAll(Currency.class);
         return list.stream().sorted(comparator).toList();
      } catch (DatabaseException e) {
         throw new DAOException("Erro na busca das moedas.", e);
      }
   }

   /**
    * Updates a Currency entity in the database.
    *
    * @param id     The ID of the Currency entity to be updated.
    * @param entity The updated Currency entity.
    * @throws DAOException If an error occurs while updating the entity.
    */
   @Override
   public void update(Long id, Currency entity) throws DAOException {
      try {
         database.update(Currency.class, id, entity);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na atualização da moeda.", e);
      }
   }

   /**
    * Deletes a Currency entity from the database.
    *
    * @param id The ID of the Currency entity to be deleted.
    * @throws DAOException If an error occurs while deleting the entity.
    */
   @Override
   public void delete(Long id) throws DAOException {
      try {
         database.delete(Currency.class, id);
      } catch (DatabaseException e) {
         throw new DAOException("Erro na remoção da moeda.", e);
      }
   }
}