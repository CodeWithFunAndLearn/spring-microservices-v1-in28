package com.rest.webservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    // jpa repository or hibernate template can be used to persist data
    // for now, we will use a static list to simulate database operations

    // This is a static list to simulate a database
    private static  int usersCount = 0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jack", LocalDate.now().minusYears(20)));
    }

    // This method will return all users
    public static List<User> getUsers() {
        return users;
    }

    // This method will return a user by id
    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // This method will save a user
    public User saveUser(User user) {
//        if (user.getId() == 0) {
//            user.setId(users.size() + 1);
//        }
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    // This method will delete a user by id
    public User deleteUserById(int id) {
        User user = getUserById(id);
        if (user == null) {
            return null;
        }
        users.remove(user);
        return user;
    }

}
