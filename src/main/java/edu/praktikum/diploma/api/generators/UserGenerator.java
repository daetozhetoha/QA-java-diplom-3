package edu.praktikum.diploma.api.generators;

import com.github.javafaker.Faker;
import edu.praktikum.diploma.api.models.User;

public class UserGenerator {
    public static Faker faker = new Faker();

    public static User randomUser() {
        return new User()
                .setEmail(faker.internet().safeEmailAddress())
                .setName(faker.name().username())
                .setPassword(faker.internet().password(6, 16));
    }
}
