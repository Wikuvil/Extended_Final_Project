package generators;

import com.github.javafaker.Faker;

public class UserCredsGenerator {
    private static final Faker faker = new Faker();

    public static String randomSafeEmail(){
        return faker.internet().safeEmailAddress();
    }

    public static String randomPassword(int maxSize){
        return faker.internet().password(1, maxSize);
    }

    public static String randomPassword(){
        return faker.internet().password();
    }
}
