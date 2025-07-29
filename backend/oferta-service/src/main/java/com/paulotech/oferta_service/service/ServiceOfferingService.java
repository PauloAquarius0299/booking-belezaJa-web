package com.paulotech.oferta_service.service;

import com.paulotech.oferta_service.dto.CategoryDto;
import com.paulotech.oferta_service.dto.SalonDto;
import com.paulotech.oferta_service.dto.ServiceDto;
import com.paulotech.oferta_service.model.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering criarServico(SalonDto salaoDto, ServiceDto serviceDto, CategoryDto categoriaDto);
    ServiceOffering atualizarServico(Long serviceId, ServiceOffering servico) throws Exception;
    Set<ServiceOffering> buscarServicosPorSalaoId(Long salaoId, Long categoriaId);
    Set<ServiceOffering> buscarServicosPorIds(Set<Long> ids);
    ServiceOffering buscarServicoPorId(Long id) throws Exception;
}
