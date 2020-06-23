package eu.wsiz.school.controller;


import eu.wsiz.school.models.Group;
import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.repositories.GroupRepository;
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
@RequestMapping(path = "/groups") // This means URL's start with /demo (after Application path)
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);
    public static final String ADMIN_PAGE = "admin/showAllGroup";
    public static final String EDIT_GROUP_PAGE = "admin/editGroup";

    @Autowired
    private GroupRepository groupRepository;


    @GetMapping(path = "/")
    public String addNewGroup() {
        return "admin/addGroup";
    }

    @PostMapping(path = "/")
    public String addNewGroup(HttpServletRequest req, ModelAndView model) {

        String name = req.getParameter("name");


        if (name != null) {

            log.debug("Полученны данные для создания группы: name = {}", name);

            Group newGroup = Group.builder()
                    .name(name)
                    .build();
            groupRepository.save(newGroup);
            log.debug("Group add");
            return "redirect:/groups/admin/";
        }

        return "redirect:/groups/admin/";
    }


    @GetMapping(path = "/admin/")
    public String getAllLessonsAdmin(Model model) {

        log.info("Отримано список груп для адміна");

        List<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return ADMIN_PAGE;

    }


    @GetMapping(path = "/edit/{id}")
    public String editGroup(HttpServletRequest req, @PathVariable(name = "id") Long id) {

        log.info("Отримано ID групи для редагування");

        Group group = groupRepository.getOne(id);
        System.out.println(group);
        req.getSession().setAttribute("group", group);

        return EDIT_GROUP_PAGE;

    }


    @PostMapping(path = "/edit/")
    public String updateLesson(HttpServletRequest req, @RequestParam(name = "id") Long id,  @RequestParam(name = "name") String name) throws ServletException, IOException {

        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Group lesson = byId.get();
            lesson.setName(name);
            groupRepository.save(lesson);
        }

        return "redirect:/groups/admin/";

    }

    @GetMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteLesson(HttpServletRequest req, @PathVariable(name = "id") long id) {

        groupRepository.deleteById(id);
        return "redirect:/groups/admin/";

    }


}