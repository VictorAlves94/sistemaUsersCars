package com.sistemaUsersCars.api.com.sistemaUsersCars.api.service;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosListagemUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsersRepository usuarioRepository;

    public void desativarUsuario(Long id){
        var usuario = buscarUsuarioDb(id);
        usuario.setAtivo(false);
    }

    public Page<DadosListagemUsers> listarTodos(Pageable paginacao) {
        Page<Users> pagina = usuarioRepository.findAll(paginacao);
        List<Users> usersList = pagina.getContent();

        List<DadosListagemUsers> dadosList = new ArrayList<>();

        usersList.forEach( usuario-> {
            DadosListagemUsers dados = new DadosListagemUsers(usuario.getId(),
                    usuario.getFirstName(),
                    usuario.getLastName(),
                    usuario.getEmail(),
                    usuario.getBirthday(),
                    usuario.getLogin(),
                    usuario.getPhone(),
                    usuario.getCars());

            dadosList.add(dados);
        });

        return new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    public DadosDetalhamentoUsers buscarUsuario(Long id){
       var usuario = buscarUsuarioDb(id);

       DadosDetalhamentoUsers dados = new DadosDetalhamentoUsers(
               usuario.getId(),
               usuario.getFirstName(),
               usuario.getLastName(),
               usuario.getEmail(),
               usuario.getBirthday(),
               usuario.getLogin(),
               usuario.getPhone(),
               usuario.getCars()
       );
       return  dados;

    }

    public DadosAtualizacaoUsuario atualizacaoUsuario(Long id, DadosAtualizacaoUsuario dadosAtualizacaoUsuario){

        Users usuarioNovo = converterDadosAtualizacaoUsers(dadosAtualizacaoUsuario);
        var usuarioAntigo = buscarUsuarioDb(id);

        BeanUtils.copyProperties(usuarioNovo,usuarioAntigo);
        Users usuarioAtualizado = usuarioRepository.save(usuarioAntigo);

        var dadosAtualizados = converterUsersParaDadosAtualizacao(usuarioAtualizado);
        return dadosAtualizados;
    }

    private DadosAtualizacaoUsuario converterUsersParaDadosAtualizacao(Users usuarioAtualizado) {
        DadosAtualizacaoUsuario dadosAtualizacaoUsuario = new DadosAtualizacaoUsuario(usuarioAtualizado.getFirstName(), usuarioAtualizado.getLastName(),
                usuarioAtualizado.getEmail(),usuarioAtualizado.getBirthday(),usuarioAtualizado.getLogin(),usuarioAtualizado.getPassword(),
                usuarioAtualizado.getPhone(),usuarioAtualizado.getCars()

        );
        return dadosAtualizacaoUsuario;
    }

    private static Users converterDadosAtualizacaoUsers(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        Users usuario = new Users();
        usuario.setFirstName(dadosAtualizacaoUsuario.getFirstName());
        usuario.setLastName(dadosAtualizacaoUsuario.getLastName());
        usuario.setEmail(dadosAtualizacaoUsuario.getEmail());
        usuario.setBirthday(dadosAtualizacaoUsuario.getBirthday());
        usuario.setLogin(dadosAtualizacaoUsuario.getLogin());
        usuario.setPassword(dadosAtualizacaoUsuario.getPassword());
        usuario.setPhone(dadosAtualizacaoUsuario.getPhone());
        usuario.setCars(dadosAtualizacaoUsuario.getCars());
        return usuario;
    }



    private Users buscarUsuarioDb(Long id) {
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



}
