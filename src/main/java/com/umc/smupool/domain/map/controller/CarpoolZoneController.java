package com.umc.smupool.domain.map.controller;

import com.umc.smupool.domain.map.converter.CarpoolZoneConverter;
import com.umc.smupool.domain.map.dto.request.CarpoolZoneRequestDTO;
import com.umc.smupool.domain.map.dto.response.CarpoolZoneResponseDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;
import com.umc.smupool.domain.map.service.commandService.CarpoolZoneCommandService;
import com.umc.smupool.domain.map.service.queryService.CarpoolZoneQueryService;
import com.umc.smupool.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carpoolZones")
public class CarpoolZoneController {

    private final CarpoolZoneCommandService carpoolZoneCommandService;
    private final CarpoolZoneQueryService carpoolZoneQueryService;

    @PostMapping("/")
    public ApiResponse<CarpoolZoneResponseDTO.CreateCarpoolZoneResultDTO> createCarpoolZone(@RequestBody CarpoolZoneRequestDTO.CreateCarpoolZoneDTO request) {
        CarpoolZone carpoolZone = carpoolZoneCommandService.createCarpoolZone(request);
        return ApiResponse.onSuccess(CarpoolZoneConverter.toCreateCarpoolZoneDTO(carpoolZone));
    }

    @GetMapping("/{carpoolZoneId}")
    public ApiResponse<CarpoolZoneResponseDTO.CarpoolZonePreviewDTO> readCarpoolZone(@PathVariable Long carpoolZoneId){
        CarpoolZone carpoolZone = carpoolZoneQueryService.readCarpoolZone(carpoolZoneId);
        return ApiResponse.onSuccess(CarpoolZoneConverter.toCarpoolZonePreviewDTO(carpoolZone));
    }

    @GetMapping("/")
    public ApiResponse<CarpoolZoneResponseDTO.CarpoolZonePreviewListDTO> readCarpoolZones(){
        List<CarpoolZone> carpoolZoneList= carpoolZoneQueryService.readCarpoolZones();
        return ApiResponse.onSuccess(CarpoolZoneConverter.toCarpoolZonePreviewListDTO(carpoolZoneList));
    }

    @DeleteMapping("/{carpoolZoneId}")
    public ApiResponse<String> deleteCarpoolZone(@PathVariable Long carpoolZoneId) {
        carpoolZoneCommandService.deleteCarpoolZone(carpoolZoneId);
        return ApiResponse.onSuccess("CarpoolZone이 삭제되었습니다.");
    }

    @PatchMapping("/{carpoolZoneId}")
    public ApiResponse<CarpoolZoneResponseDTO.CarpoolZonePreviewDTO> updateCarpoolZone(@RequestBody CarpoolZoneRequestDTO.UpdateCarpoolZoneDTO request, @PathVariable Long carpoolZoneId) {
        CarpoolZone carpoolZone = carpoolZoneCommandService.updateCarpoolZone(request, carpoolZoneId);
        return ApiResponse.onSuccess(CarpoolZoneConverter.toCarpoolZonePreviewDTO(carpoolZone));

    }
}
