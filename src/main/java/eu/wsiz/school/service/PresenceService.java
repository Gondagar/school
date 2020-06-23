package eu.wsiz.school.service;

import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.models.Presence;
import eu.wsiz.school.models.User;
import eu.wsiz.school.repositories.LessonRepository;
import eu.wsiz.school.repositories.PresenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PresenceService {

    private PresenceRepository presenceRepository;


    @Autowired
    public PresenceService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    /**
     * Save one presence.
     *
     * @return saved lesson with id

     * @see Lesson
     */
    public Presence save(Presence user) {

        Presence save = presenceRepository.save(user);

        return save;

    }

    /**
     * Update one presence.
     *
     * @param lesson lesson to update
     * @return saved lesson with update
     * @see User
     */
    public Presence update(Presence lesson) {
        Presence save = presenceRepository.save(lesson);
        return save;
    }

    /**
     * Get presences
     *
     * @return Map<String, Object> in JSON + total elements and page.
     */

    public List<Presence> getLessons() {


        List<Presence> response = presenceRepository.findAll();

        return response;
    }

    /**
     * Get one presence if exist.
     *
     * @param id presence id
     * @return presence by id
     * @see User
     */


    public Presence getOne(Long id) {
        Optional<Presence> byId = presenceRepository.findById(id);
        if(byId.isPresent()){
            return  byId.get();
        }
        return null;

    }

    /**
     * Delete presence by id.
     *
     * @param id  presence id
     * @return void
     *
     * @see User
     */
   
    public void delete(long id) {
        Optional<Presence> byId = presenceRepository.findById(id);
        if (byId.isPresent()) {
            presenceRepository.delete(byId.get());
        }
    }

}
