package bank.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bank.entity.Currency;
import bank.entity.Entity;
import bank.entity.Role;
import bank.entity.User;
import bank.exception.DatabaseException;

/**
 * Singleton class representing the database, which contains multiple tables for
 * different entities.
 */
public class Database{
   /// Map of all entity tables.
   private Map<Class<? extends Entity>, DatabaseTableI<? extends Entity>> tables = new HashMap<>();

   /// Singleton instance of the database.
   private static Database databaseInstance = null;

   /**
    * Private constructor to prevent instantiation from outside the class. This
    * ensures that the singleton pattern is followed.
    */
   private Database() {

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
   public <T extends Entity> void save(Class<T> clazz, T entity) throws DatabaseException {
      try{
         if(!tables.containsKey(clazz)){
            tables.put(clazz, new DatabaseTable<T>());
         }
         /*Solução temporária, pois não consegui resolver usando tables.get(clazz).save(entity); */
         @SuppressWarnings("unchecked")
         DatabaseTable<T> table = (DatabaseTable<T>) tables.get(clazz);
         table.save(entity);
         tables.put(clazz, table);
      } catch (Exception e){
         throw new DatabaseException("Não foi possível salvar essa Table no DataBase.", e);
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
   public <T extends Entity> Optional<? extends Entity> findById(Class<T> clazz, int id)
         throws DatabaseException {
      try {
         return tables.get(clazz).findById(id);
      } catch (Exception e) {
         throw new DatabaseException("Não foi possível encontrar uma entidade com esse ID no DataBase.", e);
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
      try {
         return tables.get(clazz).findAll();
      } catch (Exception e) {
         throw new DatabaseException("Não foi possível encontrar uma Table dessa entidade DataBase.", e);
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
   public <T extends Entity> void update(Class<T> clazz, int id, T entity)
         throws DatabaseException {
      try {
         /*Solução temporária, pois um problema semelhante ao put ocorreu */
         @SuppressWarnings("unchecked")
         DatabaseTable<T> temp = (DatabaseTable<T>)tables.get(clazz);
         temp.update(id, entity);
         tables.put(clazz, temp);
      } catch (Exception e) {
         throw new DatabaseException("Não foi possível atualizar a entidade em questão.", e);
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
   public <T extends Entity> void delete(Class<T> clazz, int id)
         throws DatabaseException {
         try {
            if(!tables.containsKey(clazz)){
               tables.put(clazz, new DatabaseTable<T>());
            }
            tables.get(clazz).delete(id);
         } catch (Exception e) {
         throw new DatabaseException("Não foi possível deletar a entidade em questão.", e);
         }
   }

   /* PARA FINS DE TESTE */
   public static void main(String[] args) {
      Database teste = Database.getInstance();
      DatabaseTable<User> testeT = new DatabaseTable<>();
      User testeU = new User("Jorge", "412.594.754-68", "senha123", 80, "teste@teste.com");
      try {
         testeT.save(testeU);
         teste.save(User.class, testeU);
         System.out.println(teste.findById(User.class, 0));
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}