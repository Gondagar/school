package eu.wsiz.school.controller;


import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.models.Presence;
import eu.wsiz.school.models.User;
import eu.wsiz.school.repositories.LessonRepository;
import eu.wsiz.school.repositories.PresenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The LessonController is a controller for working with lessons.
 */

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/lessons") // This means URL's start with /demo (after Application path)
public class LessonController {

    private static final Logger log = LoggerFactory.getLogger(LessonController.class);
    public static final String ADMIN_PAGE = "admin/showAllLesson";
    public static final String USER_PAGE = "user/showAllLesson";
    public static final String EDIT_LESSON_PAGE = "admin/editLessons";

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PresenceRepository presenceRepository;

    /**
     * Start add one lesson.
     *
     * @return web form

     * @see Lesson
     */

    @GetMapping(path = "/")
    public String addNewLesson(){
        return "admin/addLessons";
    }



    /**
     * End one lesson.
     *
     * @return saved lesson with id

     * @see Lesson
     */

    @PostMapping(path = "/")
    public String addNewLesson(HttpServletRequest req, ModelAndView model) {

        String theme = req.getParameter("theme");
        String place = req.getParameter("place");
        String teacher = req.getParameter("teacher");


        String dt = req.getParameter("date");
        String[] strings = parseDate(dt);
        String date = strings[0];
        String time = strings[1];
        log.debug("Полученая дата - {} и время {}", date, time);

        if (theme != null & place != null & date != null) {

            log.debug("Полученны данные для создания урока:  theme = {}, place = {}, teacher = {} date = {}.", theme, place, teacher, date, time);

            Lesson newLesson = Lesson.builder()
                    .theme(theme)
                    .place(place)
                    .teacher(teacher)
                    .date(date)
                    .time(time)
                    .build();
            lessonRepository.save(newLesson);
            log.debug("Lesson add");
            return "redirect:/lessons/admin/";
        }

        return "redirect:/lessons/admin/";
    }

    /**
     * Get all lessons.
     *
     * @return lessons list

     * @see Lesson
     */


    @GetMapping(path = "/user/")
    public String getAllLessons(Model model, HttpServletRequest request) throws ServletException, IOException {

        log.debug("Отримано список заннять для користувача");
        List<Lesson> lessons = lessonRepository.findAll();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("student");
       // List<Lesson> lessons = currentUser.getGroup().getLessons();

        System.out.println(currentUser);
       // List<Presence> presences = presenceRepository.findByUserId(currentUser.getId());
        int[] ints = new int[] {1,1,-1};




        model.addAttribute("lessons", lessons);
        model.addAttribute("presences", ints);
        return USER_PAGE;

    }

    /**
     * Get all lessons.
     *
     * @return lessons list for admin

     * @see Lesson
     */
    @GetMapping(path = "/admin/")
    public String getAllLessonsAdmin(Model model) throws ServletException, IOException {

        log.info("Отримано список заннять для адміна");

        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return ADMIN_PAGE;

    }


    /**
     * Start edit one lesson.
     *
     * @return web form

     * @see Lesson
     */

    @GetMapping(path = "/edit/{id}")
    public String editLesson(Model model, @PathVariable(name = "id") Long id) throws ServletException, IOException {

        log.info("Отримано список заннять для адміна");

        Lesson lesson = lessonRepository.getOne(id);
        System.out.println(lesson);
        model.addAttribute("lesson", lesson);

        return EDIT_LESSON_PAGE;

    }

    /**
     * End edit one lesson.
     *
     * @return saved lesson with id

     * @see Lesson
     */

    @PutMapping(path = "/")
    public @ResponseBody
    String updateLesson(HttpServletRequest req, ModelAndView model) throws ServletException, IOException {

        String id = req.getParameter("id");
        String theme = req.getParameter("theme");
        String place = req.getParameter("place");
        String teacher = req.getParameter("feedback");

        String dt = req.getParameter("date");
        String[] strings = parseDate(dt);
        String date = strings[0];
        String time = strings[1];

        log.debug("Получены новые данные для обновления урока в DB: theme = {}, place = {}, Date = {}, time  = {}.", theme, place, date, time);

        Optional<Lesson> byId = lessonRepository.findById(Long.parseLong(id));
        if (byId.isPresent()) {
            Lesson lesson = byId.get();

            lesson.setTheme(theme);
            lesson.setPlace(place);
            lesson.setTeacher(teacher);
            lessonRepository.save(lesson);
        }

        List<Lesson> lessons = lessonRepository.findAll();
        model.addObject("lessons", lessons);

        return "redirect:/lessons/admin/";

    }

    /**
     * Delete  one lesson.
     *
     * @see Lesson
     */

    @DeleteMapping(path = "/")
    public @ResponseBody
    String deleteLesson(HttpServletRequest req, @RequestParam(name = "id") long id) {

        lessonRepository.deleteById(id);
        return "redirect:/lessons/admin/";

    }

    /**
     * Date formatter method
     *
     * @see Lesson
     */

    private String[] parseDate(String date) {
        String[] parseDateAndTime = date.split("T");


        return parseDateAndTime;
    }


}