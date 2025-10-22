package org.petstore.utils;

import com.github.javafaker.Faker;
import org.petstore.models.Category;
import org.petstore.models.Pet;
import org.petstore.models.Tag;

import java.util.List;
import java.util.Random;

/**
 * This class for generating random Pet data for testing.
 */
public class TestDataGenerator {
    private static final Faker faker = new Faker();

    private static final Random random = new Random();

    /**
     * This creates and returns a Pet object with random, valid test data.
     */
    public static Pet randomPet() {

        long id = System.currentTimeMillis() * 1000
                + java.util.concurrent.ThreadLocalRandom.current().nextInt(1000);

        String name = faker.animal().name();

        String status = randomStatus();

        Category category = Category.builder()
                .id(faker.number().randomNumber(3, true))
                .name(faker.animal().name())
                .build();

        Tag tag = Tag.builder()
                .id(faker.number().randomNumber(3, true))
                .name(faker.funnyName().name())
                .build();

        return Pet.builder()
                .id(id)
                .name(name)
                .category(category)
                .status(status)
                .photoUrls(List.of(faker.internet().image()))
                .tags(List.of(tag))
                .build();
    }

    /**
     * This method randomly selects one of the allowed pet statuses.
     */
    private static String randomStatus() {
        String[] statuses = {"available", "pending", "sold"};
        return statuses[random.nextInt(statuses.length)];
    }
}
