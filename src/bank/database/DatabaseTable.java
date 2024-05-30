package bank.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bank.entity.Entity;
import bank.exception.DatabaseException;

/**
 * A generic class representing a database table that handles entities of type
 * T. This class implements the DatabaseTableI interface which defines the CRUD
 * operations.
 * 
 * @param <T> the type of entities this table will handle, extending the Entity
 *            class.
 */
public class DatabaseTable<T extends Entity> implements DatabaseTableI<T> {
   private List<T> entities = new ArrayList<>(); /// List of entities.

   /**
    * Saves a new entity to the database table.
    * 
    * @param entity the entity to be saved.
    * @throws DatabaseException if there is an error during the save operation.
    */
   @Override
   public void save(T entity) throws DatabaseException {
      // TODO: Auto-generated method stub
   }

   /**
    * Finds an entity by its ID.
    * 
    * @param id the ID of the entity to find.
    * @return an Optional containing the found entity, or an empty Optional if
    *         no entity is found.
    * @throws DatabaseException if there is an error during the find operation.
    */
   @Override
   public Optional<T> findById(int id) throws DatabaseException {
      // TODO: Auto-generated method stub
      return Optional.empty();
   }

   /**
    * Retrieves all entities from the database table.
    * 
    * @return a list of all entities.
    * @throws DatabaseException if there is an error during the retrieval
    *                           operation.
    */
   @Override
   public List<T> findAll() throws DatabaseException {
      // TODO: Auto-generated method stub
      return null;
   }

   /**
    * Updates an existing entity in the database table.
    * 
    * @param id     the ID of the entity to update.
    * @param entity the updated entity.
    * @throws DatabaseException if there is an error during the update
    *                           operation.
    */
   @Override
   public void update(int id, T entity) throws DatabaseException {
      // TODO: Auto-generated method stub
   }

   /**
    * Deletes an entity from the database table by its ID.
    * 
    * @param id the ID of the entity to delete.
    * @throws DatabaseException if there is an error during the delete
    *                           operation.
    */
   @Override
   public void delete(int id) throws DatabaseException {
      // TODO: Auto-generated method stub
   }
}
