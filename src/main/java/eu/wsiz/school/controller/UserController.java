package eu.wsiz.school.controller;


import eu.wsiz.school.models.Lesson;
import eu.wsiz.school.models.User;
import eu.wsiz.school.models.Role;
import eu.wsiz.school.repositories.UserRepository;
import eu.wsiz.school.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The UserController is a controller for working with users.
 */



@Controller    // This means that this class is a Controller
@RequestMapping(path = "/users") // This means URL's start with /demo (after Application path)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    //src/main/webapp/views/admin/showAllStudent.jsp
    public static final String PAGE_OK = "admin/showAllStudent";
    public static final String RELOAD_PAGE = "redirect:/users/admin/";
    //  public static final String PAGE_ERROR = "/views/error/error.jsp";
    //  public static final String PAGE_RE_ADD = "/views/admin/showAllStudent.jsp";


    @Autowired
    private UserService userService;
    //  private UserRepository userRepository;


    /**
     * Add one user.
     *
     * @return saved user with id

     * @see User
     */

    @PostMapping(path = "/") // Map ONLY GET Requests
    public String addNewUser(HttpServletRequest req, HttpServletResponse resp) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("passRepeat");

        User n = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .role(Role.USER)
                .password(pass).build();
        userService.save(n);

        return RELOAD_PAGE;
    }


    /**
     * Get all users.
     *
     * @return req HttpServletRequest

     * @see  User list
     */

    @GetMapping(path = "/admin/")
    public String getAllUsers(HttpServletRequest req) throws ServletException, IOException {

        HttpSession session = req.getSession();
        List<User> users = userService.getUsers();
        session.setAttribute("users", users);

        return PAGE_OK;
    }

    /**
     * Update user.
     * @param model MVC object
     * @return updated user with id
     * @see User
     */

    @PostMapping(path = "/update/")
    public String updateUser(HttpServletRequest req, ModelAndView model) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");

        log.debug("Получены новые данные для обновления урока в DB: name = {}, surname = {}, email = {}.", name, surname, email);

        User user = userService.getOne(id);

        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);

        userService.update(user);

        List<User> users = userService.getUsers();
        model.addObject("users", users);

        return RELOAD_PAGE;

    }

    /**
     * Update user.
     * @param id user id
     */

    @PostMapping(path = "/delete/")
    public String deleteUser(@RequestParam(name = "id") long id) {
        System.out.println("Start drop user with id " + id);
        userService.delete(id);
        return RELOAD_PAGE;

    }


}