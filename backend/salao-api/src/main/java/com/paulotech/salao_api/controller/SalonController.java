package com.paulotech.salao_api.controller;

import com.paulotech.salao_api.mapper.SalonMapper;
import com.paulotech.salao_api.model.Salon;
import com.paulotech.salao_api.payload.dtos.SalonDto;
import com.paulotech.salao_api.payload.dtos.UserDto;
import com.paulotech.salao_api.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDto> criarSalao(@RequestBody SalonDto salaoDto){
        UserDto usuarioDto = new UserDto();
        usuarioDto.setId(1L);
        Salon salao =salonService.criarSalao(salaoDto,usuarioDto);
        SalonDto salaoDto1= SalonMapper.mapToDto(salao);
        return ResponseEntity.ok(salaoDto1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDto> atualizarSalao(@PathVariable("id") Long salaoId,
                                                   @RequestBody SalonDto salaoDto) throws Exception {
        UserDto usuarioDto = new UserDto();
        usuarioDto.setId(1L);
        Salon salao =salonService.atualizarSalao(salaoDto, usuarioDto, salaoId);
        SalonDto salaoDto1= SalonMapper.mapToDto(salao);
        return ResponseEntity.ok(salaoDto1);
    }

    @GetMapping
    public ResponseEntity<List<SalonDto>> buscarSaloes() throws Exception {
        List<Salon> salao =salonService.buscarTodosSaloes();
        List<SalonDto> salaoDtos= salao.stream().map((salaos) ->
        {
            SalonDto salaosDto=SalonMapper.mapToDto(salaos);
            return salaosDto;
        }).toList();
        return ResponseEntity.ok(salaoDtos);
    }

    @GetMapping("/{salaoId}")
    public ResponseEntity<SalonDto> buscarSalaoPorId(@PathVariable Long salaoId) throws Exception {
        Salon salao = salonService.buscarSalaoPorId(salaoId);

        SalonDto salaoDto = SalonMapper.mapToDto(salao);

        return ResponseEntity.ok(salaoDto);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<SalonDto>> pesquisarSalao(@RequestParam("cidade") String cidade) throws Exception {
        List<Salon> saloes = salonService.buscarSaloesPorCidade(cidade);
        List<SalonDto> salaoDtos= saloes.stream().map((salao) ->
        {
            SalonDto salaosDto=SalonMapper.mapToDto(salao);
            return salaosDto;
        }).toList();
        return ResponseEntity.ok(salaoDtos);
    }

    @GetMapping("/proprietario")
    public ResponseEntity<SalonDto> buscarIdUsuarioDonoSalao(@PathVariable Long salaoId) throws Exception {
        UserDto usuarioDto = new UserDto();
        usuarioDto.setId(1L);
        Salon salao = salonService.buscarSalaoPorIdUsuarioDoto(usuarioDto.getId());

        SalonDto salaoDto = SalonMapper.mapToDto(salao);
        return ResponseEntity.ok(salaoDto);
    }

}
