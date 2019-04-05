/**
 * Class: CIST 2372 Java II
 * Quarter: Fall 2016
 * Instructor: Dave Busse
 * Description: Term Project
 * Date: <11/27/16>
 * @author Emmanuel Nyaletey
 * @version 8.1
 *
 * By turning in this code, I Pledge:
 *  1. That I have completed the programming assignment independently.
 *  2. I have not copied the code from a student or any source.
 *  3. I have not given my code to any student.
 *
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import Business.Student;
import Data.StudentDAO;


public class DisplayStudent extends JFrame {
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	
	//create table model
	private static Object[] columnNames = { "Student ID", "First Name", "Initial", "Last Name",  "Phone", "DOB", "Street", "ZIP", "DEPT" };
	private static Object[][] data;
	private static DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

	private static JTable jTable = new JTable(tableModel);

	private static JLabel labelStudentCount = new JLabel();

	private static final String NUM_OF_STUDENTS = "Number of Students = ";

	private static String search_id;

	public DisplayStudent() {
		// create menu items
		JMenuItem jmiExit;
		JMenuItem jmiSearch;
		JMenuItem jmiClear;
		JMenuItem jmiUpdate;
		JMenuItem jmiDelete;
		JMenuItem jmiInsert;
		JMenuItem jmiSave;
		
		// creates jmenubar and set it 
		JMenuBar jmenuBar = new JMenuBar();
		setJMenuBar(jmenuBar);

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		jmenuBar.add(fileMenu);
		jmenuBar.add(editMenu);

		fileMenu.add(jmiExit = new JMenuItem("Exit"));
		fileMenu.add(jmiSave = new JMenuItem("Save"));
		editMenu.add(jmiSearch = new JMenuItem("Search"));
		editMenu.add(jmiClear = new JMenuItem("Clear"));
		editMenu.add(jmiUpdate = new JMenuItem("Update"));
		editMenu.add(jmiDelete = new JMenuItem("Delete"));
		editMenu.add(jmiInsert = new JMenuItem("Insert"));

		// Set mnemonics
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		jmiExit.setMnemonic('x');
		jmiSearch.setMnemonic('S');
		jmiClear.setMnemonic('r');
		jmiUpdate.setMnemonic('u');
		jmiDelete.setMnemonic('d');
		jmiInsert.setMnemonic('i');
		jmiSave.setMnemonic('s');

		// listeners
		jmiExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		jmiSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				search_id = JOptionPane.showInputDialog(null, "Enter the number or number followed by %:", "Display Student",JOptionPane.QUESTION_MESSAGE);
					if (search_id==null)
						return;
					// search control prevents database hacking.
					Boolean searchControl = (search_id.contains("@") || search_id.contains("#") || search_id.contains("$") || search_id.contains("&") || search_id.contains("!"));

					if(search_id.contains("%") && !searchControl){
						ArrayList<Student> list = new ArrayList<Student>();
						list.addAll(new StudentDAO().selectStudents(search_id));
						labelStudentCount.setText("Number of Students = " + list.size());
						Iterator iterator = list.iterator();
						int count = 0;
						while(iterator.hasNext()){
							tableModel.addRow(new Object[] {
									list.get(count).getId(),
									list.get(count).getFirstName(),
									list.get(count).getMi(),
									list.get(count).getLastName(),
									list.get(count).getPhone(),
									list.get(count).getBirthDate(),
									list.get(count).getStreet(),
									list.get(count).getZip(),
									list.get(count).getDeptId(),
							});
									iterator.next();
									count++;
									}
						}

					 else if (!(search_id.contains("%") && ! searchControl)) {
					Student student = new StudentDAO().findStudent(search_id);
					if(!student.equals(null))
						labelStudentCount.setText("Number of Students = 1 ");
					
					tableModel.addRow(new Object[] {
							student.getId(),
							student.getFirstName(),
							student.getMi(),
							student.getLastName(),
							student.getPhone(),
							student.getBirthDate(),
							student.getStreet(),
							student.getZip(),
							student.getDeptId(),
							});
					}	
			}
		});
		// Clear button
		jmiClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				labelStudentCount.setText(NUM_OF_STUDENTS + 0);
			}
		});
		// Update button listener
		jmiUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search_id = JOptionPane.showInputDialog(null, "Enter the student ID number", "Display Student",JOptionPane.QUESTION_MESSAGE);
				if (search_id==null)
					return;
				// search control prevents database hacking.
				Boolean searchControl = (search_id.contains("@") || search_id.contains("#") || search_id.contains("$") || search_id.contains("&") || search_id.contains("!"));
				if(!(search_id.contains("%") && ! searchControl)){
					Student student = new StudentDAO().findStudent(search_id);
					if(!student.equals(null))
					labelStudentCount.setText("Number of Students = 1 ");		
					tableModel.addRow(new Object[] {
							student.getId(),
							student.getFirstName(),
							student.getMi(),
							student.getLastName(),
							student.getPhone(),
							student.getBirthDate(),
							student.getStreet(),
							student.getZip(),
							student.getDeptId(),
							});
					//new StudentDAO().updateStudent(student);
				}

			}
		});
		// Delete button listener 
		jmiDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search_id = JOptionPane.showInputDialog(null, "Enter the student ID number", "Display Student",JOptionPane.QUESTION_MESSAGE);
				if (search_id==null)
					return;
				// search control prevents database hacking.
				Boolean searchControl = (search_id.contains("@") || search_id.contains("#") || search_id.contains("$") || search_id.contains("&") || search_id.contains("!"));
				if(!(search_id.contains("%") && ! searchControl)){
					if(JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				  new StudentDAO().deleteStudent(search_id);
					}
				}

			}
		});
		// Insert button

		// Set default values in our labels
		labelStudentCount.setText(NUM_OF_STUDENTS + 0);

		// add panels to frame
		panel1.setLayout(new BorderLayout(5, 5));
		panel2.setLayout(new BorderLayout(5, 5));

		//add label to panel
		panel2.add(labelStudentCount, BorderLayout.EAST);
		panel2.setBorder(new LineBorder(Color.BLACK, 1));
		
		// add scrollpane to panel
		panel1.add(new JScrollPane(jTable), BorderLayout.CENTER);
		panel1.add(panel2, BorderLayout.SOUTH);

		add(panel1);
	}

	public static void main(String[] args) {
		DisplayStudent frame = new DisplayStudent();
		frame.pack();
		frame.setSize(900, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Display Student");
		frame.setVisible(true);
	}

}
