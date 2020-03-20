package eu.wsiz.school.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Table(name = "presence")
public class Presence {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "is_present")
    Integer isPresent;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;


    @ManyToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;


}
