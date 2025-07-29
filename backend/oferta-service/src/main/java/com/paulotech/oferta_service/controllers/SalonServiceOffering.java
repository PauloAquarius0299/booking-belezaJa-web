package com.paulotech.oferta_service.controllers;

import com.paulotech.oferta_service.dto.CategoryDto;
import com.paulotech.oferta_service.dto.SalonDto;
import com.paulotech.oferta_service.dto.ServiceDto;
import com.paulotech.oferta_service.model.ServiceOffering;
import com.paulotech.oferta_service.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/oferta-servico/salao-produtor")
public class SalonServiceOffering {
    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> criarServico(@RequestBody ServiceDto servicoDto)  {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(servicoDto.getCategoria());

        ServiceOffering serviceOfertas=serviceOfferingService.criarServico(salonDto, servicoDto, categoryDto);
        return ResponseEntity.ok(serviceOfertas);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> atualizarServicoOferta(@PathVariable Long id,
                                                                  @RequestBody ServiceOffering serviceOffering) throws Exception {
        ServiceOffering serviceOfertas=serviceOfferingService.atualizarServico(id, serviceOffering);
        return ResponseEntity.ok(serviceOfertas);
    }
}
