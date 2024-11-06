package Home;
import Controller.Controller;
import Interface.Window;
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Window w = new Window(controller);
        w.setVisible(true);
    }
}