package auth.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String last;
    private Timestamp date;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "employee_persons", joinColumns = {
            @JoinColumn(name = "id_person", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_employee", referencedColumnName = "id")})
    private List<Person> persons;

    public static Employee of(int id, String name, String lastName) {
        Employee employee = new Employee();
        employee.id = id;
        employee.name = name;
        employee.last = lastName;

        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
