package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();

    FormaPagamento salvar(FormaPagamento formaPagamento);

    FormaPagamento buscar(Long id);

    void remover(FormaPagamento formaPagamento);

}
