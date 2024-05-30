import bank.view.MainView;
import bank.view.View;

public class App {
    public static void main(String[] args) throws Exception {
        View view = MainView.getInstance();
        view.startView();
    }
}
