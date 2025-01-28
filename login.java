package Excercise_2;

import java.util.*;

import javax.persistence.*;

@Entity
public class login{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LoginID")
    private int LoginID;

	@Column(name = "EmployeeID")	
	private int EmployeeID;
	
    @Column(name = "Admin")
    private int Admin;

    @Column(name = "UserName")
    private String UserName;

    @Column(name = "PassWord")
    private String PassWord;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeID", unique = true, nullable = false, insertable = false, updatable = false)
    private employees employee;

	public int getLoginID() {
		return LoginID;
	}

	public void setLoginID(int loginID) {
		LoginID = loginID;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public int isAdmin() {
		return Admin;
	}

	public void setAdmin(int admin2) {
		Admin = admin2;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String string) {
		PassWord = string;
	}

	public employees getEmployee() {
		return employee;
	}

	public void setEmployee(employees employee) {
		this.employee = employee;
	}
	
	

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("login [LoginID=");
		stringBuilder.append(LoginID);
		stringBuilder.append(", EmployeeID=");
		stringBuilder.append(EmployeeID);
		stringBuilder.append(", Admin=");
		stringBuilder.append(Admin);
		stringBuilder.append(", UserName=");
		stringBuilder.append(UserName);
		stringBuilder.append(", PassWord=");
		stringBuilder.append(PassWord);
		stringBuilder.append(", employee=");
		stringBuilder.append(employee);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
    
}
