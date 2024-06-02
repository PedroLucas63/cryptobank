package bank.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import bank.entity.Entity;
import bank.exception.DAOException;

/**
 * A generic Data Access Object (DAO) interface for managing entities. This
 * interface defines the standard operations to be performed on a model
 * object(s).
 *
 * @param <T> the type of entities this DAO will handle, extending the Entity
 *            class.
 */
public interface DAO<T extends Entity> {
   /**
    * Saves a new entity to the database.
    *
    * @param entity the entity to be saved.
    * @throws DAOException if there is an error during the save operation.
    */
   void save(T entity) throws DAOException;

   /**
    * Finds an entity by its ID.
    *
    * @param id the ID of the entity to find.
    * @return an Optional containing the found entity, or an empty Optional if
    *         no entity is found.
    * @throws DAOException if there is an error during the find operation.
    */
   Optional<T> findById(Integer id) throws DAOException;

   /**
    * Retrieves all entities from the database.
    *
    * @return a list of all entities.
    * @throws DAOException if there is an error during the retrieval operation.
    */
   List<T> findAll() throws DAOException;

   /**
    * Retrieves all entities from the database that match the specified filter.
    *
    * @param filter a Predicate to filter the entities.
    * @return a list of all entities that match the filter.
    * @throws DAOException if there is an error during the retrieval operation.
    */
   List<T> findAll(Predicate<T> filter) throws DAOException;

   /**
    * Retrieves all entities from the database sorted by the specified
    * comparator.
    *
    * @param comparator a Comparator to sort the entities.
    * @return a list of all entities sorted by the comparator.
    * @throws DAOException if there is an error during the retrieval operation.
    */
   List<T> findAll(Comparator<T> comparator) throws DAOException;

   /**
    * Updates an existing entity in the database.
    *
    * @param id     the ID of the entity to update.
    * @param entity the updated entity.
    * @throws DAOException if there is an error during the update operation.
    */
   void update(Integer id, T entity) throws DAOException;

   /**
    * Deletes an entity from the database by its ID.
    *
    * @param id the ID of the entity to delete.
    * @throws DAOException if there is an error during the delete operation.
    */
   void delete(Integer id) throws DAOException;
}