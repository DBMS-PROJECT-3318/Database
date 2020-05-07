import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class ManagerPage extends JFrame {

	private JPanel contentPane;
	Connection conn;
	private JTable table_1;
	JComboBox sortCBx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPage frame = new ManagerPage();
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
	public void fillCombo() {
		try {
			String pHL = "Price: High to Low";
			String pLH = "Price: Low to High";
			String rHL = "Rooms: High to Low";
			String rLH = "Rooms: Low to High";
			String fHL = "Floors: High to Low";
			String fLH = "Floors: Low to High";
			String petsAN = "Pets: Allowed to Not Allowed";
			String petsNA = "Pets: Not Allowed to Allowed";
			sortCBx.addItem(" ");
			sortCBx.addItem(pHL);
			sortCBx.addItem(pLH);
			sortCBx.addItem(rHL);
			sortCBx.addItem(rLH);
			sortCBx.addItem(fHL);
			sortCBx.addItem(fLH);
			sortCBx.addItem(petsAN);
			sortCBx.addItem(petsNA);
		}catch(Exception e) {;
			e.printStackTrace();
		}
	}
	
	
	public ManagerPage() {
		conn = SqliteConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(240, 248, 255));
	
		
		JLabel lblNewLabel_1 = new JLabel("PRICING");
		lblNewLabel_1.setBounds(50,68,100,15);
		lblNewLabel_1.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Minimum");
		lblNewLabel_2.setBounds(50,100,100,15);
		lblNewLabel_2.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinnerMin = new JSpinner();
		spinnerMin.setModel(new SpinnerNumberModel(0, 0, 1499, 10));
		spinnerMin.setBounds(50,115,100,25);
		contentPane.add(spinnerMin);
		
		try {
		    spinnerMin.commitEdit();
		} catch ( Exception e ) { 
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		JLabel lblNewLabel_3 = new JLabel("Maximum");
		lblNewLabel_3.setBounds(50,140,100,15);
		lblNewLabel_3.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(lblNewLabel_3);
		
		JSpinner spinnerMax = new JSpinner();
		spinnerMax.setModel(new SpinnerNumberModel(0, 0, 1500, 10));
		spinnerMax.setBounds(50,155,100,25);
		contentPane.add(spinnerMax);
		
		try {
		    spinnerMax.commitEdit();
		} catch ( Exception e ) { 
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		JLabel lblNewLabel_4 = new JLabel("Floors");
		lblNewLabel_4.setBounds(50,185,100,15);
		lblNewLabel_4.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(lblNewLabel_4);
		
		JCheckBox CheckBxFloor1 = new JCheckBox("One");
		CheckBxFloor1.setBackground(new Color(240, 248, 255));
		CheckBxFloor1.setBounds(50,205,100,15);
		contentPane.add(CheckBxFloor1);
		
		//
	
		
		JCheckBox CheckBxFloor2 = new JCheckBox("Two");
		CheckBxFloor2.setBackground(new Color(240, 248, 255));
		CheckBxFloor2.setBounds(50,225,100,15);
		contentPane.add(CheckBxFloor2);
		
		//
		
		
		JLabel lblNewLabel_5 = new JLabel("Rooms");
		lblNewLabel_5.setBounds(50,245,100,15);
		lblNewLabel_5.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(lblNewLabel_5);
		
		JCheckBox CBxRoom1 = new JCheckBox("1 Room");
		CBxRoom1.setBackground(new Color(240, 248, 255));
		CBxRoom1.setBounds(50,265,100,15);
		contentPane.add(CBxRoom1);
		
		//
		
		
		JCheckBox CBxRoom2 = new JCheckBox("2 Room");
		CBxRoom2.setBackground(new Color(240, 248, 255));
		CBxRoom2.setBounds(50,280,100,15);
		contentPane.add(CBxRoom2);
		
		//
		
		
		JCheckBox CBxRoom3 = new JCheckBox("3 Room");
		CBxRoom3.setBackground(new Color(240, 248, 255));
		CBxRoom3.setBounds(50,295,100,15);
		contentPane.add(CBxRoom3);
		
		//
		
		
		JCheckBox CBxRoom4 = new JCheckBox("4 Room");
		CBxRoom4.setBackground(new Color(240, 248, 255));
		CBxRoom4.setBounds(50,310,100,15);
		contentPane.add(CBxRoom4);
		
		//
		
		JLabel lblNewLabel_6 = new JLabel("Pets");
		lblNewLabel_6.setBounds(50,330,100,15);
		lblNewLabel_6.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(lblNewLabel_6);
		
		JCheckBox CbxPets = new JCheckBox("Allowed");
		CbxPets.setBackground(new Color(240, 248, 255));
		CbxPets.setBounds(50,345,170,15);
		contentPane.add(CbxPets);
		
		
		
		JLabel search = new JLabel("Search: ");
		search.setBounds(556,20,100,15);
		search.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.add(search);
		
		JTextField SearchField = new JTextField();
		SearchField.setBounds(627,20,147,15);
		contentPane.add(SearchField);
      
		
		JButton btnSearch = new JButton("Search :)");
		btnSearch.setBounds(786, 20, 100, 15);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String name = "";
					name = "%" + SearchField.getText() + "%";
					String queryy = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId where Apartment.AptName like ? ";
					PreparedStatement ps = conn.prepareStatement(queryy);
					ps.setString(1, name);
					ResultSet rt = ps.executeQuery();
					
					table_1.setModel(DbUtils.resultSetToTableModel(rt));
					ps.close();
				}catch(Exception f) {
					JOptionPane.showMessageDialog(null, f);
				}
			}
		});
		//

		
		JLabel AccLabel = new JLabel("Account : ");
		AccLabel.setBounds(47, 12, 70, 15);
		contentPane.add(AccLabel);
		
		String name = "";
		try {
			String query="SELECT UserName FROM User WHERE Logins = 1";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		JLabel CurrentUser = new JLabel(name);
		CurrentUser.setBounds(119, 12, 144, 15);
		contentPane.add(CurrentUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(237, 68, 613, 332);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("Results");
		lblNewLabel.setBounds(493, 41, 70, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String choice = (String) sortCBx.getSelectedItem();
					
					int floor1 =0;
					if(CheckBxFloor1.isSelected()){
						floor1 = 1;
					}
					int floor2 =0;
					if(CheckBxFloor2.isSelected()){
						floor2 = 2;
					}
					int room1= 0;
					if(CBxRoom1.isSelected()){
						room1 = 1;
					}
					int room2 =0;
					if(CBxRoom2.isSelected()){
						room2 = 2;
					}
					int room3 =0;
					if(CBxRoom3.isSelected()){
						room3 = 3;
					}
					int room4=0;
					if(CBxRoom4.isSelected()){
						room4 = 4;
					}
					int pets = -1;
					if(CbxPets.isSelected()){
						pets = 1;
					}
					
					
					int min = (int) spinnerMin.getValue();
					int max = (int) spinnerMax.getValue();
					String query1 = "Select AptId from AptCharacteristics Where ((Floor = ?) or (Floor = ?)) or ((Rooms = ?) or (Rooms = ?) or (Rooms =?) or (Rooms=?)) or (Pets = ?)";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setInt(1, floor1);
					pst1.setInt(2, floor2);
					pst1.setInt(3, room1);
					pst1.setInt(4, room2);
					pst1.setInt(5, room3);
					pst1.setInt(6, room4);
					pst1.setInt(7, pets);
					
					ResultSet rrr = pst1.executeQuery();
					int count = 0;
					int[] array = new int[5];
					while((rrr.next())&& (count < 5)) {
						array[count] = rrr.getInt(1);
						count = count +1;
					}
					pst1.close();
					rrr.close();
					
					if(choice == " ") {
						String query = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Price: High to Low") {
						String query3 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by Apartment.Price DESC";
						PreparedStatement pst = conn.prepareStatement(query3);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Price: Low to High") {
						String query4 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by Apartment.Price ASC";
						PreparedStatement pst = conn.prepareStatement(query4);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Rooms: High to Low") {
						String query5 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by AptCharacteristics.Rooms DESC";
						PreparedStatement pst = conn.prepareStatement(query5);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Rooms: Low to High") {
						String query6 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by AptCharacteristics.Rooms ASC";
						PreparedStatement pst = conn.prepareStatement(query6);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Floors: High to Low") {
						String query7 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by AptCharacteristics.Floors DESC";
						PreparedStatement pst = conn.prepareStatement(query7);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Floors: Low to High") {
						String query8 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by AptCharacteristics.Floors ASC";
						PreparedStatement pst = conn.prepareStatement(query8);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Pets: Allowed to Not Allowed") {
						String query9 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by AptCharacteristics.Pets DESC";
						PreparedStatement pst = conn.prepareStatement(query9);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}
					else if(choice == "Pets: Not Allowed to Allowed") {
						String query10 = "Select AptName,AptAddress,Price,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId = ? or ? or ? or ? or ?) or (Apartment.Price <= ? and Apartment.Price >= ?) Order by AptCharacteristics.Pets ASC";
						PreparedStatement pst = conn.prepareStatement(query10);
						pst.setInt(1, array[0]);
						pst.setInt(2, array[1]);
						pst.setInt(3, array[2]);
						pst.setInt(4, array[3]);
						pst.setInt(5, array[4]);
						pst.setInt(6, max);
						pst.setInt(7, min);
						ResultSet rs = pst.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						rs.close();
						pst.close();
					}

				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton.setBounds(50, 393, 117, 25);
		contentPane.add(btnNewButton);
		
		sortCBx = new JComboBox();
		sortCBx.setBounds(324, 15, 219, 24);
		contentPane.add(sortCBx);
		
		JLabel sortLabel = new JLabel("Sort By");
		sortLabel.setBounds(259, 20, 70, 15);
		contentPane.add(sortLabel);
		
		JButton addApt = new JButton("Add Apt");
		addApt.setBounds(344, 412, 117, 25);
		contentPane.add(addApt);
		//
		addApt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Adding Apt!");
				dispose();
				additionApt aApt = new additionApt();
				aApt.setVisible(true);
			}
		});
		
		JButton deleteApt = new JButton("Delete Apt");
		deleteApt.setBounds(511, 412, 117, 25);
		contentPane.add(deleteApt);
		//
		deleteApt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Deleting Apt!");
				dispose();
				deletionApt dApt = new deletionApt();
				dApt.setVisible(true);
			}
		});
		
		JButton modifyApt = new JButton("Modify Apt");
		modifyApt.setBounds(699, 412, 117, 25);
		contentPane.add(modifyApt);
		//
		modifyApt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Modifying Apt!");
				dispose();
				modifyingApt mApt = new modifyingApt();
				mApt.setVisible(true);
			}
		});
		
		fillCombo();
		
	}
}