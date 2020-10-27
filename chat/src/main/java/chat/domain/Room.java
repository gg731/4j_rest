package chat.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_room", joinColumns = {
            @JoinColumn(name = "room_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    private List<Person> persons = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "message_room", joinColumns = {
            @JoinColumn(name = "room_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "message_id", referencedColumnName = "id")})
    private List<Message> messages =  new ArrayList<>();

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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(name, room.name) &&
                Objects.equals(persons, room.persons) &&
                Objects.equals(messages, room.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, persons, messages);
    }
}
