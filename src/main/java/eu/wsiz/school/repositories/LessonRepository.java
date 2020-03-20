package eu.wsiz.school.repositories;

import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {


}
