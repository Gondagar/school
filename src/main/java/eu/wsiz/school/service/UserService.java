package eu.wsiz.school.service;

import eu.wsiz.school.models.User;
import eu.wsiz.school.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save one User.
     *
     * @return saved user with id

     * @see User
     */
    public User save(User user) {

        User save = userRepository.save(user);

        return save;

    }

    /**
     * Update one user.
     *
     * @param user user to update
     * @return saved user with update
     * @see User
     */
    public User update(User user) {
        User save = userRepository.save(user);
        return save;
    }

    /**
     * Get users
     *
     * @return Map<String, Object> in JSON + total elements and page.
     */

    public List<User> getUsers() {


        List<User> response = userRepository.findAll();

        return response;
    }

    /**
     * Get one user if exist.
     *
     * @param id user id
     * @return user by id
     * @see User
     */
    public User getOne(Long id) {
        User user = userRepository.getOne(id);
        return user;

    }



    /**
     * Delete user by id.
     *
     * @param id  user id
     * @return void
     *
     * @see User
     */
    @Transactional
    public void delete(long id) {


    }

}
