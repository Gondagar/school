package eu.wsiz.school.service;

import eu.wsiz.school.AbstractTest;
import eu.wsiz.school.models.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Slf4j
public class LessonsServiceTest extends AbstractTest {

    @Autowired
    LessonsService lessonsService;

    Long testLessonId;

    @Test
    public void save() {
       /* Lesson lesson = new Lesson();
        lesson.setId(100L);
        lesson.setPlace("test");
        lesson.setTeacher("test");
        lesson.setDate(LocalDateTime.now().toString());

        Lesson save = lessonsService.save(lesson);
        testLessonId = save.getId();
        assertEquals("test", save.getTeacher());*/
    }

    @Test
    public void getLessons() {
        List<Lesson> lessons = lessonsService.getLessons();
        assertEquals(8l , lessons.size());
    }

    @Test
    public void getOne() {
     /*   Lesson lesson = lessonsService.getOne(3l);
        log.info("Lesson " + lesson);
        assertEquals("Z-online", lesson.getPlace());*/
    }

    @Test
    public void delete() {
     /*   lessonsService.delete(100);

        Lesson one = lessonsService.getOne(100L);
        assertNull(one);*/

    }

    @Test
    public void update() {
    }
}