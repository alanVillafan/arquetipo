package com.arquetipo.inicial.service.impl;

import com.arquetipo.inicial.model.entity.UsuarioEntity;
import com.arquetipo.inicial.repository.IUsuarioRepository;
import com.arquetipo.inicial.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public UsuarioEntity findByNombreUsuario(String pNombreUsuario) throws Exception {
        Optional<UsuarioEntity> mOptionalUsuarioEntity = this.iUsuarioRepository.findByNombreUsuario(pNombreUsuario);
        if (mOptionalUsuarioEntity.isPresent()) {
            return mOptionalUsuarioEntity.get();
        } else {
            throw new Exception("No se encontro el usuario con el nombre: " + pNombreUsuario);
        }
    }
}
