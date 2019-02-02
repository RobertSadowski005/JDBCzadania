package hibernate;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lessons")

public class Lesson {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String room;

    @ManyToMany (mappedBy = "lessons")
    private Set<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                Objects.equals(name, lesson.name) &&
                Objects.equals(room, lesson.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, room);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
