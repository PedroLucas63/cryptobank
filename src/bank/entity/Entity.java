package bank.entity;

/**
 * This class represents a basic entity with an id. It provides getter and
 * setter methods for the id.
 */
public class Entity {
   private int id; /// The unique identifier of the entity.

   /**
    * Returns the id of the entity.
    *
    * @return the id of the entity
    */
   public int getId() {
      return id;
   }

   /**
    * Sets the id of the entity.
    *
    * @param id the new id of the entity
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * Overrides the equals method to compare two Entity objects based on their
    * id.
    *
    * @param obj The object to compare with this Entity.
    * @return true if the obj is an instance of Entity and has the same id as
    *         this Entity, false otherwise.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Entity) {
         return ((Entity) obj).getId() == id;
      }

      return false;
   }
}
