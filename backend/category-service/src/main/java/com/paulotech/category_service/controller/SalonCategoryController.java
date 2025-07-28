package com.paulotech.category_service.controller;

import com.paulotech.category_service.dto.CategoryDto;
import com.paulotech.category_service.dto.SalonDto;
import com.paulotech.category_service.model.Category;
import com.paulotech.category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categorias/salao-produtor")
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> criarCategoria(@RequestBody CategoryDto dto){
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);

        Category categoria = new Category();
        categoria.setNome(dto.getNome());
        categoria.setImage(dto.getImage());
        categoria.setSalaoId(salonDto.getId());

        Category salvarCategorias = categoryService.salveCategoria(categoria, salonDto);
        return ResponseEntity.ok(salvarCategorias);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable Long id) throws Exception{
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);
        categoryService.deletarCategoriaPorId(id, salonDto.getId());
        return ResponseEntity.ok("Categoria deletada com sucesso.");
    }
}
