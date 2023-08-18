package common.exception;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
/**
 * 
 * @author Admin
 *
 */
public class InvalidSearchKeyException extends Exception {
	public InvalidSearchKeyException(String message) {
		super(message);
		JFrame Alert_Frame = new JFrame("Alert Window");
        Alert_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Alert_Frame.setSize(400, 200);
		JOptionPane.showMessageDialog(Alert_Frame, message);
	}
}