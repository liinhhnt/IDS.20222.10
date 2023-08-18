package common.exception;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author ntlinh
 *
 */
public class NoResultException extends Exception {
	public NoResultException(String message) {
		super(message);
		JFrame Alert_Frame = new JFrame("Alert Window");
        Alert_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Alert_Frame.setSize(400, 200);
		JOptionPane.showMessageDialog(Alert_Frame, message);
	}
}