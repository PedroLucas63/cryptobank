package bank.exception;

/**
 * Custom exception class for database related issues. This class extends the
 * built-in Exception class.
 */
public class DatabaseException extends Exception {
   /**
    * Constructs a new DatabaseException with the specified detail message.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    */
   public DatabaseException(String message) {
      super(message);
   }

   /**
    * Constructs a new DatabaseException with the specified detail message and
    * cause.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    * @param cause   the cause (which is saved for later retrieval by the
    *                Throwable.getCause() method)
    */
   public DatabaseException(String message, Throwable cause) {
      super(message, cause);
   }
}