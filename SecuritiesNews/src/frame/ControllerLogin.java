package frame;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControllerLogin implements ActionListener  {
	private LoginView view ;
	SecuritiesNewsFrame frame;
	
	public ControllerLogin(LoginView view) {
		this.view = view;
		view.getLogin().addActionListener(this);
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		} );
	}
	
	public boolean checkUserName() {
		try {
			if ("".equals(view.getUserNameField().getText()) == false) {
				if (view.getUserNameField().getText().trim().equals("admin") == false) {
					JOptionPane.showMessageDialog(view, "UserName is not Correct!");
					return false;
				}
				else {
					return true;
				}
			}			
			else {
				JOptionPane.showMessageDialog(view, "UserName khong duoc de trong");
			}
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "UserName khong hop le");
		}
		return false;
	}
	
	public boolean checkPassword() {		
		try {		
			if ("".equals(view.getPasswordField().getText()) == false) {
				if (view.getPasswordField().getText().equals("admin") == false) {										
					JOptionPane.showMessageDialog(view, "Password is not Correct!");
					return false;
				}
				else {					
					return true;
				}
			}				
			else {
				JOptionPane.showMessageDialog(view, "Password khong duoc de trong");
			}
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Password khong hop le");
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame = new SecuritiesNewsFrame();
		if (e.getSource() == view.getLogin()) {
			if (checkUserName() == true && checkPassword() == true) {			
				view.setVisible(false);
				frame.setVisible(true);
			}
		}
	}
}
