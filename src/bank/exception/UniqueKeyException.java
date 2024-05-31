package bank.exception;

public class UniqueKeyException extends DatabaseException {
   public UniqueKeyException(String message) {
      super(message);
   }

   public UniqueKeyException(String message, Throwable cause) {
      super(message, cause);
   }

}
