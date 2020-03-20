package eu.wsiz.school.models;

import com.sun.javafx.geom.transform.Identity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "users")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    String name;

    @ManyToMany
    @JoinTable(
            name = "group_lesson",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    Set<Lesson> lessons;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    Set<User> users;



}
