package com.paulotech.service_usuario.controllers;

import com.paulotech.service_usuario.exception.UserException;
import com.paulotech.service_usuario.model.User;
import com.paulotech.service_usuario.repositories.UserRepository;
import com.paulotech.service_usuario.service.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> criarUsuario(@RequestBody @Valid User user){
        User usuarioCriado = userService.criarUsuario(user);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> mostrarUsuarios() {
        List<User> usuarios = userService.buscarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarUsuarioPorId(@PathVariable Long id) throws UserException {
        User usuario = userService.buscarUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@RequestBody User user,
                                 @PathVariable Long id) throws Exception {
        User usuarioAtualizado = userService.atualizarUsuario(id, user);
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuarioPorId(@PathVariable Long id) throws Exception {
        userService.deletarUsuario(id);
        return new ResponseEntity<>("Usuário deletado com sucesso", HttpStatus.ACCEPTED);
    }
}
