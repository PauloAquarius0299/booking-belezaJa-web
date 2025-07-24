package com.paulotech.service_usuario.service;

import com.paulotech.service_usuario.exception.UserException;
import com.paulotech.service_usuario.model.User;

import java.util.List;

public interface UserService {
    User criarUsuario(User user);
    User buscarUsuarioPorId(Long id) throws UserException;
    List<User> buscarTodosUsuarios();
    void deletarUsuario(Long id) throws UserException;
    User atualizarUsuario(Long id, User user) throws UserException;
}
