import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaGui1 {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField tktprice;
	private JTable table;
	private JTextField tktbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaGui1 window = new JavaGui1();
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
	public JavaGui1() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javagui", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)

        {
            ex.printStackTrace();
        }
 
    }
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Uniform Bold", Font.BOLD, 37));
		lblNewLabel.setBounds(320, 10, 206, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 82, 426, 209);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setBounds(24, 33, 106, 37);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setBounds(24, 88, 106, 37);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setBounds(24, 152, 106, 37);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(119, 36, 241, 31);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setBounds(119, 93, 241, 31);
		txtedition.setColumns(10);
		panel.add(txtedition);
		
		tktprice = new JTextField();
		tktprice.setBounds(119, 152, 241, 31);
		tktprice.setColumns(10);
		panel.add(tktprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = tktprice.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name,edition,price) values (?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added!!!!");
					table_load();
					// table_load();
					txtbname.setText("");
					txtedition.setText("");
					tktprice.setText("");
					txtbname.requestFocus();
						}catch(SQLException e1){
							e1.printStackTrace();
						}
			}
			});
		
		
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(32, 304, 127, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.ORANGE);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnExit.setBounds(180, 301, 127, 53);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtedition.setText("");
				tktprice.setText("");
				txtbname.requestFocus();
			}
		});
		btnClear.setBackground(Color.ORANGE);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnClear.setBounds(321, 301, 127, 53);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(462, 81, 339, 270);
		frame.getContentPane().add(scrollPane_1);
		
		JScrollPane tableauData = new JScrollPane();
		scrollPane_1.setViewportView(tableauData);
		
		table = new JTable();
		tableauData.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(22, 375, 426, 94);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book Id");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(35, 26, 106, 37);
		panel_1.add(lblNewLabel_1_2);
		
		tktbid = new JTextField();
		tktbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = tktbid.getText();
					pst = con.prepareStatement("select name,edition,price from book where id=?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true) {
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						tktprice.setText(price);
					}
					else {
						txtbname.setText("");
						txtedition.setText("");
						tktprice.setText("");
					}
				} catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		tktbid.setColumns(10);
		tktbid.setBounds(101, 29, 270, 31);
		panel_1.add(tktbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname ,edition ,price,bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = tktprice.getText();
				bid = tktbid.getText();
				
				try {
					pst = con.prepareStatement("update book set name=? ,edition=?,price=? where id =?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!");
					table_load();
					// table_load();
					txtbname.setText("");
					txtedition.setText("");
					tktprice.setText("");
					txtbname.requestFocus();
						}catch(SQLException e1){
							e1.printStackTrace();
						}
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.setBounds(495, 389, 127, 53);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_1_1 = new JButton("Delete");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
		
				bid = tktbid.getText();
				
				try {
					pst = con.prepareStatement("delete from book where id=?");
					pst.setString(1, bid);					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted!!!!");
					table_load();
					// table_load();
					txtbname.setText("");
					txtedition.setText("");
					tktprice.setText("");
					txtbname.requestFocus();
						}catch(SQLException e1){
							e1.printStackTrace();
						}
				
			}
		});
		btnClear_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnClear_1_1.setBackground(Color.ORANGE);
		btnClear_1_1.setBounds(650, 389, 127, 53);
		frame.getContentPane().add(btnClear_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("\r\n");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\pc\\Downloads\\2.jpg"));
		lblNewLabel_2.setBounds(0, 0, 811, 490);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
