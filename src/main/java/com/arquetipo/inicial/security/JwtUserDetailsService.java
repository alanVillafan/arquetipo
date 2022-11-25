package com.arquetipo.inicial.security;

import com.arquetipo.inicial.model.entity.UsuarioEntity;
import com.arquetipo.inicial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String pUsername) throws UsernameNotFoundException {
        Optional<UsuarioEntity> mOptionalUsuarioEntity = this.iUsuarioRepository.findByNombreUsuario(pUsername);
        List<GrantedAuthority> mListGrantedAuthorities = new ArrayList<>();

        if (mOptionalUsuarioEntity.isPresent()) {
            UsuarioEntity mUsuarioEntity = mOptionalUsuarioEntity.get();
            if(mUsuarioEntity.getIdPerfil()!=null){

                mListGrantedAuthorities.add(new SimpleGrantedAuthority(mUsuarioEntity.getIdPerfil().getNombre()));
            }
            return new User(pUsername, mOptionalUsuarioEntity.get().getPassword(), mListGrantedAuthorities);
        } else {
            throw new UsernameNotFoundException("No se encontro el Usuario con el nombre: " + pUsername);
        }
    }
}
