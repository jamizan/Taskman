package Excercise_2;

import java.util.*;

import javax.persistence.*;

@Entity
public class tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int TaskID;
	private int TeamID;
	private String Description;
	private String DueDate;
	private String Status;
	private String StartDate;
	private String EndDate;
	private String Comments;
	
	@ManyToOne
	@JoinColumn(name = "TeamID", nullable = false, insertable = false, updatable = false)
	private teams teams;
	
	public int getTaskID() {
		return TaskID;
	}

	public void setTaskID(int taskID) {
		TaskID = taskID;
	}

	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	@Override
	public String toString() {
		return "tasks [TaskID=" + TaskID + ", TeamID=" + TeamID + ", Description=" + Description + ", DueDate="
				+ DueDate + ", Status=" + Status + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", Comments="
				+ Comments + ", teams=" + teams + "]";
	}
	
	
}
