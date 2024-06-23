package com.umc.smupool.domain.auth.dto;

import lombok.Builder;
import org.json.JSONArray;
import org.json.JSONObject;


@Builder
public record AuthResponseDTO(
        String name,
        String username,
        String department
) {

    public static AuthResponseDTO from(JSONArray json) {
        return from(json.getJSONObject(0));
    }

    public static AuthResponseDTO from(JSONObject obj) {
        return AuthResponseDTO.builder()
                .name(obj.getString("NM_KOR"))
                .username(obj.getString("STDNO"))
                .department(getDepartment(obj.getString("TMP_DEPT_MJR_NM")))
                .build();
    }

    public static String getDepartment(String dept) {
        String[] depts = dept.split(" ");
        return depts[depts.length - 1];
    }
}
