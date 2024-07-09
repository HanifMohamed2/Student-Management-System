package StudentManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class StudentDetails {

	private JFrame frame;
	private JTextField textID;
	private JTextField textName;
	private JTextField textDOB;
	private JTextField textGender1;
	private JTextField textBloodgroup1;
	private JTextField textContactNo1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetails window = new StudentDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentDetails() {
		initialize(); 
		Connect();
		clear();
		loadData();
	}

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public void Connect() {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con=DriverManager.getConnection
	                    ("jdbc:mysql://localhost:3306/details","root", "Hanif123.");
	        }catch(Exception e2) {

	        }
	}

	private void clear() {
		
		textID.setText("");
	    textName.setText("");
	    textDOB.setText("");
	    textGender1.setText("");
	    textBloodgroup1.setText("");
	    textContactNo1.setText("");
		
	}
	
	
		public void loadData() {
		    try {
		      ps = con.prepareStatement("Select * from member_details");
		      rs = ps.executeQuery();
		      table.setModel(DbUtils.resultSetToTableModel(rs));

		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		  }
		
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(95, 158, 160));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STUDENT DETAILS");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 67));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 10, 1021, 105);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("STUDENT ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(30, 134, 114, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("STUDENT NAME");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(30, 186, 128, 42);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("STUDENT DOB");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(30, 238, 114, 42);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		textID = new JTextField();
		textID.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textID.setBounds(219, 143, 226, 30);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		
		textName = new JTextField();
		textName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textName.setColumns(10);
		textName.setBounds(219, 195, 226, 30);
		frame.getContentPane().add(textName);
		
		textDOB = new JTextField();
		textDOB.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textDOB.setColumns(10);
		textDOB.setBounds(219, 247, 226, 30);
		frame.getContentPane().add(textDOB);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textID.getText();
		        if (id.isEmpty())
		          JOptionPane.showMessageDialog(null, "Select First");
		        else {
		          String name = textName.getText();
		          String dob = textDOB.getText();
		          String gender = textGender1.getText();
		          String blood_group = textBloodgroup1.getText();
		          String contact_no = textContactNo1.getText();
		          
		          
		          
		          try {
		            String 
		            sql ="Update member_details set ID=?,NAME=?,DOB=?,GENDER=?,BLOOD_GROUP=?,CONTACT_NO=?where ID=?";
		            ps = con.prepareStatement(sql);
		            ps.setString(1, id);
		            ps.setString(2, name); 
		            ps.setString(3, dob);
		            ps.setString(4, gender);
		            ps.setString(5, blood_group);
		            ps.setString(6, contact_no);
		            ps.setString(7, id);
		            ps.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Update Success");
		            clear();
		            loadData();
		          }

		          catch (Exception e1) {
		            e1.printStackTrace();
		          }
		        }

			}
		});
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		btnNewButton.setBounds(168, 478, 140, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textID.getText();
		        if (id.isEmpty()) {
		          JOptionPane.showMessageDialog(null, "Select First");
		        } else {
		          try {
		            String sql = "Delete from member_details where ID=?";
		            ps = con.prepareStatement(sql);
		            ps.setString(1, id);
		            ps.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Delete Success");
		            clear();
		            loadData();

		          } catch (SQLException e1) {
		            e1.printStackTrace();
		          }
		        }


			}
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		btnDelete.setBounds(318, 478, 127, 36);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton_2 = new JButton("CREATE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String id = textID.getText();
				        if (id.isEmpty())
				          JOptionPane.showMessageDialog(null, "Enter Student Details");
				        else {
				          String name = textName.getText();
				          String dob = textDOB.getText();
				          String gender = textGender1.getText();
				          String blood_group = textBloodgroup1.getText();
				          String contact_no = textContactNo1.getText();
				          
				          try {
				            String sql =
				                "insert into member_details (ID,NAME,DOB,GENDER,BLOOD_GROUP,CONTACT_NO) values (?,?,?,?,?,?)";
				            ps = con.prepareStatement(sql);
				            ps.setString(1, id);
				            ps.setString(2, name);
				            ps.setString(3, dob);
				            ps.setString(4, gender);
				            ps.setString(5, blood_group);
				            ps.setString(6, contact_no);
				            ps.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Create Success");
				            clear();
				            loadData();
				        
				          } catch (SQLException e1) {
				            e1.printStackTrace();
				          }
				        }
			}

			

		});
		btnNewButton_2.setBackground(new Color(0, 255, 0));
		btnNewButton_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		btnNewButton_2.setBounds(30, 478, 128, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(493, 125, 522, 389);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textGender1 = new JTextField();
		textGender1.setText("");
		textGender1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textGender1.setColumns(10);
		textGender1.setBounds(219, 301, 226, 30);
		frame.getContentPane().add(textGender1);
		
		textBloodgroup1 = new JTextField();
		textBloodgroup1.setText("");
		textBloodgroup1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textBloodgroup1.setColumns(10);
		textBloodgroup1.setBounds(219, 356, 226, 30);
		frame.getContentPane().add(textBloodgroup1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("GENDER");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(30, 292, 114, 42);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("BLOOD GROUP");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_2.setBounds(30, 347, 114, 42);
		frame.getContentPane().add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("CONTACT NO");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_3.setBounds(30, 399, 114, 42);
		frame.getContentPane().add(lblNewLabel_1_2_3);
		
		textContactNo1 = new JTextField();
		textContactNo1.setText("");
		textContactNo1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textContactNo1.setColumns(10);
		textContactNo1.setBounds(219, 408, 226, 30);
		frame.getContentPane().add(textContactNo1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
		        TableModel model = table.getModel();

		        textID.setText(model.getValueAt(index, 0).toString());
		        textName.setText(model.getValueAt(index, 1).toString());
		        textDOB.setText(model.getValueAt(index, 2).toString());
		        textGender1.setText(model.getValueAt(index, 3).toString());
		        textBloodgroup1.setText(model.getValueAt(index, 4).toString());
		        textContactNo1.setText(model.getValueAt(index, 5).toString());
		        
			}
		});
	
	}
}
