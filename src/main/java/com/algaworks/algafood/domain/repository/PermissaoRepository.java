package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao salvar(Permissao permissao);

    Permissao buscar(Long id);

    void remover(Permissao permissao);

}
