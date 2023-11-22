package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping(value = "/por-nome")
    public List<Cozinha> listarPorNome(@RequestParam(name = "nome") String nome) {
        return cozinhaService.listarPorNome(nome);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaService.buscar(id);

        return ResponseEntity.ok(cozinha);
    }

    @GetMapping(value = "/busca-por-nome/{nome}")
    public ResponseEntity<Cozinha> buscarPorNome(@PathVariable String nome) {
        Cozinha cozinha = cozinhaService.buscarPorNome(nome);

        return ResponseEntity.ok(cozinha);
    }

    @GetMapping(value = "/top2-por-nome")
    public List<Cozinha> buscaTop2PorNome(@RequestParam String nome) {
        return cozinhaService.buscaTop2PorNome(nome);
    }

    @GetMapping(value = "/exists-por-nome")
    public boolean existePorNome(String nome) {
        return cozinhaService.existeCozinhaPorNome(nome);
    }
    @GetMapping(value = "/buscar-primeiro")
    public Optional<Cozinha> buscarPrimeiro(String nome) {
        return cozinhaService.buscarPrimeiro();
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
        Cozinha response = cozinhaService.salvar(cozinha);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinhaRequest) {
        Cozinha cozinha = cozinhaService.buscar(id);

        BeanUtils.copyProperties(cozinhaRequest, cozinha, "id");

        Cozinha cozinhaRetorno = cozinhaService.salvar(cozinha);

        return ResponseEntity.ok(cozinhaRetorno);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            cozinhaService.remover(id);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();
    }
}
