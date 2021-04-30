package frame;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

public class LoginView extends JFrame {
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	
    JButton login;
	
    private static JTextField userNameField;
	private static JPasswordField passwordField;	
	
	private static final String img = "Image/drone-footage-of-a-beach-3556117.jpg";
	public LoginView() {
		
		setTitle("Login");
		
		//set BackGround
		ImageIcon backGround = new ImageIcon(img);
		JPanel jpanel = new JPanel() {
			@Override
				protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (backGround != null) {
				g.drawImage(backGround.getImage(), 0, 0, this);
			}
		}
	};
	setContentPane(jpanel);
		// tao cac phim chuc nang
		userNameLabel = new JLabel("UserName");
		passwordLabel = new JLabel("PassWord");
		login = new JButton("LOGIN");
		userNameField = new JTextField();
		passwordField = new JPasswordField();

		//set cac khung cua login
		userNameLabel.setBounds(50, 100, 80, 35);
		userNameLabel.setFont(new Font("Serif", Font.BOLD, 16));
		passwordLabel.setBounds(50, 140,80,35);	
		passwordLabel.setFont(new Font("Serif", Font.BOLD, 16));
		userNameField.setBounds(140, 100, 165, 35);
		userNameField.setFont(new Font("Serif", Font.BOLD, 15));
		passwordField.setBounds(140, 140, 165, 35);	
		passwordField.setFont(new Font("Serif", Font.BOLD, 15));
		login.setBounds(160, 200, 80, 35);

		//add doi tuong chua cac thanh phan cua login
		getContentPane().setBackground(Color.gray);
		add(userNameLabel);
		add(passwordLabel);		
		add(userNameField);
		add(passwordField);
		add(login);	
		
		//thiet lap bo cuc cuar Login

		setLayout(null);
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	    setVisible(true);

	}
	
	public static void main(String[] args) {
		LoginView sc = new LoginView();
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		LoginView.passwordField = passwordField;
	}

	public static JTextField getUserNameField() {
		return userNameField;
	}

	public static void setUserNameField(JTextField userNameField) {
		LoginView.userNameField = userNameField;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	
	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

}
