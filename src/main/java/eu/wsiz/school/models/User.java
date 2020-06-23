package eu.wsiz.school.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by serfer on 24.07.16.  implements Comparable<Employee>
 */
@Data

@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email", unique =  true, nullable =  false)
    String email;

    @Column(name = "password")
    String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    Group group;

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', surname='" + surname + "', email = '" + email + "'}.";
    }

}
