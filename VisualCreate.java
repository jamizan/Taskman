package Excercise_2;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.*;

// This java file is used for creating a visual GUI for the program.
// It has different methods for creating different visual applications
// These methods are called from buttons that are in this visual programs main screen (CreateAdmin())
// These methods are designed to be accessible only for admin users and this program is made just for administrator personnel


public class VisualCreate extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected static final int Updatefield1IntValue = 0;

	// Creates the first login screen
	
	public static void CreateLogin() {

		JPanel loginPanel = new JPanel();
		final JFrame frameObj = new JFrame();
		frameObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel.setLayout(null);
		loginPanel.setPreferredSize(new Dimension(500,300));
		
		final JTextField username = new JTextField();
		final JPasswordField password = new JPasswordField();
		JButton submit = new JButton("Login");
		JLabel Title = new JLabel("TaskMan");
		
		username.setColumns(20);
		username.setBounds(150,110,200,25);
		
		password.setColumns(20);
		password.setBounds(150,140,200,25);
		
		submit.setBounds(150,170,70,20);
		
		Title.setBounds(150,10,200,100);
		Title.setFont(new Font("Verdana", Font.BOLD, 40));
		
		
        loginPanel.add(submit);
        loginPanel.add(username);
        loginPanel.add(password);
        loginPanel.add(Title);

        frameObj.add(loginPanel);
        frameObj.pack();
        frameObj.setVisible(true);
        frameObj.setLocationRelativeTo(null);
		
		submit.addActionListener(new java.awt.event.ActionListener() {
			private int adminStatus;
			
			// Searches for matching passwords and usernames from database
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = username.getText();
				char[] passWord = password.getPassword();
				
		        login login = App.findLoginByUsername(userName, passWord);
		        if (login != null) {
		            System.out.println("Login found with the provided username and password.");
		            System.out.println("Is Admin: " + login.isAdmin()); // Assuming isAdmin() method exists in Login class
		            adminStatus = login.isAdmin();
		            if(adminStatus == 1) {
		            	frameObj.dispose();
		            	CreateAdmin();
		            }
		        } else {
		            System.out.println("No login found with the provided username and password.");
		        }
		    }
		});
        
	}
	
	// Creates page for users that have the admin rights
	
	public static void CreateAdmin() {
		JPanel selectionPanel = new JPanel();
		JFrame frameObj = new JFrame();
		frameObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		selectionPanel.setLayout(null);
		selectionPanel.setPreferredSize(new Dimension(600,500));
		
		JLabel Title = new JLabel("TaskMan");
		Title.setBounds(200,10,200,100);
		Title.setFont(new Font("Verdana", Font.BOLD, 40));
		
		JButton B1 = new JButton("Team Overview");
		B1.setBounds(50,140,200,100);
		
		JButton B2 = new JButton("Employee Overview");
		B2.setBounds(350,140,200,100);
		
		JButton B3 = new JButton("Task Overview");
		B3.setBounds(50,280,200,100);
		
		JButton B4 = new JButton("Intranet");
		B4.setBounds(350,280,200,100);
		
		selectionPanel.add(Title);
		selectionPanel.add(B1);
		selectionPanel.add(B2);
		selectionPanel.add(B3);
		selectionPanel.add(B4);
		
		frameObj.add(selectionPanel);
		frameObj.pack();
        frameObj.setVisible(true);
        frameObj.setLocationRelativeTo(null);
        
    B1.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        		CreateTeamsOverview();
        	}
	});
    B2.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        		CreateEmployeeOverview();
        	}
	});
    
    B3.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        		CreateTaskOverview();
        	}
	});
    
    B4.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        		CreateIntraOverview();
        	}
	});
    
	}
	

	// Creates the teams overview window 
	
    public static void CreateTeamsOverview() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<teams> query = em.createQuery("SELECT t FROM teams t", teams.class);
        java.util.List<teams> dataList = query.getResultList();


        String[] columnNames = {"TeamID", "Description", "Location"};
        Object[][] data = new Object[dataList.size()][columnNames.length];
        for (int i = 0; i < dataList.size(); i++) {
            teams teams = dataList.get(i);
            data[i][0] = teams.getTeamID(); 
            data[i][1] = teams.getDescription(); 
            data[i][2] = teams.getLocation(); 
        }

        final JFrame frame = new JFrame("Data from Database");

        JTable table = new JTable(data, columnNames);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        final JComboBox<String> combo = new JComboBox<>();

        combo.addItem("Add new team");
        combo.addItem("Change information");
        combo.addItem("Delete team");

        combo.setSelectedIndex(0);
        combo.setBounds(140,150,150,50);


        final JTextField textField1 = new JTextField(10);
        final JTextField textField2 = new JTextField(10);

        JLabel label1 = new JLabel("Location:");
        JLabel label2 = new JLabel("Description:");

        JButton submitButton = new JButton("Submit");

        final JPanel addNewPanel = new JPanel(new BorderLayout());
        addNewPanel.setPreferredSize(new Dimension(600, 50)); 
        JPanel addNewFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        addNewFieldsPanel.add(label1);
        addNewFieldsPanel.add(textField1);
        addNewFieldsPanel.add(label2);
        addNewFieldsPanel.add(textField2);
        addNewFieldsPanel.add(submitButton);
        addNewPanel.add(addNewFieldsPanel, BorderLayout.CENTER);

        

        final JTextField UpdateField1 = new JTextField(10);
        final JTextField UpdateField2 = new JTextField(10);
        final JTextField UpdateField3 = new JTextField(10);

        JLabel Updatelabel1 = new JLabel("TeamID:");
        JLabel Updatelabel2 = new JLabel("Description:");
        JLabel Updatelabel3 = new JLabel("Location:");
        
        final JPanel UpdatePanel = new JPanel(new BorderLayout());
        UpdatePanel.setPreferredSize(new Dimension(600, 50));
        JPanel UpdateNewFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton UpdatesubmitButton = new JButton("Submit");
        
        UpdateNewFieldsPanel.add(Updatelabel1);
        UpdateNewFieldsPanel.add(UpdateField1);
        UpdateNewFieldsPanel.add(Updatelabel2);
        UpdateNewFieldsPanel.add(UpdateField2);
        UpdateNewFieldsPanel.add(Updatelabel3);
        UpdateNewFieldsPanel.add(UpdateField3);
        UpdateNewFieldsPanel.add(UpdatesubmitButton);
        UpdatePanel.add(UpdateNewFieldsPanel, BorderLayout.CENTER);

        
        

        JButton deleteButton = new JButton("Delete");

        final JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deletePanel.setPreferredSize(new Dimension(600, 50));

        final JTextField deleteField = new JTextField(20);
        JLabel deleteLabel = new JLabel("TeamID to be deleted:");

        deletePanel.add(deleteLabel);
        deletePanel.add(deleteField);
        deletePanel.add(deleteButton);
        
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(600, 150));

        

        frame.add(combo, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String field1Value = textField1.getText();
                String field2Value = textField2.getText();
                App.addNewTeam(field1Value, field2Value);
                
                showMessageDialog(null, "New team has been created!");
                frame.dispose();
                CreateTeamsOverview();
               
                }
            }
        );
        

        UpdatesubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                    int Updatefield1IntValue = Integer.parseInt(UpdateField1.getText());
                    
                    String Updatefield2Value = UpdateField2.getText();
                    String Updatefield3Value = UpdateField3.getText();
                	App.updateTeam(Updatefield1IntValue, Updatefield2Value, Updatefield3Value);
                	
                    if(App.checkTeamExists(Updatefield1IntValue) == true) {
                    	App.updateTeam(Updatefield1IntValue, Updatefield2Value, Updatefield3Value);
                        showMessageDialog(null, "Team has been modified!");
                        frame.dispose();
                        CreateTeamsOverview();
                    }
                    else {
                    	showMessageDialog(null, "This TeamID is not in use");
                    }
                	
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
			}
        });
        


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int Deletefield1IntValue = Integer.parseInt(deleteField.getText());
                	App.deleteTeam(Deletefield1IntValue);
                	
                    showMessageDialog(null, "Team info has been deleted!");
                    frame.dispose();
                    CreateTeamsOverview();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        });


        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) combo.getSelectedItem();
                if (selectedOption.equals("Add new team")) {
                    bottomPanel.add(addNewPanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(true);
                    UpdatePanel.setVisible(false);
                    deletePanel.setVisible(false);
                } else if (selectedOption.equals("Change information")) {

                    bottomPanel.add(UpdatePanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(false);
                    UpdatePanel.setVisible(true);
                    deletePanel.setVisible(false);
                } else {

                    bottomPanel.add(deletePanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(false);
                    UpdatePanel.setVisible(false);
                    deletePanel.setVisible(true);
                }
            }
        });
        em.close();
        emf.close();
    }

    // Creates employees overview window
    
    public static void CreateEmployeeOverview() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();

        
        TypedQuery<employees> query = em.createQuery("SELECT e FROM employees e JOIN e.teams t", employees.class);
        java.util.List<employees> dataList = query.getResultList();

        String[] columnNames = {"EmployeeID", "TeamID", "FirstName","LastName", "PhoneNumber"};
        Object[][] data = new Object[dataList.size()][columnNames.length];
        for (int i = 0; i < dataList.size(); i++) {
            employees employees = dataList.get(i);
            data[i][0] = employees.getEmployeeID();
            data[i][1] = employees.getTeamID();
            data[i][2] = employees.getFirstName();
            data[i][3] = employees.getLastName(); 
            data[i][4] = employees.getPhoneNumber(); 
        }

        final JFrame frame = new JFrame("Data from Database");

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        final JComboBox<String> combo = new JComboBox<>();

        combo.addItem("Add new employee");
        combo.addItem("Change information");
        combo.addItem("Delete employee");

        combo.setSelectedIndex(0);
        combo.setBounds(140,150,150,50);

        
        final JTextField textField1 = new JTextField(2);
        final JTextField textField2 = new JTextField(8);
        final JTextField textField3 = new JTextField(8);
        final JTextField textField4 = new JTextField(8);


        JLabel label1 = new JLabel("TeamID:");
        JLabel label2 = new JLabel("Firstname:");
        JLabel label3 = new JLabel("Lastname:");
        JLabel label4 = new JLabel("Phonenumber:");

        JButton submitButton = new JButton("Submit");

        final JPanel addNewPanel = new JPanel(new BorderLayout());
        addNewPanel.setPreferredSize(new Dimension(600, 50));
        JPanel addNewFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addNewFieldsPanel.add(label1);
        addNewFieldsPanel.add(textField1);
        addNewFieldsPanel.add(label2);
        addNewFieldsPanel.add(textField2);

        addNewFieldsPanel.add(label3);
        addNewFieldsPanel.add(textField3);
        addNewFieldsPanel.add(label4);
        addNewFieldsPanel.add(textField4);
        addNewFieldsPanel.add(submitButton);
        addNewPanel.add(addNewFieldsPanel, BorderLayout.CENTER);

        

        final JTextField UpdateField1 = new JTextField(2);
        final JTextField UpdateField2 = new JTextField(2);
        final JTextField UpdateField3 = new JTextField(8);
        final JTextField UpdateField4 = new JTextField(8);
        final JTextField UpdateField5 = new JTextField(8);

        JLabel Updatelabel1 = new JLabel("EmployeeID:");
        JLabel Updatelabel2 = new JLabel("TeamID:");
        JLabel Updatelabel3 = new JLabel("Firstname:");
        JLabel Updatelabel4 = new JLabel("Lastname:");
        JLabel Updatelabel5 = new JLabel("Phonenumber:");
        
        final JPanel UpdatePanel = new JPanel(new BorderLayout());
        UpdatePanel.setPreferredSize(new Dimension(600, 50));
        JPanel UpdateNewFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton UpdatesubmitButton = new JButton("Submit");
        
        UpdateNewFieldsPanel.add(Updatelabel1);
        UpdateNewFieldsPanel.add(UpdateField1);
        UpdateNewFieldsPanel.add(Updatelabel2);
        UpdateNewFieldsPanel.add(UpdateField2);
        UpdateNewFieldsPanel.add(Updatelabel3);
        UpdateNewFieldsPanel.add(UpdateField3);
        UpdateNewFieldsPanel.add(Updatelabel4);
        UpdateNewFieldsPanel.add(UpdateField4);
        UpdateNewFieldsPanel.add(Updatelabel5);
        UpdateNewFieldsPanel.add(UpdateField5);
        UpdateNewFieldsPanel.add(UpdatesubmitButton);
        UpdatePanel.add(UpdateNewFieldsPanel, BorderLayout.CENTER);

        
        

        JButton deleteButton = new JButton("Delete");

        final JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deletePanel.setPreferredSize(new Dimension(600, 50));

        final JTextField deleteField = new JTextField(20);
        JLabel deleteLabel = new JLabel("EmployeeID to be deleted:");

        deletePanel.add(deleteLabel);
        deletePanel.add(deleteField);
        deletePanel.add(deleteButton);
        

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(600, 150)); 


        frame.add(combo, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int field1IntValue = Integer.parseInt(textField1.getText());
                    String field2Value = textField2.getText();
                    String field3Value = textField3.getText();
                    String field4Value = textField4.getText();
                    
                    
                    if(App.checkTeamExists(field1IntValue) == true) {
                    	App.addNewEmployee(field1IntValue, field2Value, field3Value, field4Value);
                        showMessageDialog(null, "New employee has been added!");
                        frame.dispose();
                        CreateEmployeeOverview();
                    }
                    else {
                    	showMessageDialog(null, "This TeamID is not in use");
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
                }
            }
        );
        UpdatesubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                    int Updatefield1IntValue = Integer.parseInt(UpdateField1.getText());
                    int Updatefield2IntValue = Integer.parseInt(UpdateField2.getText());
                    
                    String Updatefield2Value = UpdateField3.getText();
                    String Updatefield3Value = UpdateField4.getText();
                    String Updatefield4Value = UpdateField5.getText();
                    
                    if(App.checkTeamExists(Updatefield2IntValue) == true && App.checkEmployeeExists(Updatefield1IntValue)) {
                    	App.updateEmployee(Updatefield1IntValue, Updatefield2IntValue, Updatefield2Value, Updatefield3Value, Updatefield4Value);
                        showMessageDialog(null, "Employee information has been changed!");
                        frame.dispose();
                        CreateEmployeeOverview();
                    }
                    else {
                    	showMessageDialog(null, "EmployeeID or TeamID is not in use");
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
			}
        });
        

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int Deletefield1IntValue = Integer.parseInt(deleteField.getText());
                	App.deleteEmployee(Deletefield1IntValue);
                	
                    showMessageDialog(null, "Employee information has been deleted!");
                    frame.dispose();
                    CreateEmployeeOverview();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        });

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) combo.getSelectedItem();
                if (selectedOption.equals("Add new employee")) {

                    bottomPanel.add(addNewPanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(true);
                    UpdatePanel.setVisible(false);
                    deletePanel.setVisible(false);
                } else if (selectedOption.equals("Change information")) {

                    bottomPanel.add(UpdatePanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(false);
                    UpdatePanel.setVisible(true);
                    deletePanel.setVisible(false);
                } else {

                    bottomPanel.add(deletePanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(false);
                    UpdatePanel.setVisible(false);
                    deletePanel.setVisible(true);
                }
            }
        });

        em.close();
        emf.close();
    }

    // Creates tasks overview window
    
	public static void CreateTaskOverview() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<tasks> query = em.createQuery("SELECT t FROM tasks t JOIN t.teams teams", tasks.class);
        java.util.List<tasks> dataList = query.getResultList();


        String[] columnNames = {"TaskID", "TeamID", "Description","DueDate", "Status", "StartDate", "EndDate", "Comments"}; // Replace with your column names
        Object[][] data = new Object[dataList.size()][columnNames.length];
        for (int i = 0; i < dataList.size(); i++) {
            tasks tasks = dataList.get(i);
            data[i][0] = tasks.getTaskID(); 
            data[i][1] = tasks.getTeamID(); 
            data[i][2] = tasks.getDescription();
            data[i][3] = tasks.getDueDate(); 
            data[i][4] = tasks.getStatus();
            data[i][5] = tasks.getStartDate();
            data[i][6] = tasks.getEndDate();
            data[i][7] = tasks.getComments();

        }

        final JFrame frame = new JFrame("Data from Database");

        JTable table = new JTable(data, columnNames);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));


        final JComboBox<String> combo = new JComboBox<>();

        combo.addItem("Add new task");
        combo.addItem("Change task information");
        combo.addItem("Delete task");

        combo.setSelectedIndex(0);
        combo.setBounds(140,150,150,50);

        
        

        final JTextField textField1 = new JTextField(2);
        final JTextField textField2 = new JTextField(8);
        final JTextField textField3 = new JTextField(8);
        final JTextField textField4 = new JTextField(8);
        final JTextField textField5 = new JTextField(8);
        final JTextField textField6 = new JTextField(8);
        final JTextField textField7 = new JTextField(8);


        JLabel label1 = new JLabel("TeamID:");
        JLabel label2 = new JLabel("Description:");
        JLabel label3 = new JLabel("DueDate:");
        JLabel label4 = new JLabel("Status:");
        JLabel label5 = new JLabel("StartDate:");
        JLabel label6 = new JLabel("EndDate:");
        JLabel label7 = new JLabel("Comments:");

        JButton submitButton = new JButton("Submit");

        final JPanel addNewPanel = new JPanel(new BorderLayout());
        addNewPanel.setPreferredSize(new Dimension(600, 50));
        JPanel addNewFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addNewFieldsPanel.add(label1);
        addNewFieldsPanel.add(textField1);
        addNewFieldsPanel.add(label2);
        addNewFieldsPanel.add(textField2);
        addNewFieldsPanel.add(label3);
        addNewFieldsPanel.add(textField3);
        addNewFieldsPanel.add(label4);
        addNewFieldsPanel.add(textField4);
        addNewFieldsPanel.add(label5);
        addNewFieldsPanel.add(textField5);
        addNewFieldsPanel.add(label6);
        addNewFieldsPanel.add(textField6);
        addNewFieldsPanel.add(label7);
        addNewFieldsPanel.add(textField7);
        addNewFieldsPanel.add(submitButton);
        addNewPanel.add(addNewFieldsPanel, BorderLayout.CENTER);

        

        final JTextField UpdateField1 = new JTextField(2);
        final JTextField UpdateField2 = new JTextField(2);
        final JTextField UpdateField3 = new JTextField(8);
        final JTextField UpdateField4 = new JTextField(8);
        final JTextField UpdateField5 = new JTextField(8);
        final JTextField UpdateField6 = new JTextField(8);
        final JTextField UpdateField7 = new JTextField(8);
        final JTextField UpdateField8 = new JTextField(8);

        JLabel Updatelabel1 = new JLabel("TaskID:");
        JLabel Updatelabel2 = new JLabel("TeamID:");
        JLabel Updatelabel3 = new JLabel("Description:");
        JLabel Updatelabel4 = new JLabel("DueDate:");
        JLabel Updatelabel5 = new JLabel("Status:");
        JLabel Updatelabel6 = new JLabel("StartDate:");
        JLabel Updatelabel7 = new JLabel("EndDate:");
        JLabel Updatelabel8 = new JLabel("Comments:");
        
        final JPanel UpdatePanel = new JPanel(new BorderLayout());
        UpdatePanel.setPreferredSize(new Dimension(600, 50));
        JPanel UpdateNewFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        
        JButton UpdatesubmitButton = new JButton("Submit");
        
        UpdateNewFieldsPanel.add(Updatelabel1);
        UpdateNewFieldsPanel.add(UpdateField1);
        UpdateNewFieldsPanel.add(Updatelabel2);
        UpdateNewFieldsPanel.add(UpdateField2);
        UpdateNewFieldsPanel.add(Updatelabel3);
        UpdateNewFieldsPanel.add(UpdateField3);
        UpdateNewFieldsPanel.add(Updatelabel4);
        UpdateNewFieldsPanel.add(UpdateField4);
        UpdateNewFieldsPanel.add(Updatelabel5);
        UpdateNewFieldsPanel.add(UpdateField5);
        UpdateNewFieldsPanel.add(Updatelabel6);
        UpdateNewFieldsPanel.add(UpdateField6);
        UpdateNewFieldsPanel.add(Updatelabel7);
        UpdateNewFieldsPanel.add(UpdateField7);
        UpdateNewFieldsPanel.add(Updatelabel8);
        UpdateNewFieldsPanel.add(UpdateField8);
        UpdateNewFieldsPanel.add(UpdatesubmitButton);
        UpdatePanel.add(UpdateNewFieldsPanel, BorderLayout.CENTER);

        
        
        JButton deleteButton = new JButton("Delete");

        final JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deletePanel.setPreferredSize(new Dimension(600, 50));

        final JTextField deleteField = new JTextField(20);
        JLabel deleteLabel = new JLabel("TaskID to be deleted:");

        deletePanel.add(deleteLabel);
        deletePanel.add(deleteField);
        deletePanel.add(deleteButton);
        

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(600, 150));


        frame.add(combo, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int field1IntValue = Integer.parseInt(textField1.getText());
                    String field2Value = textField2.getText();
                    String field3Value = textField3.getText();
                    String field4Value = textField4.getText();
                    String field5Value = textField5.getText();
                    String field6Value = textField6.getText();
                    String field7Value = textField7.getText();
                    
                    if(App.checkTeamExists(field1IntValue) == true) {
                        App.addNewTask(field1IntValue, field2Value, field3Value, field4Value, field5Value, field6Value, field7Value);
                        showMessageDialog(null, "New Task has been added!");
                        frame.dispose();
                        CreateTaskOverview();
                    }
                    else {
                    	showMessageDialog(null, "This TeamID is not in use");
                    }
                    
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
                }
            }
        );
        UpdatesubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                    int Updatefield1IntValue = Integer.parseInt(UpdateField1.getText());
                    int Updatefield2IntValue = Integer.parseInt(UpdateField2.getText());
                    
                    
                    String Updatefield3Value = UpdateField3.getText();
                    String Updatefield4Value = UpdateField4.getText();
                    String Updatefield5Value = UpdateField5.getText();
                    String Updatefield6Value = UpdateField6.getText();
                    String Updatefield7Value = UpdateField7.getText();
                    String Updatefield8Value = UpdateField8.getText();

                	
                    if(App.checkTeamExists(Updatefield2IntValue) == true && App.checkTaskExists(Updatefield1IntValue) == true) {
                    	App.updateTask(Updatefield1IntValue, Updatefield2IntValue, Updatefield3Value, Updatefield4Value, Updatefield5Value, Updatefield6Value, Updatefield7Value, Updatefield8Value);
                        showMessageDialog(null, "Task has been modified!");
                        frame.dispose();
                        CreateTaskOverview();
                    }
                    else {
                    	showMessageDialog(null, "This TeamID or TaskID is not in use");
                    }
                    
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
			}
        });
        

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int Deletefield1IntValue = Integer.parseInt(deleteField.getText());
                	App.deleteTask(Deletefield1IntValue);
                	
                    showMessageDialog(null, "Task information has been deleted!");
                    frame.dispose();
                    CreateTaskOverview();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        });

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) combo.getSelectedItem();
                if (selectedOption.equals("Add new task")) {
                    bottomPanel.add(addNewPanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(true);
                    UpdatePanel.setVisible(false);
                    deletePanel.setVisible(false);
                } else if (selectedOption.equals("Change task information")) {
                    bottomPanel.add(UpdatePanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(false);
                    UpdatePanel.setVisible(true);
                    deletePanel.setVisible(false);
                } else {
                    bottomPanel.add(deletePanel, BorderLayout.CENTER);
                    addNewPanel.setVisible(false);
                    UpdatePanel.setVisible(false);
                    deletePanel.setVisible(true);
                }
            }
        });

        em.close();
        emf.close();
	    }
	
	// Creates intranet overview window
	
	public static void CreateIntraOverview() {

        System.out.println("Intra Overview");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<teams> teamquery = em.createQuery("SELECT t FROM teams t", teams.class);
        java.util.List<teams> teamList = teamquery.getResultList();

        TypedQuery<tasks> taskquery = em.createQuery("SELECT t FROM tasks t JOIN t.teams teams", tasks.class);
        java.util.List<tasks> taskList = taskquery.getResultList();

        TypedQuery<employees> employeequery = em.createQuery("SELECT e FROM employees e JOIN e.teams t", employees.class);
        java.util.List<employees> employeeList = employeequery.getResultList();

        String[] teamColumnNames = {"TeamID", "Location", "Description"};
        Object[][] teamData = new Object[teamList.size()][teamColumnNames.length];
        for (int i = 0; i < teamList.size(); i++) {
            teams team = teamList.get(i);
            teamData[i][0] = team.getTeamID();
            teamData[i][1] = team.getLocation();
            teamData[i][2] = team.getDescription();
        }

        String[] taskColumnNames = {"TaskID", "DueDate", "TeamID", "Status"};
        Object[][] taskData = new Object[taskList.size()][taskColumnNames.length];
        for (int i = 0; i < taskList.size(); i++) {
            tasks task = taskList.get(i);
            taskData[i][0] = task.getTaskID();
            taskData[i][1] = task.getDueDate();
            taskData[i][2] = task.getTeamID();
            taskData[i][3] = task.getStatus();
        }

        String[] employeeColumnNames = {"EmployeeID", "FirstName", "LastName", "PhoneNumber"};
        Object[][] employeeData = new Object[employeeList.size()][employeeColumnNames.length];
        for (int i = 0; i < employeeList.size(); i++) {
            employees employee = employeeList.get(i);
            employeeData[i][0] = employee.getEmployeeID();
            employeeData[i][1] = employee.getFirstName();
            employeeData[i][2] = employee.getLastName();
            employeeData[i][3] = employee.getPhoneNumber();
        }

        JTable teamTable = new JTable(teamData, teamColumnNames);
        JTable taskTable = new JTable(taskData, taskColumnNames);
        JTable employeeTable = new JTable(employeeData, employeeColumnNames);

        JFrame frame = new JFrame("Intra Overview");
        frame.setLayout(new GridLayout(3, 1));

        frame.add(new JScrollPane(teamTable));
        frame.add(new JScrollPane(taskTable));
        frame.add(new JScrollPane(employeeTable));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        em.close();
        emf.close();
    }

	public static void main(String[] args) {
		// This main method is used to start the visual program cycle
		CreateLogin();
	}
	
}

