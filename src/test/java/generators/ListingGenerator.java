package generators;

import com.github.javafaker.Faker;

public class ListingGenerator {
    private static final Faker faker = new Faker();

    public static String randomName(){
        return faker.commerce().productName();
    }

    public static String randomDescription(){
        return faker.lorem().sentence(10);
    }

    public static int randomPrice(){
        return faker.number().numberBetween(100, 10000);
    }
}
