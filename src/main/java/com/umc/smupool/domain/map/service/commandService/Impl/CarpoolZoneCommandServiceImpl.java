package com.umc.smupool.domain.map.service.commandService.Impl;

import com.umc.smupool.domain.map.dto.request.CarpoolZoneRequestDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;
import com.umc.smupool.domain.map.exception.CarpoolZoneErrorCode;
import com.umc.smupool.domain.map.exception.handler.CarpoolZoneHandler;
import com.umc.smupool.domain.map.repository.CarpoolZoneRepository;
import com.umc.smupool.domain.map.service.commandService.CarpoolZoneCommandService;
import com.umc.smupool.domain.map.converter.CarpoolZoneConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class CarpoolZoneCommandServiceImpl implements CarpoolZoneCommandService {

    private final CarpoolZoneRepository carpoolZoneRepository;

    @Override
    public CarpoolZone createCarpoolZone(CarpoolZoneRequestDTO.CreateCarpoolZoneDTO request){
        CarpoolZone carpoolZone = CarpoolZoneConverter.toCarpoolZone(request);
        return carpoolZoneRepository.save(carpoolZone);
    }

    @Override
    public void deleteCarpoolZone(Long carpoolZoneId){
        CarpoolZone carpoolZone = carpoolZoneRepository.findById(carpoolZoneId).orElseThrow(()-> new CarpoolZoneHandler(CarpoolZoneErrorCode._NOT_FOUND_CARPOOLZONE));
        carpoolZoneRepository.delete(carpoolZone);
    }

    @Override
    public CarpoolZone updateCarpoolZone(CarpoolZoneRequestDTO.UpdateCarpoolZoneDTO request, Long carpoolZoneId){
        CarpoolZone carpoolZone = carpoolZoneRepository.findById(carpoolZoneId).orElseThrow(()-> new CarpoolZoneHandler(CarpoolZoneErrorCode._NOT_FOUND_CARPOOLZONE));
        carpoolZone.update(request.getName(), request.getAddress());
        return carpoolZone;
    }

}
