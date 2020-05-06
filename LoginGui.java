import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginGui {

	private JFrame frame;
	
	Connection conn = null;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui window = new LoginGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillCombo() {
		try {
			String u = "User";
			String m = "Manager";
			comboBox.addItem(u);
			comboBox.addItem(m);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public LoginGui() {
		initialize();
		//conn = SqliteConnect.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = SqliteConnect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(200, 200, 500, 300);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(325, 162, 115, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String choice = (String) comboBox.getSelectedItem(); 
					String query="SELECT * FROM User WHERE UserName = ? and UserPass = ?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2,textField_1.getText());
					String man = "Select Manager From User Where Username = ?";
					PreparedStatement pp=conn.prepareStatement(man);
					pp.setString(1, textField.getText());
					ResultSet cc = pp.executeQuery();
					int m = 0;
					while(cc.next()) {
						m = cc.getInt(1);
					}
					
					ResultSet rs=pst.executeQuery();
					int count =0;
					while(rs.next()) {
						count = count + 1;
					}
					if(count ==1) {
						if(choice == "User") {
							JOptionPane.showMessageDialog(null, "Username and password are correct");	
							JOptionPane.showMessageDialog(null, "LOGGING IN ...");
							String query2 = "UPDATE User SET Logins = 1 WHERE UserName = ?";
							PreparedStatement pst2 = conn.prepareStatement(query2);
							pst2.setString(1, textField.getText());
							pst2.execute();
							String query3 = "UPDATE User SET Logins = 0 WHERE UserName <> ?";
							PreparedStatement pst3 = conn.prepareStatement(query3);
							pst3.setString(1, textField.getText());
							pst3.execute();
							frame.dispose();
							AptBasePage page = new AptBasePage();
							page.setVisible(true);
						}
						else if((choice == "Manager") & (m == 1)) {
							JOptionPane.showMessageDialog(null, "Username and password are correct");	
							JOptionPane.showMessageDialog(null, "LOGGING IN ...");
							String query2 = "UPDATE User SET Logins = 1 WHERE UserName = ?";
							PreparedStatement pst2 = conn.prepareStatement(query2);
							pst2.setString(1, textField.getText());
							pst2.execute();
							String query3 = "UPDATE User SET Logins = 0 WHERE UserName <> ?";
							PreparedStatement pst3 = conn.prepareStatement(query3);
							pst3.setString(1, textField.getText());
							pst3.execute();
							frame.dispose();
							ManagerPage page2 = new ManagerPage();
							page2.setVisible(true);
						}
							
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Username and/or password");
					}
					rs.close();
					pst.close();
									
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnLogin);
		
		textField = new JTextField();
		textField.setBounds(167, 80, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 120, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setBounds(68, 80, 68, 15);
		txtrUsername.setEditable(false);
		txtrUsername.setOpaque(false);
		txtrUsername.setText("Username ");
		frame.getContentPane().add(txtrUsername);
		
		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setBounds(76, 122, 60, 15);
		txtrPassword.setEditable(false);
		txtrPassword.setOpaque(false);
		txtrPassword.setText("Password");
		frame.getContentPane().add(txtrPassword);
		
		JTextArea txtrLogin = new JTextArea();
		txtrLogin.setBounds(133, 28, 160, 20);
		txtrLogin.setEditable(false);
		txtrLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtrLogin.setOpaque(false);
		txtrLogin.setText("Apartment Finder Login");
		frame.getContentPane().add(txtrLogin);
		
		JButton btnCreateUser = new JButton("Create New User");
		btnCreateUser.setBounds(286, 193, 154, 25);
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Creating a new account!");
				frame.dispose();
				CreateUser cUser = new CreateUser();
				cUser.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnCreateUser);
		
		comboBox = new JComboBox();
		comboBox.setBounds(340, 63, 100, 24);
		frame.getContentPane().add(comboBox);
		
		fillCombo();
		
	}
}
