package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/estados", consumes = APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private CadastroEstadoService cadastroEstavoService;

    @PostMapping()
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {

        Estado estadoRetorno = cadastroEstavoService.salvar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoRetorno);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado) {

        try {
            Estado estadoRetorno = cadastroEstavoService.buscar(id);

            BeanUtils.copyProperties(estado, estadoRetorno, "id");

            return ResponseEntity.ok(estadoRetorno);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Estado>> listar() {

        List<Estado> estados = cadastroEstavoService.listar();

        return ResponseEntity.ok(estados);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        try {
            Estado estado = cadastroEstavoService.buscar(id);
            return ResponseEntity.ok(estado);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        try {
            cadastroEstavoService.remover(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
