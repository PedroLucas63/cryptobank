package bank.exception;

/**
 * Custom exception class for Data Access Object (DAO) operations. This class
 * extends the base Exception class and provides constructors to handle both
 * simple error messages and error messages with a cause.
 */
public class DAOException extends Exception {
   /**
    * Constructs a new DAOException with the specified detail message.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    */
   public DAOException(String message) {
      super(message);
   }

   /**
    * Constructs a new DAOException with the specified detail message and cause.
    *
    * @param message the detail message (which is saved for later retrieval by
    *                the Throwable.getMessage() method)
    * @param cause   the cause (which is saved for later retrieval by the
    *                Throwable.getCause() method)
    */
   public DAOException(String message, Throwable cause) {
      super(message, cause);
   }
}