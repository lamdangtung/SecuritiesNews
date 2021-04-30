package application;

import java.io.IOException;

import javax.swing.SwingUtilities;

import frame.ControllerLogin;
import frame.LoginView;
import frame.SecuritiesNewsFrame;

public class Application {
	
	public static void main(String[] args) throws IOException {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LoginView loginFrame = new LoginView();
				ControllerLogin controller = new ControllerLogin(loginFrame);
			}
		});
	}
	
}
