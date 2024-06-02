package bank.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bank.entity.Entity;
import bank.exception.DatabaseException;

/**
 * Singleton class representing the database, which contains multiple tables for
 * different entities.
 */
public class Database {
   /// Map of all entity tables.
   private Map<Class<? extends Entity>, DatabaseTableI<? extends Entity>> tables = new HashMap<>();

   /// Singleton instance of the database.
   private static Database databaseInstance = null;

   /**
    * Private constructor to prevent instantiation from outside the class. This
    * ensures that the singleton pattern is followed.
    */
   private Database() {
      // Initialize the database with necessary tables.
   }

   /**
    * Provides the singleton instance of the database.
    * 
    * @return the singleton instance of the Database.
    */
   public static Database getInstance() {
      if (databaseInstance == null) {
         databaseInstance = new Database();
      }

      return databaseInstance;
   }

   /**
    * Saves a new entity to the appropriate table in the database.
    * 
    * @param <T>    the type of the entity.
    * @param clazz  the class of the entity type.
    * @param entity the entity to be saved.
    * @throws DatabaseException if there is an error during the save operation.
    */
   public <T extends Entity> void save(Class<T> clazz, T entity)
         throws DatabaseException {
      // TODO: Implement the method to save the entity.
   }

   /**
    * Finds an entity by its ID from the appropriate table in the database.
    * 
    * @param <T>   the type of the entity.
    * @param clazz the class of the entity type.
    * @param id    the ID of the entity to find.
    * @return an Optional containing the found entity, or an empty Optional if
    *         no entity is found.
    * @throws DatabaseException if there is an error during the find operation.
    */
   public <T extends Entity> Optional<T> findById(Class<T> clazz, Integer id)
         throws DatabaseException {
      // TODO: Implement the method to find the entity by ID.
      return Optional.empty();
   }

   /**
    * Retrieves all entities from the appropriate table in the database.
    * 
    * @param <T>   the type of the entity.
    * @param clazz the class of the entity type.
    * @return a list of all entities of the specified type.
    * @throws DatabaseException if there is an error during the retrieval
    *                           operation.
    */
   public <T extends Entity> List<T> findAll(Class<T> clazz)
         throws DatabaseException {
      // TODO: Implement the method to retrieve all entities.
      return null;
   }

   /**
    * Updates an existing entity in the appropriate table in the database.
    * 
    * @param <T>    the type of the entity.
    * @param clazz  the class of the entity type.
    * @param id     the ID of the entity to update.
    * @param entity the updated entity.
    * @throws DatabaseException if there is an error during the update
    *                           operation.
    */
   public <T extends Entity> void update(Class<T> clazz, Integer id, T entity)
         throws DatabaseException {
      // TODO: Implement the method to update the entity.
   }

   /**
    * Deletes an entity from the appropriate table in the database by its ID.
    * 
    * @param <T>   the type of the entity.
    * @param clazz the class of the entity type.
    * @param id    the ID of the entity to delete.
    * @throws DatabaseException if there is an error during the delete
    *                           operation.
    */
   public <T extends Entity> void delete(Class<T> clazz, Integer id)
         throws DatabaseException {
      // TODO: Implement the method to delete the entity.
   }
}