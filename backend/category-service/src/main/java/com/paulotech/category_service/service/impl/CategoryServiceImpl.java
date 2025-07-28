package com.paulotech.category_service.service.impl;

import com.paulotech.category_service.dto.SalonDto;
import com.paulotech.category_service.model.Category;
import com.paulotech.category_service.repositories.CategoryRepository;
import com.paulotech.category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category salveCategoria(Category categoria, SalonDto salaoDto) {
        Category novaCategoria = new Category();
        novaCategoria.setNome(categoria.getNome());
        novaCategoria.setImage(categoria.getImage());
        novaCategoria.setSalaoId(salaoDto.getId());
        return categoryRepository.save(novaCategoria);
    }


    @Override
    public Set<Category> buscarTodasAsCategoriasPorSalao(Long id) {
        return categoryRepository.findBySalaoId(id);
    }

    @Override
    public Category buscarCategoriaPorId(Long id) throws Exception {
        Category categoria = categoryRepository.findById(id).orElse(null);

        if (categoria == null) {
            throw new Exception("Categoria não encontrada com o ID: " + id);
        }
        return categoria;
    }

    @Override
    public void deletarCategoriaPorId(Long id, Long salaoId) throws Exception {
        Category categoria = buscarCategoriaPorId(id);
        if (!categoria.getSalaoId().equals(salaoId)) {
            throw new Exception("Você não tem permissão para deletar esta categoria.");
        }
        categoryRepository.deleteById(id);
    }

}
