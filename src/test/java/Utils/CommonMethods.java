package Utils;

import com.github.javafaker.Faker;

public class CommonMethods {

    private static Faker faker = new Faker();

    public static String firstName() {
        return faker.name().firstName();
    }

    public static String lastName() {
        return faker.name().lastName();
    }

    public static String companyName() {
        return faker.company().name();
    }
}
