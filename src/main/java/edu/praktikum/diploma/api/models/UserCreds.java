package edu.praktikum.diploma.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserCreds {
    private String email;
    private String password;
    private String name;

    public UserCreds() {

    }

    private UserCreds(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCreds credsFromUser(User user) {
        return new UserCreds(user.getEmail(), user.getPassword());
    }
}
