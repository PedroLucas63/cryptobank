package bank.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import bank.entity.CryptoCurrency;
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
   private int actualId = 0;

   /**
    * Saves a new entity to the database table.
    * 
    * @param entity the entity to be saved.
    * @throws DatabaseException if there is an error during the save operation.
    */
   @Override
   public void save(T entity) throws DatabaseException { 
      try{
         entities.add(entity);
         entity.setId(actualId);
         actualId++;
      } 
      catch (Exception e) {
         throw new DatabaseException("Não foi possível adicionar o elemento ao DatabaseTable.", e);
      }
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
        try {
            return entities.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst();
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar a entidade com ID: " + id, e);
        }
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
        try {
            if (entities == null) {
                throw new DatabaseException("A lista de entidades é nula.");
            }
            return new ArrayList<>(entities); 
        } catch (Exception e) {
            throw new DatabaseException("Erro ao recuperar todas as entidades.", e);
        }
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
      try {
         entities.set(id, entity);
      } catch (Exception e) {
         throw new DatabaseException("Erro ao atualizar a entidade com ID: " + id, e);
      }
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
      try {
         boolean removed = entities.removeIf(entity -> entity.getId() == id);
         if (!removed) {
               throw new DatabaseException("Entidade não encontrada com ID: " + id);
         }
      } catch (Exception e) {
         throw new DatabaseException("Erro ao deletar a entidade com ID: " + id, e);
      }
   }
   /* TESTANDO  */
   //  public static void main(String[] args) {
   //      try {
   //          CryptoCurrency teste = new CryptoCurrency("Bitcoin", 68.520, 1000, 350);
   //          DatabaseTable<CryptoCurrency> testeTable = new DatabaseTable<>();
   //          testeTable.save(teste);

   //          // Tentando recuperar todas as entidades
   //          List<CryptoCurrency> allEntities = testeTable.findAll();
   //          allEntities.forEach(t -> {
   //             System.out.println(t.getName());
   //          });

   //      } catch (DatabaseException e) {
   //          e.printStackTrace();
   //      }
   //  }
   @Override
   public String toString() {
       return entities.toString();
   }
}
