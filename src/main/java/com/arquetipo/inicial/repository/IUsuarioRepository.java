package com.arquetipo.inicial.repository;

import com.arquetipo.inicial.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity , Integer> {

    @Query("SELECT u FROM UsuarioEntity u where u.nombreUsuario = :username")
    Optional<UsuarioEntity> findByNombreUsuario(@Param("username") String username);
}
