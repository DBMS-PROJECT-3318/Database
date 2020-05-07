import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class modifyingApt extends JFrame {

	private JPanel contentPane;
	Connection conn;
	JComboBox comboBox;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public void fillCombo() {
		try {
			int u = 0;
			comboBox.addItem(u);
			String query = "Select AptId from Apartment";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				comboBox.addItem(rs.getInt(1));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyingApt frame = new modifyingApt();
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
	public modifyingApt() {
		conn = SqliteConnect.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 46, 515, 269);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel Title = new JLabel("Modification");
		Title.setBounds(288, 0, 137, 15);
		contentPane.add(Title);
		
		JLabel tableTitle = new JLabel("ALL AVAILABLE APARTMENTS");
		tableTitle.setBounds(369, 25, 226, 15);
		contentPane.add(tableTitle);
		try {
			String query = "Select Apartment.AptId,AptName,AptAddress,Price,Rooms,Floor,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
			
		JLabel newLabel = new JLabel("Select AptId to Modify");
		newLabel.setBounds(22, 47, 183, 15);
		contentPane.add(newLabel);
			
		comboBox = new JComboBox();
		comboBox.setBounds(32, 74, 95, 24);
		contentPane.add(comboBox);
			
	
		JLabel priceLabel = new JLabel("New Price :");
		priceLabel.setBounds(22, 110, 183, 15);
		contentPane.add(priceLabel);
		
		JLabel nameLabel = new JLabel("New Name :");
		nameLabel.setBounds(22, 164, 183, 15);
		contentPane.add(nameLabel);
		
		textField = new JTextField();
		textField.setBounds(22, 133, 183, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 181, 183, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel addressLabel = new JLabel("New Address :");
		addressLabel.setBounds(22, 214, 173, 15);
		contentPane.add(addressLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(22, 240, 183, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel roomLable = new JLabel("New Rooms :");
		roomLable.setBounds(22, 272, 100, 15);
		contentPane.add(roomLable);
		
		JRadioButton room1Btn = new JRadioButton("1");
		room1Btn.setBounds(129, 268, 38, 23);
		contentPane.add(room1Btn);
		
		JRadioButton room2Btn = new JRadioButton("2");
		room2Btn.setBounds(177, 268, 38, 23);
		contentPane.add(room2Btn);
		
		JRadioButton room3Btn = new JRadioButton("3");
		room3Btn.setToolTipText("");
		room3Btn.setBounds(129, 295, 38, 23);
		contentPane.add(room3Btn);
		
		JRadioButton room4Btn = new JRadioButton("4");
		room4Btn.setBounds(177, 295, 38, 23);
		contentPane.add(room4Btn);
		
		ButtonGroup rooms = new ButtonGroup();
		rooms.add(room1Btn);
		rooms.add(room2Btn);
		rooms.add(room3Btn);
		rooms.add(room4Btn);
		
		JLabel floorLable = new JLabel("New Floor :");
		floorLable.setBounds(22, 327, 95, 15);
		contentPane.add(floorLable);
		
		JRadioButton floor1Btn = new JRadioButton("1");
		floor1Btn.setBounds(117, 328, 38, 23);
		contentPane.add(floor1Btn);
		
		JRadioButton floor2Btn = new JRadioButton("2");
		floor2Btn.setBounds(167, 328, 38, 23);
		contentPane.add(floor2Btn);
		
		ButtonGroup floors = new ButtonGroup();
		floors.add(floor1Btn);
		floors.add(floor2Btn);
		
		JLabel petLable = new JLabel("New Pets :");
		petLable.setBounds(22, 361, 95, 15);
		contentPane.add(petLable);
		
		JRadioButton petsABtn = new JRadioButton("Allowed");
		petsABtn.setBounds(127, 357, 81, 23);
		contentPane.add(petsABtn);
		
		JRadioButton petsNABtn = new JRadioButton("Not Allowed");
		petsNABtn.setBounds(129, 384, 117, 23);
		contentPane.add(petsNABtn);
		
		ButtonGroup pets = new ButtonGroup();
		pets.add(petsABtn);
		pets.add(petsNABtn);
		
		JButton modifyBtn = new JButton("Modify");
		modifyBtn.setBounds(324, 369, 117, 25);
		contentPane.add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int space = (int) comboBox.getSelectedItem();
					if(space == 0) {
						JOptionPane.showMessageDialog(null, "INVALID CHOICE TRY AGAIN");
					}
					else {
						int id = (int) comboBox.getSelectedItem();
						int roomss = 0;
						int floorss=0;
						int petss=0;
						String query = "Update Apartment set Price = ?, AptName = ?, AptAddress = ? where (AptId = ?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, textField.getText());
						pst.setString(2, textField_1.getText());
						pst.setString(3, textField_2.getText());
						pst.setInt(4, id);
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
						if(petsABtn.isSelected()) {
							petss=1;
						}
						else if(petsNABtn.isSelected()) {
							petss=0;
						}
						String query2 = "Update AptCharacteristics set Floor = ?, Rooms = ?, Pets = ? where (AptId = ?)";
						PreparedStatement pst2 = conn.prepareStatement(query2);
						pst2.setInt(1, floorss);
						pst2.setInt(2, roomss);
						pst2.setInt(3, petss);
						pst2.setInt(4, id);
						pst2.execute();
						
						pst.close();
						pst2.close();
						
						JOptionPane.showMessageDialog(null, "Modification Confirmed! Returning to Manager Page!");
						dispose();
						ManagerPage man2 = new ManagerPage();
						man2.main(null);
						
					}}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				
			}
				
		});
			
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(503, 369, 117, 25);
		contentPane.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Going Back!");
				dispose();
				ManagerPage man = new ManagerPage();
				man.main(null);
			}
		});
		
		
		
		
		
		fillCombo();
	}

}
