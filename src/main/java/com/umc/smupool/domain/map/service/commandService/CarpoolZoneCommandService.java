package com.umc.smupool.domain.map.service.commandService;

import com.umc.smupool.domain.map.dto.request.CarpoolZoneRequestDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;

public interface CarpoolZoneCommandService {
    CarpoolZone createCarpoolZone(CarpoolZoneRequestDTO.CreateCarpoolZoneDTO request);
    void deleteCarpoolZone(Long carpoolZoneId);
    CarpoolZone updateCarpoolZone(CarpoolZoneRequestDTO.UpdateCarpoolZoneDTO request, Long carpoolZoneId);
}
