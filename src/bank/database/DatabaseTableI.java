package bank.database;

import java.util.List;
import java.util.Optional;

import bank.entity.Entity;
import bank.exception.DatabaseException;

/**
 * This interface represents a database table for a specific entity type. It
 * provides methods for CRUD operations (Create, Read, Update, Delete).
 *
 * @param <T> The type of entity this table represents. It must extend the
 *            Entity class.
 */
public interface DatabaseTableI<T extends Entity> {

   /**
    * Saves a new entity in the database.
    *
    * @param entity The entity to be saved.
    * @throws DatabaseException If there is an error while saving the entity.
    */
   void save(T entity) throws DatabaseException;

   /**
    * Finds an entity by its ID in the database.
    *
    * @param id The ID of the entity to be found.
    * @return An Optional containing the found entity, or an empty Optional if
    *         no entity is found.
    * @throws DatabaseException If there is an error while finding the entity.
    */
   Optional<T> findById(Integer id) throws DatabaseException;

   /**
    * Retrieves all entities from the database.
    *
    * @return A List containing all entities.
    * @throws DatabaseException If there is an error while retrieving the
    *                           entities.
    */
   List<T> findAll() throws DatabaseException;

   /**
    * Updates an existing entity in the database.
    *
    * @param id     The ID of the entity to be updated.
    * @param entity The updated entity.
    * @throws DatabaseException If there is an error while updating the entity.
    */
   void update(Integer id, T entity) throws DatabaseException;

   /**
    * Deletes an entity from the database by its ID.
    *
    * @param id The ID of the entity to be deleted.
    * @throws DatabaseException If there is an error while deleting the entity.
    */
   void delete(Integer id) throws DatabaseException;
}
