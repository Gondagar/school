package eu.wsiz.school.controller;


import eu.wsiz.school.models.Group;
import eu.wsiz.school.models.User;
import eu.wsiz.school.models.enums.Role;
import eu.wsiz.school.repositories.GroupRepository;
import eu.wsiz.school.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public static final String LOGIN = "auth/login";
    public static final String REGISTRY = "auth/registry";
    public static final String PAGE_ERROR = "/error/error";


    public static final String PAGE_ADMIN = "/lessons/admin/";
    public static final String PAGE_USER = "/lessons/user/";


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping(path = "/registry")
    public String registry() throws ServletException, IOException {
        System.out.println("start registry");
        return REGISTRY;
    }

    @GetMapping(path = "/login")
    public String login() throws ServletException, IOException {
        System.out.println("start login");

        return LOGIN;
    }


    @PostMapping(path = "/registry")
    public String registry(HttpServletRequest req) throws ServletException, IOException {

        System.out.println("finish registry");


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passRepeat = req.getParameter("passRepeat");

        log.info("name = '{}', surname = '{}',  email = '{}', password '{}', passRepeat '{}'.", name, surname, email, password, passRepeat);


        if (email != null & password != null) {



            Group group = groupRepository.getOne(1l);

            User newUser = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .role(Role.USER)
                    .group(group)
                    .password(password).build();
            userRepository.save(newUser);
            log.info("User add");
            return LOGIN;
        } else {
            System.out.println("empty fields");
            return REGISTRY;
        }

    }


    @PostMapping(path = "/login")
    String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("login");
        String password = req.getParameter("password");
        String forgot = req.getParameter("forgot");
        HttpSession session = req.getSession();
        if (email != null) {


            User user = userRepository.getUserByEmail(email).get();
            if (user.getPassword().equals(password)) {


                log.info("Password valid ");
                if (user.getRole().equals(Role.ADMIN) ) {
                    log.debug("Hello admin {}", user.getName());
                    session.setAttribute("admin", user);
                    return "redirect:" + PAGE_ADMIN;
                } else if (user.getRole().equals(Role.USER)) {

                    session.setAttribute("student", user);
                    return "redirect:" + PAGE_USER;

                } else {
                    return PAGE_ERROR;
                }

            } else {

                return "redirect:/";
            }


        } else {

            return "redirect:/";
        }


    }


}




