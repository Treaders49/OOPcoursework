import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cas extends JFrame {

	private JPanel contentPane;
	private ArrayList<ArrayList<String>> usersArray = new ArrayList<ArrayList<String>>(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cas frame = new cas();
					frame.setTitle("Login");
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
	public cas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox(getNames());
		comboBox.setBounds(42, 56, 127, 20);
		contentPane.add(comboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = (String) comboBox.getSelectedItem();
				if (user.contains("admin")) {
					int x;
					for (x = 0; x < 4; x++) {
						if (usersArray.get(x).contains(user.substring(0,(user.indexOf(" -"))))){
							break;
						}
						
						
					}
					admin person = new admin(user.substring(0,(user.indexOf("-"))), usersArray.get(x).get(3), usersArray.get(x).get(4), usersArray.get(x).get(5));
				} else {
					int x;
					for (x = 0; x < 4; x++) {
						if (usersArray.get(x).contains(user.substring(0,(user.indexOf(" -"))))) {
							break;
						}
						
					}
					customer person = new customer(user.substring(0,(user.indexOf("-"))), usersArray.get(x).get(3), usersArray.get(x).get(4), usersArray.get(x).get(5));
				}
				
			}
		});
		btnLogin.setBounds(179, 55, 89, 23);
		contentPane.add(btnLogin);
	}

	private String[] getNames() {
		ArrayList<String> names = new ArrayList<String>();
		int fileLength = 0;
		try {
		      File f = new File("UserAccounts.txt");
		      Scanner myReader = new Scanner(f);
		      while (myReader.hasNextLine()) {
		        String row = myReader.nextLine();
		        String[] rowData = row.split(", ");
				usersArray.add(new ArrayList<String>(Arrays.asList(rowData)));
				System.out.println(usersArray);
		        names.add(rowData[2] + " - " + rowData[6]);
		        fileLength++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		String[] nameArray = new String[fileLength];
		for (int x = 0; x < fileLength; x++) { //converting dynamic arrayList to array which can be inserted into combo box
			nameArray[x] = names.get(x);
		}
		return nameArray;
	}
	
	
}

