package com.umc.smupool.domain.map.dto.request;

import lombok.Getter;

public class CarpoolZoneRequestDTO {

    @Getter
    public static class CreateCarpoolZoneDTO {
        String name;
        String address;
    }

    @Getter
    public static class UpdateCarpoolZoneDTO {
        String name;
        String address;
    }
}
