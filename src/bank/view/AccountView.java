package bank.view;

public class AccountView extends ViewAbstract {
   enum State {
      BEGIN, END
   };

   State state = State.BEGIN;

   @Override
   public void process() {

   }

   @Override
   public void update() {}

   @Override
   public void view() {}

   @Override
   public Boolean exit() {
      return state == State.END;
   }
}