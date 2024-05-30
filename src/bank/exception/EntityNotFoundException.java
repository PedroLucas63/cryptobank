package bank.exception;

/**
 * This class represents an exception that is thrown when an entity (e.g., a
 * customer, account, etc.) is not found in the database. It extends the
 * DatabaseException class.
 */
public class EntityNotFoundException extends DatabaseException {

   /**
    * Constructs a new EntityNotFoundException with the specified detail
    * message.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    */
   public EntityNotFoundException(String message) {
      super(message);
   }

   /**
    * Constructs a new EntityNotFoundException with the specified detail message
    * and cause.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    * @param cause   the cause (which is saved for later retrieval by the
    *                Throwable.getCause() method)
    */
   public EntityNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }
}