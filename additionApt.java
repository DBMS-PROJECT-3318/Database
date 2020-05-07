import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

public class additionApt extends JFrame {

	private JPanel contentPane;
	Connection conn;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					additionApt frame = new additionApt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public additionApt() {
		conn = SqliteConnect.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Add Apartment");
		title.setBounds(310, 0, 142, 40);
		contentPane.add(title);
		
		JLabel priceLabel = new JLabel("Price : ");
		priceLabel.setBounds(41, 49, 70, 15);
		contentPane.add(priceLabel);
		
		JLabel nameLabel = new JLabel("Apt. Name :");
		nameLabel.setBounds(41, 88, 93, 15);
		contentPane.add(nameLabel);
		
		JLabel addressLabel = new JLabel("Apt. Address :");
		addressLabel.setBounds(41, 128, 100, 15);
		contentPane.add(addressLabel);
		
		JLabel roomLabel = new JLabel("Rooms :");
		roomLabel.setBounds(41, 169, 70, 15);
		contentPane.add(roomLabel);
		
		JLabel floorLabel = new JLabel("Floor :");
		floorLabel.setBounds(41, 211, 70, 15);
		contentPane.add(floorLabel);
		
		JLabel petsLabel = new JLabel("Pets :");
		petsLabel.setBounds(41, 254, 70, 15);
		contentPane.add(petsLabel);
		
		textField = new JTextField();
		textField.setBounds(203, 47, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 86, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(203, 126, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JRadioButton room1Btn = new JRadioButton("1");
		room1Btn.setBounds(176, 165, 41, 23);
		contentPane.add(room1Btn);
		
		JRadioButton room2Btn = new JRadioButton("2");
		room2Btn.setBounds(221, 165, 46, 23);
		contentPane.add(room2Btn);
		
		JRadioButton room3Btn = new JRadioButton("3");
		room3Btn.setBounds(276, 165, 53, 23);
		contentPane.add(room3Btn);
		
		JRadioButton room4Btn = new JRadioButton("4");
		room4Btn.setBounds(333, 165, 53, 23);
		contentPane.add(room4Btn);
		
		ButtonGroup rooms = new ButtonGroup();
		rooms.add(room1Btn);
		rooms.add(room2Btn);
		rooms.add(room3Btn);
		rooms.add(room4Btn);
		
		
		JRadioButton floor1Btn = new JRadioButton("1");
		floor1Btn.setBounds(179, 207, 41, 23);
		contentPane.add(floor1Btn);
		
		JRadioButton floor2Btn = new JRadioButton("2");
		floor2Btn.setBounds(221, 207, 70, 23);
		contentPane.add(floor2Btn);
		
		ButtonGroup floors = new ButtonGroup();
		floors.add(floor1Btn);
		floors.add(floor2Btn);
		
		JRadioButton petABtn = new JRadioButton("Allowed");
		petABtn.setBounds(180, 250, 93, 23);
		contentPane.add(petABtn);
		
		JRadioButton petNABtn = new JRadioButton("Not Allowed");
		petNABtn.setBounds(290, 250, 149, 23);
		contentPane.add(petNABtn);
		
		ButtonGroup pets = new ButtonGroup();
		pets.add(petABtn);
		pets.add(petNABtn);
		
		JLabel warningLabel = new JLabel("EVERY FIELD MUST BE FILLED OUT");
		warningLabel.setBounds(434, 52, 251, 46);
		contentPane.add(warningLabel);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(200, 309, 117, 25);
		contentPane.add(confirmBtn);
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//do stuff later
				try {
					int roomss = 0;
					int floorss=0;
					int petss=0;
					String query = "Insert into Apartment (Price,AptName,AptAddress) Values(?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.execute();
					
					if(room1Btn.isSelected()) {
						roomss = 1;
					}
					else if(room2Btn.isSelected()) {
						roomss =2;
					}
					else if(room3Btn.isSelected()) {
						roomss =3;
					}
					else if(room4Btn.isSelected()) {
						roomss=4;
					}
					if(floor1Btn.isSelected()) {
						floorss=1;
					}
					else if(floor2Btn.isSelected()) {
						floorss=2;
					}
					if(petABtn.isSelected()) {
						petss=1;
					}
					else if(petNABtn.isSelected()) {
						petss=0;
					}
					String query2 = "Insert into AptCharacteristics (Floor,Rooms,Pets) Values(?,?,?)";
					PreparedStatement pst2 = conn.prepareStatement(query2);
					pst2.setInt(1,floorss);
					pst2.setInt(2, roomss);
					pst2.setInt(3, petss);
					pst2.execute();
					
					pst.close();
					pst2.close();
					
					JOptionPane.showMessageDialog(null,"Confirmed!");
					dispose();
					ManagerPage man  = new ManagerPage();
					man.main(null);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(383, 309, 117, 25);
		contentPane.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Going Back!");
				dispose();
				ManagerPage man  = new ManagerPage();
				man.main(null);
			}
		});
	}
}
