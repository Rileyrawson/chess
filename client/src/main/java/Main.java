import ui.Input;
import ui.PostloginUI;
import ui.PreloginUI;

public class Main {
    public static void main(String[] args) {
        try {
            Input.input();
//            PostloginUI.drawBoard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}