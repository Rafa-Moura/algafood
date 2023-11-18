package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {

        Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());

        if (Objects.isNull(cozinha)) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha não encontrada com o código [%d]", restaurante.getCozinha().getId()));
        }

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscar(Long id) {

        return restauranteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Nenhum restaurante encontrado com o código [%d]", id)));
    }

    public void remover(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(String.format("Nenhum restaurante encontrado com o código [%d]", id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format("Restaurante de código %d em uso e não pode ser deletado", id));
        }
    }

    public List<Restaurante> buscaPorValorDaTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    public List<Restaurante> buscarRestaurantePorNomeECozinhaId(String nome, Long id) {
        return restauranteRepository.consultaPorNome(nome, id);
    }

    public Restaurante retornarOPrimeiroEncontradoPorNome(String nome){
        return restauranteRepository.findFirstByNomeContaining(nome).get();
    }

    public List<Restaurante> top2PorNome(String nome){
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    public int totalDeRestaurantePorCozinha(Long id){
        return restauranteRepository.countByCozinhaId(id);
    }
}
