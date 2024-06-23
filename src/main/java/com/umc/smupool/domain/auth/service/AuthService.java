package com.umc.smupool.domain.auth.service;

import com.umc.smupool.domain.auth.dto.AuthRequestDTO;
import com.umc.smupool.domain.auth.dto.AuthResponseDTO;
import com.umc.smupool.domain.member.exception.AuthErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final String LOGIN_URL = "https://smsso.smu.ac.kr/Login.do";
    private final String BASE_URL = "https://smul.smu.ac.kr";

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        JSONObject response = getData(authRequestDTO, "/UsrSchMng/selectStdInfo.do");
        return AuthResponseDTO.from(response.getJSONArray("dsStdInfoList"));
    }

    private Map<String, String> login(AuthRequestDTO authRequestDTO) {
        try {
            Connection.Response response = Jsoup.connect(LOGIN_URL)
                    .data("user_id",authRequestDTO.username())
                    .data("user_password", authRequestDTO.password())
                    .method(Connection.Method.POST)
                    .execute();
            if (response.url().toString().equals(LOGIN_URL))
                throw new GeneralException(AuthErrorCode.AUTH_UNAUTHORIZED);
            return Jsoup.connect(BASE_URL.concat("/index.do"))
                    .method(Connection.Method.GET)
                    .cookies(response.cookies())
                    .execute()
                    .cookies();
        } catch (IOException e) {
            throw new GeneralException(AuthErrorCode.AUTH_INTERNAL_SERVER_ERROR);
        }
    }

    private JSONObject getData(AuthRequestDTO authRequestDTO, String url) {
        Map<String, String> session = login(authRequestDTO);
        try {
            URL apiUrl = new URL(BASE_URL.concat(url));
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> cookie : session.entrySet())
                connection.addRequestProperty("Cookie", cookie.getKey() + "=" + cookie.getValue());
            String requestData = "@d#=@d1#&@d1#tp=dm&_AUTH_MENU_KEY=usrCPsnlInfoUpd-STD&@d1#strStdNo=".concat(authRequestDTO.username());
            connection.setDoOutput(true);
            connection.getOutputStream().write(requestData.getBytes());

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null)
                    response.append(line);
            }
            return new JSONObject(response.toString());
        } catch (IOException e) {
            throw new GeneralException(AuthErrorCode.AUTH_INTERNAL_SERVER_ERROR);
        }
    }
}
