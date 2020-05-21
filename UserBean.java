package beans;

import java.sql.Timestamp;

public class UserBean {

	private int studentid=0;
	private String studentname=null;
	private String studentemail=null;
	private String studentmobilenumber=null;
	private String interestedin=null;
	private String goal=null;
	private Timestamp creationdatetime = null;
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentemail() {
		return studentemail;
	}
	public void setStudentemail(String studentemail) {
		this.studentemail = studentemail;
	}
	public String getStudentmobilenumber() {
		return studentmobilenumber;
	}
	public void setStudentmobilenumber(String studentmobilenumber) {
		this.studentmobilenumber = studentmobilenumber;
	}
	public String getInterestedin() {
		return interestedin;
	}
	public void setInterestedin(String interestedin) {
		this.interestedin = interestedin;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public Timestamp getCreationdatetime() {
		return creationdatetime;
	}
	public void setCreationdatetime(Timestamp creationdatetime) {
		this.creationdatetime = creationdatetime;
	}
	
	private String Qualification=null;
	private String Lastname=null;
	private String College =null;
	public String getQualification() {
		return Qualification;
	}
	public void setQualification(String qualification) {
		Qualification = qualification;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getCollege() {
		return College;
	}
	public void setCollege(String college) {
		College = college;
	}
	
}
