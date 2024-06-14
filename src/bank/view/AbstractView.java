package bank.view;

public abstract class AbstractView implements View {
   protected String warning = null;

   protected String getWarning() {
      return warning;
   }

   protected void title() {
      System.out.println("=====================================");
      System.out.println("   Cryptobank - O seu banco seguro   ");
      System.out.println("=====================================");

      if (warning != null) {
         System.out.println("\nAviso: " + warning + "\n");
      }
   }

   @Override
   public void startView() {
      do {
         process();
         update();
         view();
      } while (!exit());
   }
}
