package com.paulotech.category_service.service;

import com.paulotech.category_service.dto.SalonDto;
import com.paulotech.category_service.model.Category;

import java.util.Set;

public interface CategoryService {
    Category salveCategoria(Category categoria, SalonDto salaoDto);
    Set<Category> buscarTodasAsCategoriasPorSalao(Long id);
    Category buscarCategoriaPorId(Long id) throws Exception;
    void deletarCategoriaPorId(Long id, Long salaoId) throws Exception ;
}
