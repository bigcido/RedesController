package view;
import javax.swing.JOptionPane;
import controller.RedeController;


public class Main {

	public static void main(String[] args) {
		int opc;
		RedeController rc = new RedeController();
		
		String[] options = {"IP", "Ping", "Sair"};
        
        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha uma operação",
                "Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (escolha == 0) {
                rc.ip();
            } else if (escolha == 1) {
                rc.ping();
            } else if (escolha == 2) {
                JOptionPane.showMessageDialog(null, "Programa encerrado.");
                break;
            }
        }
    }
}