package com.arquetipo.inicial.controller;

import com.arquetipo.inicial.model.entity.UsuarioEntity;
import com.arquetipo.inicial.model.request.LoginRequestDTO;
import com.arquetipo.inicial.security.JwtUserDetailsService;
import com.arquetipo.inicial.security.TokenManager;
import com.arquetipo.inicial.service.IUsuarioService;
import com.arquetipo.inicial.util.MsException;
import com.arquetipo.inicial.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping
    ResponseEntity<?> authentication(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            log.info(this.passwordEncoder.encode("Hola123$"));
            authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            UserDetails mUserDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
            UsuarioEntity mUsuarioEntity = this.iUsuarioService.findByNombreUsuario(loginRequestDTO.getUsername());

            String authorities = TokenUtils.formAuthorities(mUsuarioEntity);
            final String mStringToken = tokenManager.generateToken(mUserDetails,authorities);
            return new ResponseEntity<>(mStringToken, HttpStatus.OK);

        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>("Algo salio Mal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private void authenticate(String username, String password) throws MsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new MsException(e.getMessage(), "EC001");
        } catch (BadCredentialsException e) {
            throw new MsException(e.getMessage(), "EA001");
        }
    }
}
