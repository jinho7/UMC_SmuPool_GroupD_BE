package com.umc.smupool.domain.map.service.queryService;

import com.umc.smupool.domain.map.entity.CarpoolZone;

import java.util.List;

public interface CarpoolZoneQueryService {
    CarpoolZone readCarpoolZone(Long carpoolZoneId);
    List<CarpoolZone> readCarpoolZones();
}
