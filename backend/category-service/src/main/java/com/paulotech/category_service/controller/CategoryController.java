package com.paulotech.category_service.controller;

import com.paulotech.category_service.model.Category;
import com.paulotech.category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/salao/{id}")
    public ResponseEntity<Set<Category>> buscarCategoriasPorSalao(@PathVariable Long id) {
        Set<Category> categorias = categoryService.buscarTodasAsCategoriasPorSalao(id);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> buscarCategoriaPorId(@PathVariable Long id)throws Exception {
        Category categoria = categoryService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }


}
