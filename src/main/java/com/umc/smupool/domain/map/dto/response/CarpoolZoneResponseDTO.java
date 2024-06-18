package com.umc.smupool.domain.map.dto.response;

import com.umc.smupool.domain.map.entity.CarpoolZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CarpoolZoneResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateCarpoolZoneResultDTO {

        private Long CarpoolZoneId;

        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CarpoolZonePreviewDTO {
        private Long carpoolZoneId;
        private String name;
        private String address;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CarpoolZonePreviewListDTO {
        List<CarpoolZonePreviewDTO> carpoolZonePreviewDTOList;
    }
}
