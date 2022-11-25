package com.arquetipo.inicial.service;

import com.arquetipo.inicial.model.entity.UsuarioEntity;

public interface IUsuarioService {
    UsuarioEntity findByNombreUsuario(String pNombreUsuario) throws Exception;
}
