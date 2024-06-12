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
   private Map<Class<? extends Entity>, DatabaseTableI<? extends Entity>> tables = new HashMap<>();
   private static Database databaseInstance = null;

   /**
    * Private constructor to prevent instantiation from outside the class. This
    * ensures that the singleton pattern is followed.
    */
   private Database() {}

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
    * Checks if the database contains a table for the specified entity class. If
    * not, it creates a new table for the entity class.
    *
    * @param <T>   the type of the entity. It must extend the Entity class.
    * @param clazz the class of the entity type.
    */
   private <T extends Entity> void checkClass(Class<T> clazz) {
      if (!tables.containsKey(clazz)) {
         tables.put(clazz, new DatabaseTable<T>());
      }
   }

   /**
    * Saves a new entity to the appropriate table in the database.
    * 
    * @param <T>    the type of the entity.
    * @param clazz  the class of the entity type.
    * @param entity the entity to be saved.
    * @throws DatabaseException if there is an error during the save operation.
    */
   @SuppressWarnings("unchecked")
   public <T extends Entity> void save(Class<T> clazz, T entity)
         throws DatabaseException {
      checkClass(clazz);

      try {
         DatabaseTable<T> table = (DatabaseTable<T>) tables.get(clazz);
         table.save(entity);
         tables.put(clazz, table);
      } catch (DatabaseException e) {
         throw new DatabaseException(e.getMessage() + " Classe: " + clazz, e);
      }
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
   public <T extends Entity> Optional<? extends Entity> findById(Class<T> clazz,
         Long id) throws DatabaseException {
      checkClass(clazz);

      try {
         return tables.get(clazz).findById(id);
      } catch (DatabaseException e) {
         throw new DatabaseException(e.getMessage() + " Classe: " + clazz, e);
      }
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
   public <T extends Entity> List<? extends Entity> findAll(Class<T> clazz)
         throws DatabaseException {
      checkClass(clazz);

      try {
         return tables.get(clazz).findAll();
      } catch (DatabaseException e) {
         throw new DatabaseException(e.getMessage() + " Classe: " + clazz, e);
      }
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
   @SuppressWarnings("unchecked")
   public <T extends Entity> void update(Class<T> clazz, Long id, T entity)
         throws DatabaseException {
      checkClass(clazz);

      try {
         DatabaseTable<T> table = (DatabaseTable<T>) tables.get(clazz);
         table.update(id, entity);
         tables.put(clazz, table);
      } catch (DatabaseException e) {
         throw new DatabaseException(e.getMessage() + " Classe: " + clazz, e);
      }
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
   public <T extends Entity> void delete(Class<T> clazz, Long id)
         throws DatabaseException {
      checkClass(clazz);

      try {
         tables.get(clazz).delete(id);
      } catch (DatabaseException e) {
         throw new DatabaseException(e.getMessage() + " Classe: " + clazz, e);
      }
   }
}