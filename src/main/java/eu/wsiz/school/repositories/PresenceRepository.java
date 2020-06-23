package eu.wsiz.school.repositories;

import eu.wsiz.school.models.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence,Long> {

 //   @Query(value = "select * from presence where user_id = 1?", nativeQuery = true)
    List<Presence> findByUserId(Long id);

}
