package com.umc.smupool.domain.map.converter;

import com.umc.smupool.domain.map.dto.request.CarpoolZoneRequestDTO;
import com.umc.smupool.domain.map.dto.response.CarpoolZoneResponseDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;

import java.time.LocalDateTime;
import java.util.List;


public class CarpoolZoneConverter {

    public static CarpoolZoneResponseDTO.CreateCarpoolZoneResultDTO toCreateCarpoolZoneDTO(CarpoolZone carpoolZone){
        return CarpoolZoneResponseDTO.CreateCarpoolZoneResultDTO.builder()
                .CarpoolZoneId(carpoolZone.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CarpoolZone toCarpoolZone(CarpoolZoneRequestDTO.CreateCarpoolZoneDTO request) {
        return CarpoolZone.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static CarpoolZoneResponseDTO.CarpoolZonePreviewDTO toCarpoolZonePreviewDTO(CarpoolZone carpoolZone){
        return CarpoolZoneResponseDTO.CarpoolZonePreviewDTO.builder()
                .carpoolZoneId(carpoolZone.getId())
                .name(carpoolZone.getName())
                .address(carpoolZone.getAddress())
                .createdAt(carpoolZone.getCreatedAt())
                .updatedAt(carpoolZone.getUpdatedAt())
                .build();
    }

    public static CarpoolZoneResponseDTO.CarpoolZonePreviewListDTO toCarpoolZonePreviewListDTO(List<CarpoolZone> carpoolZoneList) {
        List<CarpoolZoneResponseDTO.CarpoolZonePreviewDTO> carpoolZonePreviewDTOList = carpoolZoneList.stream()
                .map(CarpoolZoneConverter::toCarpoolZonePreviewDTO)
                .toList();
        return CarpoolZoneResponseDTO.CarpoolZonePreviewListDTO.builder()
                .carpoolZonePreviewDTOList(carpoolZonePreviewDTOList)
                .build();
    }
}
