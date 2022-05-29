package com.mandarin.mplus.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mandarin.mplus.model.User;
import com.mandarin.mplus.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User loginKakaoUser(String token) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String nickname = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
            Long id = element.getAsJsonObject().get("id").getAsLong();

            br.close();

            User user = new User(id, nickname);
            return saveUser(user);

        } catch (Exception e) {
            throw new RuntimeException("login error");
        }
    }

    private User saveUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getId());

        if (findUser.isEmpty()) {
            userRepository.save(user);
        }

        return user;
    }
}
