package com.paulotech.oferta_service.repository;

import com.paulotech.oferta_service.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ServiceOfferingRespository extends JpaRepository<ServiceOffering, Long> {
    Set<ServiceOffering> findBySalaoId(Long salaoId);
}
