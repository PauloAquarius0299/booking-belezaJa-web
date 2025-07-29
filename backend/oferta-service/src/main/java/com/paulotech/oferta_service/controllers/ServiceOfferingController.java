package com.paulotech.oferta_service.controllers;

import com.paulotech.oferta_service.model.ServiceOffering;
import com.paulotech.oferta_service.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/oferta-servico")
@RequiredArgsConstructor
public class ServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salao/{salaoId}")
    public ResponseEntity<Set<ServiceOffering>> buscarServicoPorIdSalao(@PathVariable Long salaoId,
                                                                        @RequestParam(required = false) Long categoriaId) {
        Set<ServiceOffering> serviceOfertas=serviceOfferingService.buscarServicosPorSalaoId(salaoId, categoriaId);
        return ResponseEntity.ok(serviceOfertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> buscarServicoPorId(@PathVariable Long id) throws Exception {
        ServiceOffering serviceOfertas=serviceOfferingService.buscarServicoPorId(id);
        return ResponseEntity.ok(serviceOfertas);
    }

    @GetMapping("/lista/{ids}")
    public ResponseEntity<Set<ServiceOffering>> buscarServicoPorId(@PathVariable Set<Long> ids){
        Set<ServiceOffering> serviceOfertas = serviceOfferingService.buscarServicosPorIds(ids);
        return ResponseEntity.ok(serviceOfertas);
    }


}
