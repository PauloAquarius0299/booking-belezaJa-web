package com.paulotech.salao_api.service.impl;

import com.paulotech.salao_api.model.Salon;
import com.paulotech.salao_api.payload.dtos.SalonDto;
import com.paulotech.salao_api.payload.dtos.UserDto;
import com.paulotech.salao_api.repositories.SalonRepository;
import com.paulotech.salao_api.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon criarSalao(SalonDto req, UserDto user) {
        Salon salao = new Salon();
        salao.setNome(req.getNome());
        salao.setEndereco(req.getEndereco());
        salao.setEmail(req.getEmail());
        salao.setTelefone(req.getTelefone());
        salao.setCidade(req.getCidade());
        salao.setIdUsuarioDono(req.getIdUsuarioDono());
        salao.setImages(req.getImages());
        salao.setIdUsuarioDono(user.getId());
        salao.setAberto(req.getAberto());
        salao.setFechado(req.getFechado());
        return salonRepository.save(salao);
    }

    @Override
    public Salon atualizarSalao(SalonDto salaoDto, UserDto usuarioDto, Long salaoId) throws Exception {
        Salon existeSalao = salonRepository.findById(salaoId).orElse(null);
        if(existeSalao != null && salaoDto.getIdUsuarioDono().equals(usuarioDto.getId()) ) {
            existeSalao.setCidade(salaoDto.getCidade());
            existeSalao.setEmail(salaoDto.getEmail());
            existeSalao.setEndereco(salaoDto.getEndereco());
            existeSalao.setNome(salaoDto.getNome());
            existeSalao.setTelefone(salaoDto.getTelefone());
            existeSalao.setImages(salaoDto.getImages());
            existeSalao.setAberto(salaoDto.getAberto());
            existeSalao.setFechado(salaoDto.getFechado());
            existeSalao.setIdUsuarioDono(salaoDto.getIdUsuarioDono());
            return salonRepository.save(existeSalao);
        }throw new Exception("Salão não existe");
    }

    @Override
    public List<Salon> buscarTodosSaloes() {
        return salonRepository.findAll();
    }

    @Override
    public Salon buscarSalaoPorId(Long id) throws Exception {
        Salon salao = salonRepository.findById(id).orElse(null);
        if (salao == null) {
            throw new Exception("Salão não existe");
        }
        return salao;
    }

    @Override
    public Salon buscarSalaoPorIdUsuarioDoto(Long idUsuarioDono) {
        return salonRepository.findByIdUsuarioDono(idUsuarioDono);
    }

    @Override
    public List<Salon> buscarSaloesPorCidade(String cidade) {
        return salonRepository.buscarSaloes(cidade);
    }
}
