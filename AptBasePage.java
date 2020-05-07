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



public class AptBasePage extends JFrame {

	private JPanel contentPane;
	Connection conn;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AptBasePage frame = new AptBasePage();
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
	public AptBasePage() {
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
		
		JTextField textField = new JTextField();
		textField.setBounds(627,20,147,15);
		contentPane.add(textField);
      
		
		JButton btnSearch = new JButton("Search :)");
		btnSearch.setBounds(786, 20, 100, 15);
		contentPane.add(btnSearch);

		
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
					
					String query = "Select AptName,AptAddress,Floor,Rooms,Pets From Apartment join AptCharacteristics on Apartment.AptId = AptCharacteristics.AptId Where (AptCharacteristics.AptId) = ? or ? or ? or ? or ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setInt(1, array[0]);
					pst.setInt(2, array[1]);
					pst.setInt(3, array[2]);
					pst.setInt(4, array[3]);
					pst.setInt(5, array[4]);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton.setBounds(50, 393, 117, 25);
		contentPane.add(btnNewButton);
		
		
	}
}