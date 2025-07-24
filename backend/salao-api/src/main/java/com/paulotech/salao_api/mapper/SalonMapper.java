package com.paulotech.salao_api.mapper;

import com.paulotech.salao_api.model.Salon;
import com.paulotech.salao_api.payload.dtos.SalonDto;

public class SalonMapper {

    public static SalonDto mapToDto(Salon salao){
        SalonDto salaoDto = new SalonDto();
        salaoDto.setId(salao.getId());
        salaoDto.setNome(salao.getNome());
        salaoDto.setImages(salao.getImages());
        salaoDto.setEndereco(salao.getEndereco());
        salaoDto.setEmail(salao.getEmail());
        salaoDto.setTelefone(salao.getTelefone());
        salaoDto.setCidade(salao.getCidade());
        salaoDto.setIdUsuarioDono(salao.getIdUsuarioDono());
        salaoDto.setAberto(salao.getAberto());
        salaoDto.setFechado(salao.getFechado());
        return salaoDto;
    }
}
