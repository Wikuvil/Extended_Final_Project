package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String generateUserJson(int id, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "User");
        user.put("email", email);

        try {
            return mapper.writeValueAsString(user);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сериализации JSON", e);
        }
    }
}