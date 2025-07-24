package com.paulotech.salao_api.service;

import com.paulotech.salao_api.model.Salon;
import com.paulotech.salao_api.payload.dtos.SalonDto;
import com.paulotech.salao_api.payload.dtos.UserDto;

import java.util.List;


public interface SalonService {
    Salon criarSalao(SalonDto salao, UserDto user);

    Salon atualizarSalao(SalonDto salaoDto, UserDto usuarioDto, Long salaoId) throws Exception;

    List<Salon> buscarTodosSaloes();

    Salon buscarSalaoPorId(Long id) throws Exception;

    Salon buscarSalaoPorIdUsuarioDoto(Long idUsuarioDono);

    List<Salon> buscarSaloesPorCidade(String cidade);

}
