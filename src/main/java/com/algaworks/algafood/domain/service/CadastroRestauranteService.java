package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante salvar(Restaurante restaurante) {

        Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());

        if(Objects.isNull(cozinha)){
            throw new EntidadeNaoEncontradaException(String.format("Cozinha não encontrada com o código [%d]", restaurante.getCozinha().getId()));
        }

        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante buscar(Long id) {

        try {
            return restauranteRepository.buscar(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(String.format("Nenhum restaurante encontrado com o código [%d]", id));
        }
    }
}
