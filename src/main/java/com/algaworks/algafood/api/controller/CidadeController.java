package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/cidades", consumes = APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @PostMapping()
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {

        Cidade cidadeRetorno = cadastroCidadeService.salvar(cidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeRetorno);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {

        try {
            Cidade cidadeRetorno = cadastroCidadeService.buscar(id);

            BeanUtils.copyProperties(cidade, cidadeRetorno, "id");

            return ResponseEntity.ok(cidadeRetorno);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Cidade>> listar() {

        List<Cidade> cidades = cadastroCidadeService.listar();

        return ResponseEntity.ok(cidades);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        try {
            Cidade cidade = cadastroCidadeService.buscar(id);
            return ResponseEntity.ok(cidade);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        try {
            cadastroCidadeService.remover(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
