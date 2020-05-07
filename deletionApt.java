import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class deletionApt extends JFrame {

	private JPanel contentPane;
	Connection conn;
	private JTable table;
	JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deletionApt frame = new deletionApt();
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
	
	public deletionApt() {
		conn = SqliteConnect.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 46, 515, 269);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel Title = new JLabel("Deletion");
		Title.setBounds(288, 0, 70, 15);
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
			
		JLabel newLabel = new JLabel("Select AptId to Delete");
		newLabel.setBounds(22, 47, 183, 15);
		contentPane.add(newLabel);
			
		comboBox = new JComboBox();
		comboBox.setBounds(32, 74, 95, 24);
		contentPane.add(comboBox);
			
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(32, 141, 117, 25);
		contentPane.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int space = (int) comboBox.getSelectedItem();
					if(space == 0) {
						JOptionPane.showMessageDialog(null, "INVALID CHOICE TRY AGAIN");
					}
					else {
						int id = (int) comboBox.getSelectedItem();
						String query2 = "Delete From Apartment where AptId = ?";
						String query3 = "Delete From AptCharacteristics where AptId = ?";
						PreparedStatement pst2 = conn.prepareStatement(query2);
						PreparedStatement pst3 = conn.prepareStatement(query3);
						pst2.setInt(1, id);
						pst3.setInt(1, id);
						pst2.execute();
						pst3.execute();
						pst2.close();
						pst3.close();
						
						JOptionPane.showMessageDialog(null, "Deletion Confirmed! Returning to Manager Page!");
						dispose();
						ManagerPage man2 = new ManagerPage();
						man2.main(null);
						
					}}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				
			}
				
		});
			
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(32, 200, 117, 25);
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
