package com.umc.smupool.domain.map.service.queryService.Impl;

import com.umc.smupool.domain.map.entity.CarpoolZone;
import com.umc.smupool.domain.map.exception.CarpoolZoneErrorCode;
import com.umc.smupool.domain.map.exception.handler.CarpoolZoneHandler;
import com.umc.smupool.domain.map.repository.CarpoolZoneRepository;
import com.umc.smupool.domain.map.service.queryService.CarpoolZoneQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarpoolZoneQueryServiceImpl implements CarpoolZoneQueryService {

    private final CarpoolZoneRepository carpoolZoneRepository;

    @Override
    public CarpoolZone readCarpoolZone(Long carpoolZoneId){
        CarpoolZone carpoolZone = carpoolZoneRepository.findById(carpoolZoneId).orElseThrow(()-> {
            throw new CarpoolZoneHandler(CarpoolZoneErrorCode._NOT_FOUND_CARPOOLZONE);
        });
        return carpoolZone;
    }

    @Override
    public List<CarpoolZone> readCarpoolZones(){
        return carpoolZoneRepository.findAll();
    }
}
