package com.paulotech.service_usuario.service.impl;

import com.paulotech.service_usuario.exception.UserException;
import com.paulotech.service_usuario.model.User;
import com.paulotech.service_usuario.repositories.UserRepository;
import com.paulotech.service_usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User criarUsuario(User user) {
        return userRepository.save(user);
    }

    @Override
    public User buscarUsuarioPorId(Long id) throws UserException {
        Optional<User> opt=userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("usuario nao encontrado");
    }

    @Override
    public List<User> buscarTodosUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public void deletarUsuario(Long id) throws UserException {
        Optional<User> opt=userRepository.findById(id);
        if(opt.isEmpty()){
            String s = "Usuario não encontrado pelo id: " + id;
            throw new UserException("Usuario não encontrado pelo id: " + id);
        }

        userRepository.deleteById(opt.get().getId());
    }

    @Override
    public User atualizarUsuario(Long id, User user) throws UserException {
        Optional<User> opt=userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("Usuario não encontrado pelo id: " + id);
        }

        User existeUsuario=opt.get();

        existeUsuario.setNome(user.getNome());
        existeUsuario.setEmail(user.getEmail());
        existeUsuario.setCargo(user.getCargo());
        existeUsuario.setTelefone(user.getTelefone());

        return userRepository.save(existeUsuario);
    }
}
