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
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Business.Student;

public class StudentDAO extends Student {
	private String url = "jdbc:mysql://localhost/javabook?autoReconnect=true&useSSL=false";

	public StudentDAO() {
		
	}
	
	// delete a row from the Student table.
		public boolean deleteStudent(String stuId) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection(url, "scott", "tiger");
				PreparedStatement preparedStatement = conn.prepareStatement("DELETE from student where stuId =?");
				preparedStatement.setString(1, stuId);
				preparedStatement.executeUpdate();
				conn.close();

				return true;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}
		
		// find one particular student in the Student table in the database.
		 public Student findStudent(String stuId){
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				java.sql.Connection conn = DriverManager.getConnection(url, "scott", "tiger");
				System.out.println("Connected");
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from student where stuId =?");
				preparedStatement.setString(1, stuId);
				preparedStatement.executeQuery();
				
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					String id = resultSet.getString("stuId");
					String firstName =resultSet.getString("firstName");
	                char ini = resultSet.getString("mi").charAt(0);
	                String lastName = resultSet.getString("lastName");
	                String phone = resultSet.getString("phone");
	                String birthdate = resultSet.getString("birthDate");
	                String street = resultSet.getString("street");
	                String zip = resultSet.getString("zipCode");
	                String deptId = resultSet.getString("deptId");
	                
	                return new Student(id, firstName, ini, lastName, phone, birthdate, street, zip, deptId);
	               
					}
				
				  conn.close();
				 
				  
				} catch (Exception ex) {
						    ex.printStackTrace();
				}
			
				return null;
			}

	// insert the fields of a student object into the database creating a new row in the Student table.
public boolean insertStudent(Student aStudent) {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			java.sql.Connection conn = DriverManager.getConnection(url, "scott", "tiger");
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT into student (stuId, firstName, mi, lastName, phone, "
							+ "birthdate, street, zipCode, deptId) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, aStudent.getDeptId());
			preparedStatement.setString(2, aStudent.getFirstName());
			preparedStatement.setString(3, String.valueOf(aStudent.getMi()));
			preparedStatement.setString(4, aStudent.getLastName());
			preparedStatement.setString(5, aStudent.getPhone());
			preparedStatement.setString(6, aStudent.getBirthDate());
			preparedStatement.setString(7, aStudent.getStreet());
			preparedStatement.setString(8, aStudent.getZip());
			preparedStatement.setString(9, aStudent.getDeptId()); 

			preparedStatement.executeUpdate();

			conn.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	} 


	// get a number of Student records back from the database in the form of a Collection of Student objects.
	public Collection selectStudents(String stuId){
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			java.sql.Connection conn = DriverManager.getConnection(url, "scott", "tiger");
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from student where stuId like '" + stuId +  "' ");
				preparedStatement.executeQuery();
				
				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<Student>arrayList = new ArrayList<Student>();
				while(resultSet.next()){
					arrayList.add(new Student(resultSet.getString("stuId"), resultSet.getString("firstName"), resultSet.getString("mi").charAt(0), resultSet.getString("lastName"), 
					resultSet.getString("phone"), resultSet.getString("birthDate"), resultSet.getString("street"), resultSet.getString("zipCode"), resultSet.getString("deptId")));
				}
				  conn.close();
				  
				  return arrayList;
				} catch (Exception ex) {
						    ex.printStackTrace();
				}
				return null;
			} 
			
	// update an existing Student row in the Student table in the database.
	public boolean updateStudent(Student aStudent) {
	
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			java.sql.Connection conn = DriverManager.getConnection(url, "scott", "tiger");
			PreparedStatement preparedStatement = conn.prepareStatement("UPDATE student SET firstName =?, mi=?, lastName=?, phone=?, "
							+ "birthDate=?, street=?, zipCode=?, deptId=? WHERE stuId =" + aStudent.getId());
			
			preparedStatement.setString(1, aStudent.getFirstName());
			preparedStatement.setString(2, String.valueOf(aStudent.getMi()));
			preparedStatement.setString(3, aStudent.getLastName());
			preparedStatement.setString(4, aStudent.getPhone());
			preparedStatement.setString(5, aStudent.getBirthDate());
			preparedStatement.setString(6, aStudent.getStreet());
			preparedStatement.setString(7, aStudent.getZip());
			preparedStatement.setString(8, aStudent.getDeptId()); 

			preparedStatement.executeUpdate();
			
			conn.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
