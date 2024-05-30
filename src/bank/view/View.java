package bank.view;

/**
 * Interface representing a generic View in the Model-View-Controller (MVC)
 * architecture. Views are responsible for displaying data to the user and
 * handling user interactions.
 */
public interface View {

   /**
    * Method to start the view. This method will continue running the view until
    * the exit condition is met.
    */
   default void startView() {
      while (!exit()) {
         process(); // Process user input or perform necessary actions.
         update(); // Update the view based on any changes.
         view(); // Render the view to display data to the user.
      }
   }

   /**
    * Method to check if the view should exit.
    * 
    * @return true if the view should exit, false otherwise.
    */
   boolean exit();

   /**
    * Method to process user input or perform necessary actions. This method is
    * typically used to handle user interactions.
    */
   void process();

   /**
    * Method to update the view based on any changes. This method is used to
    * update the view's state or data.
    */
   void update();

   /**
    * Method to render the view and display data to the user. This method is
    * responsible for rendering the view's content.
    */
   void view();
}
