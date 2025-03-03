public class Employee {
    protected String name;
    protected int id;
    protected double salary;
    protected String department;
    public static final double BONUS_RATE = 0.1;

    public Employee(String name, int id, double salary, String department){
        this.setName(name);
        this.setId(id);
        this.setSalary(salary);
        this.setDepartment(department);
    }

    public void printDetails(){
        System.out.println("Employee ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Salary: " + getSalary());
        System.out.println("Department: " + getDepartment());
    }

    public void applyBonus(){
        setSalary(getSalary() + calculateBonus());
        System.out.println("Bonus applied! New salary: " + getSalary());
    }

    private double calculateBonus() {
        double bonus = getSalary() * BONUS_RATE;
        return bonus;
    }

    public void updateDepartment(String newDepartment){
        setDepartment(newDepartment);
        System.out.println("Department updated to: " + getDepartment());
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected double getSalary() {
        return salary;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    protected String getDepartment() {
        return department;
    }

    protected void setDepartment(String department) {
        this.department = department;
    }
}

class Project{
    public String projectName;
    public String projectDeadline;
    public Employee projectLeader;
    public double budget;

    public Project(String projectName, String projectDeadline, Employee projectLeader, double budget){
        this.projectName = projectName;
        this.projectDeadline = projectDeadline;
        this.projectLeader = projectLeader;
        this.budget = budget;
    }

    public void printProjectDetails(){
        System.out.println("Project Name: " + projectName);
        System.out.println("Project Deadline: " + projectDeadline);
        System.out.println("Budget: " + budget);
        projectLeader.printDetails();
    }

    public void updateBudget(double newBudget){
        budget = newBudget;
        System.out.println("Budget updated to: " + budget);
    }
}

class Client{
    public String clientName;
    public String clientEmail;
    public Project project;

    public Client(String clientName, String clientEmail, Project project){
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.project = project;
    }

    public void printClientDetails(){
        System.out.println("Client Name: " + clientName);
        System.out.println("Client Eamil: " + clientEmail);
        project.printProjectDetails();
    }
}
class MainApp{
}
