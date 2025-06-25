package vn.edu.tlu.cse.ht3.nguyenyenhieu.androi_qlns.models;

public class Department {
    private int id;
    private String name;
    private String description;
    private String leader;
    private int number;

    public Department() {}

    public Department(int id, String name, String description, String leader, int number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.leader = leader;
        this.number = number;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLeader() { return leader; }
    public void setLeader(String leader) { this.leader = leader; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
}
