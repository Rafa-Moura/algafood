package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public List<Cozinha> listarPorNome(String nome) {
        return cozinhaRepository.findTodasByNome(nome);
    }

    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Nenhuma cozinha encontrada com o código [%d]", id)));
    }

    public Cozinha buscarPorNome(String nome){
        return cozinhaRepository.findByNome(nome)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Nenhuma cozinha encontrado com o nome [%s]", nome)));
    }

    public List<Cozinha> buscaTop2PorNome(String nome){
        return cozinhaRepository.findTop2ByNomeContaining(nome);
    }

    public Optional<Cozinha> buscarPrimeiro(){
        return cozinhaRepository.buscarPrimeiro();
    }

    public boolean existeCozinhaPorNome(String nome){
        return cozinhaRepository.existsByNome(nome);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(String.format("Nenhuma cozinha encontrada com o código [%d]", id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código [%d] não pode ser removida, pois está em uso.", id));
        }
    }
}
