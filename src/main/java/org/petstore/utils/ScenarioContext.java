package org.petstore.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class thread-safe context to store and share data between Cucumber steps.
 */
public class ScenarioContext {

    private static final ThreadLocal<Map<String, Object>> context =
            ThreadLocal.withInitial(HashMap::new);

    /**
     * This method Store a key-value pair in the current scenario context.
     */
    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    /**
     * This method retrieve a stored value from the context and cast it to the expected type.
     */
    public static <T> T get(String key, Class<T> type) {
        return type.cast(context.get().get(key));
    }

    /**
     * This method convenience method to store the created pets ID.
     */
    public static void setCreatedPetId(Long id) {
        set("createdPetId", id);
    }

    /**
     * This method convenience method to retrieve the created pets ID.
     */
    public static Long getCreatedPetId() {
        return get("createdPetId", Long.class);
    }
}
