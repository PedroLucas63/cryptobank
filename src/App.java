import bank.view.MainView;
import bank.view.View;

public class App {
    public static void main(String[] args) throws Exception {
        seed();

        View view = new MainView();
        view.startView();
    }

    private static void seed() {

    }
}
