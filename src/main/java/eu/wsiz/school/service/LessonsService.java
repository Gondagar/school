package eu.wsiz.school.service;

import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.models.User;
import eu.wsiz.school.repositories.LessonRepository;
import eu.wsiz.school.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LessonsService {

    private LessonRepository lessonRepository;


    @Autowired
    public LessonsService(LessonRepository userRepository) {
        this.lessonRepository = userRepository;
    }

    /**
     * Save one lesson.
     * @param lesson lesson to save
     * @return saved lesson with id

     * @see Lesson
     */
    public Lesson save(Lesson lesson) {

        Lesson save = lessonRepository.save(lesson);

        return save;

    }

    /**
     * Update one lesson.
     *
     * @param lesson lesson to update
     * @return saved lesson with update
     * @see User
     */
    public Lesson update(Lesson lesson) {
        Lesson save = lessonRepository.save(lesson);
        return save;
    }

    /**
     * Get lessons
     *
     * @return  List
     */

    public List<Lesson> getLessons() {


        List<Lesson> response = lessonRepository.findAll();

        return response;
    }

    /**
     * Get one user if exist.
     *
     * @param id lesson id
     * @return lesson by id
     * @see User
     */

    @Transactional
    public Lesson getOne(Long id) {
        Optional<Lesson> byId = lessonRepository.findById(id);
        if(byId.isPresent()){
            return  byId.get();
        }
        return null;

    }



    /**
     * Delete user by id.
     *
     * @param id  user id
     *
     * @see User
     */
    @Transactional
    public void delete(long id) {


    }

}
