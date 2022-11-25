package com.arquetipo.inicial.util;

import com.arquetipo.inicial.model.entity.UsuarioEntity;
import com.google.gson.JsonObject;

public class TokenUtils {

   public  static String formAuthorities(UsuarioEntity pUsuarioEntity) {
        JsonObject authorities = new JsonObject();
        authorities.addProperty("id", pUsuarioEntity.getIdUsuario());
       authorities.addProperty("username", pUsuarioEntity.getNombreUsuario());
        authorities.addProperty("perfil", pUsuarioEntity.getIdPerfil() != null ? pUsuarioEntity.getIdPerfil().getNombre() : "");
        return authorities.toString();

    }

}
