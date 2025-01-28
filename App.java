package Excercise_2;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.util.*;

public class App{
	static void addNewTeam(String loc, String desc) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	
    	em.getTransaction().begin();
    	teams t = new teams();
    	
    	t.setLocation(loc);
    	t.setDescription(desc);
    	
    	em.merge(t);
    	em.getTransaction().commit();
    	em.close();
    	emf.close();
	}

	static void addNewEmployee(int id, String FirstName, String LastName, String PhoneNumber) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
		
    	employees e = new employees();
    	e.setTeamID(id);
    	e.setFirstName(FirstName);
    	e.setLastName(LastName);
    	e.setPhoneNumber(PhoneNumber);
    	
    	em.merge(e);
    	em.getTransaction().commit();
    	em.close();
    	emf.close();
	}
	
	private static void findEmployeeById(int EmployeeID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	
    	employees e = em.find(employees.class, EmployeeID);
    	
    	if (e != null) {
    		System.out.println(e.toString());
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
    	em.getTransaction().commit();
		em.close();
    	emf.close();
	}
	
	private static void findTeamById(int TeamID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	
    	teams t = em.find(teams.class, TeamID);
    	
    	if (t != null) {
    		System.out.println(t.toString());
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
    	em.getTransaction().commit();
		em.close();
    	emf.close();
	}
	
	static void updateEmployee(int EmployeeID, int TeamID, String FirstName, String LastName, String PhoneNumber) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	
    	employees emp = em.find(employees.class, EmployeeID);
    	
    	if (emp != null) {
    		em.getTransaction().begin();
    		System.out.println(emp.toString() + " BEFORE updated");
    		emp.setTeamID(TeamID);
    		emp.setFirstName(FirstName);
    		emp.setLastName(LastName);
    		emp.setPhoneNumber(PhoneNumber);
    		emp = em.merge(emp);
    		em.getTransaction().commit();
    		System.out.println(emp.toString() + " has been updated");
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
		em.close();
    	emf.close();
	}
	
	static void updateTeam(int TeamID, String Description, String Location) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	
    	teams t = em.find(teams.class, TeamID);
    	
    	if (t != null) {
    		em.getTransaction().begin();
    		System.out.println(t.toString() + " BEFORE updated");
    		t.setDescription(Description);
    		t.setLocation(Location);
    		t = em.merge(t);
    		em.getTransaction().commit();
    		System.out.println(t.toString() + " has been updated");
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
		em.close();
    	emf.close();
	}
	
	static void deleteEmployee(int EmployeeID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
		
    	employees emp = em.find(employees.class, EmployeeID);
    	
    	em.getTransaction().begin();
    	em.remove(emp);
    	em.getTransaction().commit();

    	em.close();
    	emf.close();
	}
	
	static void deleteTeam(int TeamID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
		
    	teams team = em.find(teams.class, TeamID);
    	
    	em.getTransaction().begin();
    	em.remove(team);
    	em.getTransaction().commit();

    	em.close();
    	emf.close();
	}

	private static void findTaskByID(int TaskID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	
    	tasks task = em.find(tasks.class, TaskID);
    	
    	if (task != null) {
    		System.out.println(task.toString());
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
    	em.getTransaction().commit();
		em.close();
    	emf.close();
		
	}
	
	static void addNewTask(int TeamID, String Description, String DueDate, String Status, String StartDate, String EndDate, String Comments){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
		
    	tasks task = new tasks();
    	task.setTeamID(TeamID);
    	task.setDescription(Description);
    	task.setDueDate(DueDate);
    	task.setStatus(Status);
    	task.setStartDate(StartDate);
    	task.setEndDate(EndDate);
    	task.setComments(Comments);
    	
    	em.merge(task);
    	em.getTransaction().commit();
    	em.close();
    	emf.close();
	}
	
	static void updateTask(int TaskID, int TeamID, String Description, String DueDate, String Status, String StartDate, String EndDate, String Comments) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	
    	tasks task = em.find(tasks.class, TaskID);
    	
    	if (task != null) {
    		em.getTransaction().begin();
    		
    		System.out.println(task.toString() + " BEFORE updated");
        	task.setTeamID(TeamID);
        	task.setDescription(Description);
        	task.setDueDate(DueDate);
        	task.setStatus(Status);
        	task.setStartDate(StartDate);
        	task.setEndDate(EndDate);
        	task.setComments(Comments);
    		
    		task = em.merge(task);
    		em.getTransaction().commit();
    		System.out.println(task.toString() + " has been updated");
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
		em.close();
    	emf.close();
	}
	
	static void deleteTask(int TaskID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
		
    	tasks task = em.find(tasks.class, TaskID);
    	
    	em.getTransaction().begin();
    	em.remove(task);
    	em.getTransaction().commit();

    	em.close();
    	emf.close();
	}
	
	private static void addNewLogin(int EmployeeID, int admin, String UserName, String string) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
		
    	login login = new login();
    	login.setEmployeeID(EmployeeID);
    	login.setAdmin(admin);
    	login.setUserName(UserName);
    	login.setPassWord(string);
    	
    	em.merge(login);
    	em.getTransaction().commit();
    	em.close();
    	emf.close();
	}
	
	private static void findLoginByID(int LoginID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	
    	login login = em.find(login.class, LoginID);
    	
    	if (login != null) {
    		System.out.println(login.toString());
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
    	em.getTransaction().commit();
		em.close();
    	emf.close();
	}
	
	private static void updateLogin(int LoginID, int Admin, String UserName, String PassWord) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
    	
    	login login= em.find(login.class, LoginID);
    	if (login != null) {
    		em.getTransaction().begin();
    		
    		System.out.println(login.toString() + " BEFORE updated");
        	login.setAdmin(Admin);
        	login.setUserName(UserName);
        	login.setPassWord(PassWord);
    		
    		login = em.merge(login);
    		em.getTransaction().commit();
    		System.out.println(login.toString() + " has been updated");
    	}
    	else {
    		System.out.println("This entry does not exist");
    	}
		em.close();
    	emf.close();
	}
	
	private static void deleteLogin(int LoginID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    	EntityManager em = emf.createEntityManager();
		
    	login login = em.find(login.class, LoginID);
    	
    	em.getTransaction().begin();
    	em.remove(login);
    	em.getTransaction().commit();

    	em.close();
    	emf.close();
	}

    public static login findLoginByUsername(String userName, char[] passWord) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        TypedQuery<login> query = entityManager.createQuery("SELECT l FROM login l WHERE l.UserName = :userName AND l.PassWord = :passWord", login.class);
        query.setParameter("userName", userName);
        query.setParameter("passWord", passWord);

        List<login> logins = query.getResultList();
        
        
        System.out.println(logins);
        entityManager.close();
        entityManagerFactory.close();

        return logins.isEmpty() ? null : logins.get(0);
    
      
    }

    public static boolean checkTeamExists(int TeamID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM teams t WHERE t.TeamID = :TeamID", Long.class);
        query.setParameter("TeamID", TeamID);
        Long count = query.getSingleResult();

        em.close();
        emf.close();


        return count > 0;
    }

    public static boolean checkTaskExists(int TaskID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM tasks t WHERE t.TaskID = :TaskID", Long.class);
        query.setParameter("TaskID", TaskID);
        Long count = query.getSingleResult();

        em.close();
        emf.close();
        

        return count > 0;
    }

    public static boolean checkEmployeeExists(int EmployeeID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Long> query = em.createQuery("SELECT COUNT(e) FROM employees e WHERE e.EmployeeID = :EmployeeID", Long.class);
        query.setParameter("EmployeeID", EmployeeID);
        Long count = query.getSingleResult();

        em.close();
        emf.close();

        return count > 0;
    }
    
	public static void main( String[] args ) throws Exception {
		
		
		Scanner scanner = new Scanner(System.in);
        System.out.println("Terminal GUI Menu:");
        System.out.println("1. Start TaskMan");
        System.out.println("2. Modify TaskMan users");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.println("Starting TaskMan...");
                VisualCreate.main(args);
                break;
            case 2:
                System.out.println("Modify TaskMan users");
                System.out.println();
        		Scanner scanner2 = new Scanner(System.in);
                System.out.println("TaskMan menu:");
                System.out.println("1. Add new user");
                System.out.println("2. Modify existing user");
                System.out.println("3. Delete existing user");
                System.out.println("4. Exit");
                System.out.println("Enter your choice: ");
                int choice2 = scanner2.nextInt();
                
                switch (choice2) {
                case 1:
                    System.out.println("Input EmployeeID (int): ");
                    int EmployeeId = scanner2.nextInt();
                    scanner2.nextLine();
                    System.out.println("Input Admin (1 = true, 0 = false): ");
                    int admin = scanner2.nextInt();
                    scanner2.nextLine();
                    System.out.println("Input Username: ");
                    String Username = scanner2.nextLine();
                    scanner2.nextLine();
                    System.out.println("Input Password: ");
                    String Password = scanner2.nextLine();
                    scanner2.nextLine();
                    
                    addNewLogin(EmployeeId, admin, Username, Password);
                    break;
                    
                case 2:
                    System.out.println("Modify existing user.");
                    System.out.println("Input LoginID (int): ");
                    int LoginId = scanner2.nextInt();
                    
                    
                    System.out.println("Input Admin (1 = true, 0 = false): ");
                    int admin2 = scanner2.nextInt();
                    scanner2.nextLine();
                    
                    System.out.println("Input Username: ");
                    String Username2 = scanner2.nextLine();
                    scanner2.nextLine();
                    
                    System.out.println("Input Password: ");
                    String Password2 = scanner2.nextLine();
                    scanner2.nextLine();
                    
                    updateLogin(LoginId, admin2, Username2, Password2);
                    break;
                    
                case 3:
                    System.out.println("Delete existing user.");
                    System.out.println("Input LoginID (int): ");
                    int LoginId2 = scanner2.nextInt();
                    
                    deleteLogin(LoginId2);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
                break;
            case 3:
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                break;
        }
    }
}




