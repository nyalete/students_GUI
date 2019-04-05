
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Student;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dbusse
 */
public class StudentDAOTest {
    private static Connection connection;
    private static Statement statement;
    
    public StudentDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        connection = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost/javabook?autoReconnect=true&useSSL=false", "scott", "tiger");
        statement = (Statement) connection.createStatement(); 
        }
        catch(Exception ex1){
            fail("setUpClass(): create connection failed with this exception: " +
                    ex1.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try{
            //
            // just in case the test row is still there
            statement.execute("delete from Student where stuId = " + TESTSTUDENTID + ";");
            connection.close();
        }
        catch(Exception ex){
            System.out.println("tearDown(): Exception returned: "+ex.getMessage());
        }
    }
    
    public static final String TESTSTUDENTID = "44499";
    public static void insertTestStudentRow(){
        try{
            statement.executeUpdate("insert into Student values("
                               + TESTSTUDENTID + ","
                               + "'Ben', "
                               + "'P', "
                               + "'Franklin', "
                               + "'2125551212', "
                               + "'1976-10-11', "
                               + "'123 Main St', "
                               + "'30099', "
                               + "'CS')");
        }
        catch(Exception ex){
        }
    }
    
    public static void deleteTestStudentRow(){
        try{
            statement.execute("delete from Student where stuId = '" + TESTSTUDENTID +"';");
        }
        catch(Exception ex){
            System.out.println("deleteTestStudentRow(): "+ex.getMessage());
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertStudent method, of class StudentDAO.
     */
    @Test
    public void testInsertStudent() {
        System.out.println("insertStudent");
        deleteTestStudentRow();
        Student aStudent = new Student(TESTSTUDENTID,"Ben",'P',"Franklin","2125551212","1976-10-11","123 Main St","30099","CS");
        StudentDAO instance = new StudentDAO();
        boolean expResult = true;
        boolean result = instance.insertStudent(aStudent);
        assertEquals(expResult, result);

        deleteTestStudentRow();
    }

    /**
     * Test of deleteStudent method, of class StudentDAO.
     */
    @Test
    public void testDeleteStudent() {
        System.out.println("deleteStudent");
        insertTestStudentRow();
        String stuId = TESTSTUDENTID;
        StudentDAO instance = new StudentDAO();
        boolean expResult = true;
        boolean result = instance.deleteStudent(stuId);
        assertEquals(expResult, result);

        //
        // Check to see if the row is gone
        int expResult2 = 0;
        int result2;
        try {
            //
            // Try to get a row that should not be there.
            // Result set should be empty.
            ResultSet resultSet = statement.executeQuery ("select * from Student where stuId = "+TESTSTUDENTID);
            result2 = resultSet.getRow();
        }
        catch(Exception ex){
            result2 = 0;
        }
        assertEquals(expResult2, result2);
        deleteTestStudentRow();
    }

    /**
     * Test of findStudent method, of class StudentDAO.
     */
    @Test
    public void testFindStudent() {
        System.out.println("findStudent");
        String stuId = "444111110";
        StudentDAO instance = new StudentDAO();
        String expResult = "Jacob";
        Student result = instance.findStudent(stuId);
        assertEquals(expResult, result.getFirstName());
    }

    /**
     * Test of updateStudent method, of class StudentDAO.
     */
    @Test
    public void testUpdateStudent() {
        insertTestStudentRow();
        System.out.println("updateStudent");
        Student aStudent = new Student(TESTSTUDENTID,"Foo",'P',"Franklin","2125551212","1976-10-11","123 Main St","30099","CS");
        StudentDAO instance = new StudentDAO();
        boolean expResult = true;
        boolean result = instance.updateStudent(aStudent);
        assertEquals(expResult, result);
        //
        // Check to be sure
        String result2 = null;
        String expResult2 = "Foo";
        try{
           ResultSet resultSet = statement.executeQuery ("select firstName from Student where stuId = 44499");
           resultSet.next();
           result2 = resultSet.getString("firstName");
        }
        catch (Exception ex){
            fail("Exception when attempting to read test data: " + ex.getMessage());
        }
        assertEquals(expResult2, result2);
        deleteTestStudentRow();
    }

    /**
     * Test of selectStudents method, of class StudentDAO.
     */
    @Test
    public void testSelectStudents() {
        System.out.println("selectStudents");
        String stuId = "44411111%";
        StudentDAO instance = new StudentDAO();
        ArrayList result = (ArrayList)instance.selectStudents(stuId);
        int expCount = 10;
        int count = result.size();
        assertEquals(expCount, count);
        //
        // Check one of the students
        Student aStudent = (Student)result.get(1);
        String result2 = aStudent.getLastName();
        String expResult2 = "Stevenson";
        assertEquals(expResult2, result2);
    }
    
}
