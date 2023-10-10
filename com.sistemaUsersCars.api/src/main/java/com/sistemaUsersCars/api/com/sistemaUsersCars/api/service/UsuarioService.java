package com.sistemaUsersCars.api.com.sistemaUsersCars.api.service;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosListagemUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UsuarioService {
    @Autowired
    UsersRepository usuarioRepository;

    public void desativarUsuario(Long id){
        var usuario = buscarUsuario(id);
        usuario.setAtivo(false);
    }

    private Users buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));
    }

    private Users converterCadastroEntidade(DadosCadasUsuario  cadasUsuario){
        Users users = new Users();
                users.setFirstName(cadasUsuario.getFirstName());
                users.setLastName(cadasUsuario.getLastName());
                users.setEmail(cadasUsuario.getEmail());
                users.setBirthday(cadasUsuario.getBirthday());
                users.setLogin(cadasUsuario.getLogin());
                users.setPassword(cadasUsuario.getPassword());
                users.setPhone(cadasUsuario.getPhone());
        return users;
    }

    private Users atualizacaoUsuario(DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        Users usuario = new Users();
        usuario.setFirstName(dadosAtualizacaoUsuario.getFirstName());
        usuario.setLastName(dadosAtualizacaoUsuario.getLastName());
        usuario.setEmail(dadosAtualizacaoUsuario.getEmail());
        usuario.setBirthday(dadosAtualizacaoUsuario.getBirthday());
        usuario.setLogin(dadosAtualizacaoUsuario.getLogin());
        usuario.setPassword(dadosAtualizacaoUsuario.getPassword());
        usuario.setPhone(dadosAtualizacaoUsuario.getPhone());

        return ;
    }


    public Page<DadosListagemUsers> listarTodos(Pageable paginacao) {
        Page<Users> pagina = usuarioRepository.findAll(paginacao);
        List<Users> usersList = pagina.getContent();

        List<DadosListagemUsers> dadosList = new ArrayList<>();

        usersList.forEach(usuario-> {
        DadosListagemUsers dados = new DadosListagemUsers(usuario.getId(),
                usuario.getFirstName(),
                usuario.getLastName(),
                usuario.getEmail(),
                usuario.getBirthday(),
                usuario.getLogin(),
                usuario.getPhone(),
                (Cars) usuario.getCars());
        });

        return null;
    }
}
