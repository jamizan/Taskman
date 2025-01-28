package Excercise_2;

import java.util.*;
import javax.persistence.*;

@Entity
public class teams {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int TeamID;
	 private String Description;
	 private String Location;
	 
	 @OneToMany(mappedBy="teams")
	 private List<employees> employees;
	
	 public int getTeamID() {
	 return TeamID;
	 }
	 public void setTeamID(int TeamID) {
	 this.TeamID = TeamID;
	 }
	 public String getDescription() {
	 return Description;
	 }
	 public void setDescription(String Description) {
	 this.Description = Description;
	 }
	 public String getLocation() {
	 return Location;
	 }
	 public void setLocation(String Location) {
	 this.Location = Location;
	 }
	 public List<employees> getEmployees() {
		 return employees;
	 }
	 
	 public void setEmployee(List<employees> employee) {
		 this.employees = employee;
	 }
	 
	@Override
	public String toString() {
		return "teams [TeamID=" + TeamID + ", Description=" + Description + ", Location=" + Location + ", employees="
				+ employees + "]";
	}

}
