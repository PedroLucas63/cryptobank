import bank.dao.CareerDAO;
import bank.entity.Career;
import bank.service.EmployeeService;
import bank.service.UserService;
import bank.view.MainView;
import bank.view.View;

public class App {
    public static void main(String[] args) throws Exception {
        seed();

        View view = new MainView();
        view.startView();
    }

    private static void seed() {
        UserService.create("Ana Maria", "123.456.789-10", "Ana123", 30,
                "ana.maria@example.com");
        UserService.create("João Silva", "11.222.333/0001-81", "Joao123", 45,
                "joao.silva@example.com");
        UserService.create("Mariana Souza", "987.654.321-00", "Mariana123", 28,
                "mariana.souza@example.com");
        UserService.create("Carlos Alberto", "22.333.444/0002-90", "Carlos123",
                35, "carlos.alberto@example.com");
        UserService.create("Administrador", "123.567.890-11", "Admin123",
                20, "admin@example.com");
        Career Administrator = new Career("Administrator", true, true, true);
        CareerDAO careerDAO = new CareerDAO();
        try {
                careerDAO.save(Administrator);
        } catch (Exception e) {
                System.out.println(e);
        }
        EmployeeService.create("123.567.890-11", Administrator, 20500.0F);
    };
}
