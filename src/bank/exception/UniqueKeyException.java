package bank.exception;

/**
 * This class represents a custom exception that is thrown when a unique key
 * constraint is violated in the database. It extends the DatabaseException
 * class.
 */
public class UniqueKeyException extends DatabaseException {

   /**
    * Constructs a new UniqueKeyException with the specified detail message.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    */
   public UniqueKeyException(String message) {
      super(message);
   }

   /**
    * Constructs a new UniqueKeyException with the specified detail message and
    * cause.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    * @param cause   the cause (which is saved for later retrieval by the
    *                Throwable.getCause() method)
    */
   public UniqueKeyException(String message, Throwable cause) {
      super(message, cause);
   }
}
