package com.paulotech.oferta_service.service.impl;

import com.paulotech.oferta_service.dto.CategoryDto;
import com.paulotech.oferta_service.dto.SalonDto;
import com.paulotech.oferta_service.dto.ServiceDto;
import com.paulotech.oferta_service.model.ServiceOffering;
import com.paulotech.oferta_service.repository.ServiceOfferingRespository;
import com.paulotech.oferta_service.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRespository serviceOfferingRepository;

    @Override
    public ServiceOffering criarServico(SalonDto salaoDto, ServiceDto serviceDto, CategoryDto categoriaDto) {
        ServiceOffering servicoOferta = new ServiceOffering();
        servicoOferta.setImage(serviceDto.getImage());
        servicoOferta.setNome(serviceDto.getNome());
        servicoOferta.setDescricao(serviceDto.getDescricao());
        servicoOferta.setPreco(serviceDto.getPreco());
        servicoOferta.setDuracao(serviceDto.getDuracao());
        servicoOferta.setSalaoId(serviceDto.getSalaoId());
        servicoOferta.setCategoriaId(serviceDto.getCategoria());

        return serviceOfferingRepository.save(servicoOferta);
    }

    @Override
    public ServiceOffering atualizarServico(Long serviceId, ServiceOffering servico) throws Exception {
        ServiceOffering servicoOferta = serviceOfferingRepository.findById(serviceId).orElse(null);

        if(servicoOferta==null){
            throw new Exception("Serviço não existe nesse id "+ serviceId);
        }

        servicoOferta.setImage(servico.getImage());
        servicoOferta.setNome(servico.getNome());
        servicoOferta.setDescricao(servico.getDescricao());
        servicoOferta.setPreco(servico.getPreco());
        servicoOferta.setDuracao(servico.getDuracao());

        return serviceOfferingRepository.save(servicoOferta);
    }

    @Override
    public Set<ServiceOffering> buscarServicosPorSalaoId(Long salaoId, Long categoriaId) {
        Set<ServiceOffering> servicos = serviceOfferingRepository.findBySalaoId(salaoId);

        if (categoriaId != null) {
            servicos = servicos.stream()
                    .filter(servico -> Objects.equals(servico.getCategoriaId(), categoriaId))
                    .collect(Collectors.toSet());
        }

        return servicos;
    }


    @Override
    public Set<ServiceOffering> buscarServicosPorIds(Set<Long> ids) {
        List<ServiceOffering> servicos = serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(servicos);
    }

    @Override
    public ServiceOffering buscarServicoPorId(Long id) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(id).orElse(null);

        if(serviceOffering==null){
            throw new Exception("Serviço não existe nesse id " + id);

        }
        return serviceOffering;
    }
}
