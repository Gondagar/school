package eu.wsiz.school.controller;


import eu.wsiz.school.controller.student.ShowStudentsController;
import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.repositories.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/lessons") // This means URL's start with /demo (after Application path)
public class LessonController {

    private static final Logger log = LoggerFactory.getLogger(ShowStudentsController.class);
    public static final String ADMIN_PAGE = "admin/showAllLesson";
    public static final String USER_PAGE = "user/showAllLesson";
    public static final String EDIT_LESSON_PAGE = "admin/editLessons";

    @Autowired
    private LessonRepository lessonRepository;


    @GetMapping(path = "/")
    public String addNewLesson(){
        return "admin/addLessons";
    }

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

    @GetMapping(path = "/user/")
    public String getAllLessons(Model model) throws ServletException, IOException {

        log.debug("Отримано список заннять для користувача");
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return USER_PAGE;

    }

    @GetMapping(path = "/admin/")
    public String getAllLessonsAdmin(Model model) throws ServletException, IOException {

        log.info("Отримано список заннять для адміна");

        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return ADMIN_PAGE;

    }




    @GetMapping(path = "/edit/{id}")
    public String editLesson(Model model, @PathVariable(name = "id") Long id) throws ServletException, IOException {

        log.info("Отримано список заннять для адміна");

        Lesson lesson = lessonRepository.getOne(id);
        System.out.println(lesson);
        model.addAttribute("lesson", lesson);

        return EDIT_LESSON_PAGE;

    }



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

    @DeleteMapping(path = "/")
    public @ResponseBody
    String deleteLesson(HttpServletRequest req, @RequestParam(name = "id") long id) {

        lessonRepository.deleteById(id);
        return "redirect:/lessons/admin/";

    }


    private String[] parseDate(String date) {
        String[] parseDateAndTime = date.split("T");


        return parseDateAndTime;
    }


}