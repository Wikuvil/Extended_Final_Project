package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserResponse {
    private User user;

    @JsonProperty("access_token")
    private AccessToken accessToken;

    @Data
    @NoArgsConstructor
    public static class User {
        private int id;
        private String name;
        private String email;
    }

    @Data
    @NoArgsConstructor
    public static class AccessToken {
        @JsonProperty("access_token")
        private String accessToken;
    }
}
