package bank.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import bank.entity.Entity;
import bank.exception.DatabaseException;
import bank.exception.EntityNotFoundException;
import bank.exception.UniqueKeyException;

/**
 * A generic class representing a database table that handles entities of type
 * T. This class implements the DatabaseTableI interface which defines the CRUD
 * operations.
 * 
 * @param <T> the type of entities this table will handle, extending the Entity
 *            class.
 */
public class DatabaseTable<T extends Entity> implements DatabaseTableI<T> {
   private Map<Integer, T> entities = new HashMap<>();

   /**
    * Saves a new entity to the database table.
    * 
    * @param entity the entity to be saved.
    * @throws DatabaseException if there is an error during the save operation.
    */
   @Override
   public void save(T entity) throws DatabaseException {
      try {
         entity.setId(entity.hashCode());

         if (entities.containsKey(entity.getId())) {
            throw new UniqueKeyException("A chave única da entidade "
                  + entity.getId() + " já está em uso.");
         }

         entities.put(entity.getId(), entity);
      } catch (Exception e) {
         throw new DatabaseException(
               "Não foi possível adicionar o elemento ao DatabaseTable.", e);
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
   public Optional<T> findById(Integer id) throws DatabaseException {
      try {
         return Optional.of(entities.get(id));
      } catch (Exception e) {
         throw new DatabaseException("Erro ao buscar a entidade " + id, e);
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
         return new ArrayList<>(entities.values());
      } catch (Exception e) {
         throw new DatabaseException("Erro ao recuperar todas as entidades.",
               e);
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
   public void update(Integer id, T entity) throws DatabaseException {
      try {
         if (!entities.containsKey(id)) {
            throw new EntityNotFoundException(
                  "A entidade " + id + " não existe.");
         } else if (!entities.get(id).equals(entity)) {
            throw new UniqueKeyException(
                  "A chave única da entidade " + id + " já está em uso.");
         }

         entities.replace(id, entity);
      } catch (Exception e) {
         throw new DatabaseException("Erro ao atualizar a entidade " + id, e);
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
   public void delete(Integer id) throws DatabaseException {
      try {
         T removed = entities.remove(id);

         if (removed == null) {
            throw new EntityNotFoundException(
                  "A entidade " + id + " não existe.");
         }
      } catch (Exception e) {
         throw new DatabaseException("Erro ao deletar a entidade " + id, e);
      }
   }
}
