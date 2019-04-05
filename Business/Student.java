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
package Business;

public class Student {
	private String id;
	private String firstName;
	private char mi;
	private String lastName;
	private String phone;
	private String birthDate;
	private String street;
	private String zip;
	private String deptId;
	
	public Student(){
		
	}
	public Student(String stuId, String firstNam, char midName, String lastNam, String phoneNum, String birthDat, String streetAdd, String zipCode, String departId){
		id = stuId;
		firstName = firstNam;
		mi = midName;
		lastName = lastNam;
		phone = phoneNum;
		birthDate = birthDat;
		street = streetAdd;
		zip = zipCode;
		deptId = departId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public char getMi() {
		return mi;
	}
	public void setMi(char mi) {
		this.mi = mi;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}
