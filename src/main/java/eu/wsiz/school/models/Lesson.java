package eu.wsiz.school.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @Column(name = "id")
    @GeneratedValue
    Long id;

    @Column(name = "theme")
    String theme;

    @Column(name = "place")
    String place;

    @Column(name = "teacher")
    String teacher;

    @Column(name = "date")
    String date;

    @Column(name = "time")
    String time;

    @ManyToMany
    @JoinTable(
            name = "group_lesson",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    List<Group> groups;


    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    Set<Presence> presences;



    @Override
    public String toString() {
        return "Lesson{id='" + id + "', theme = '" + theme + "', palece = '" + place + "', teacher = '" + teacher + "', date = '" + date + "', time = " + time + "'.";
    }
}
