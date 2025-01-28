package Excercise_2;

import javax.persistence.*;

@Entity
public class employees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int EmployeeID;
	private int TeamID;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;

	@ManyToOne
	@JoinColumn(name = "TeamID", nullable = false, insertable = false, updatable = false) teams teams;
	
	@OneToOne(mappedBy = "employee")
	private login login;
	
	public teams getTeam() {
		return teams;
	}

	public void setTeam(teams teams) {
		this.teams = teams;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int EmployeeID) {
		this.EmployeeID = EmployeeID;
	}

	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int TeamID) {
		this.TeamID = TeamID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber;
	}

	@Override
	public String toString() {
		return "Employees [EmployeeID=" + EmployeeID + ", TeamID=" + TeamID + ", firstName=" + FirstName + ", LastName="
				+ LastName + ", PhoneNumber=" + PhoneNumber + "]";
	}
}
