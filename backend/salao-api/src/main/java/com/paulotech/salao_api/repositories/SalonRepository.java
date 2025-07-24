package com.paulotech.salao_api.repositories;

import com.paulotech.salao_api.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {

    Salon findByIdUsuarioDono(Long idUsuarioDono);

    @Query("SELECT s FROM Salon s WHERE " +
            "LOWER(s.cidade) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.nome) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.endereco) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Salon> buscarSaloes(@Param("keyword") String keyword);
}
