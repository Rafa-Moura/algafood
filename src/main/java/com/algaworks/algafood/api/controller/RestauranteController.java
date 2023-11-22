package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/restaurantes", produces = APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteNovo = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteNovo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {

        try {
            Restaurante restauranteAtual = cadastroRestauranteService.buscar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

            cadastroRestauranteService.salvar(restauranteAtual);

            return ResponseEntity.ok(restauranteAtual);

        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {

        List<Restaurante> restaurantes = cadastroRestauranteService.listar();

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping(value = "/buscar-por-taxa")
    public ResponseEntity<List<Restaurante>> listarPorValorDeTaxa(@RequestParam BigDecimal taxaInicial,
                                                                  @RequestParam BigDecimal taxaFinal) {

        List<Restaurante> restaurantes = cadastroRestauranteService.buscaPorValorDaTaxa(taxaInicial, taxaFinal);

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping(value = "/buscar-por-taxa-nome")
    public ResponseEntity<List<Restaurante>> listarPorValorDeTaxaENome(@RequestParam(required = false) String nome,
                                                                       @RequestParam(required = false ) BigDecimal taxaInicial,
                                                                       @RequestParam(required = false) BigDecimal taxaFinal) {

        List<Restaurante> restaurantes = cadastroRestauranteService.buscaPorValorDaTaxaENome(nome, taxaInicial, taxaFinal);

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping(value = "/buscar-por-nome-id")
    public ResponseEntity<List<Restaurante>> listarPorNomeId(@RequestParam String nome,
                                                                  @RequestParam Long id) {

        List<Restaurante> restaurantes = cadastroRestauranteService.buscarRestaurantePorNomeECozinhaId(nome, id);

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping(value = "/buscar-primeiro-por-nome")
    public ResponseEntity<Restaurante> buscarPrimeiroPorNome(@RequestParam String nome) {

        Restaurante restaurante = cadastroRestauranteService.retornarOPrimeiroEncontradoPorNome(nome);

        return ResponseEntity.ok(restaurante);
    }

    @GetMapping(value = "/top2-por-nome")
    public ResponseEntity<List<Restaurante>> top2PorNome(@RequestParam String nome) {

        List<Restaurante> restaurante = cadastroRestauranteService.top2PorNome(nome);

        return ResponseEntity.ok(restaurante);
    }

    @GetMapping(value = "/total-por-cozinha")
    public int totalPorCozinha(@RequestParam Long id) {

        int totalRestaurantes = cadastroRestauranteService.totalDeRestaurantePorCozinha(id);

        return totalRestaurantes;
    }

    @GetMapping(value = "/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome){

        return cadastroRestauranteService.restaurantesComFiltros(nome);
    }

    @GetMapping(value = "/buscar-primeiro")
    public Optional<Restaurante> buscarPrimeiro(){

        return cadastroRestauranteService.buscarPrimeiro();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante;
        try {
            restaurante = cadastroRestauranteService.buscar(id);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurante);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = cadastroRestauranteService.buscar(id);

        if (Objects.isNull(restauranteAtual)) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(id, restauranteAtual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cadastroRestauranteService.remover(id);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.noContent().build();
    }


    private static void merge(Map<String, Object> campos, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}