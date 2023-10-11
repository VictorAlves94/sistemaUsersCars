package com.sistemaUsersCars.api.com.sistemaUsersCars.api.service;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosAtualizacaoUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosCadasUsuario;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.DadosListagemUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
               usuario.getPhone()
               , usuario.getCars()
       );
       return  dados;

    }
    public DadosDetalhamentoUsers cadastrarUsuario(DadosCadasUsuario dadosCadastroUsuario){

        Users usuarioCadastro = converterCadastroEntidade(dadosCadastroUsuario);
        var usuarioCadastrado = usuarioRepository.save(usuarioCadastro);
        var usuarioDevolver = converterUsersParaDadosDetalharUsuario(usuarioCadastrado);

        return usuarioDevolver;
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
    private DadosDetalhamentoUsers converterUsersParaDadosDetalharUsuario(Users usuarioCadastrado) {
        DadosDetalhamentoUsers usuario = new DadosDetalhamentoUsers(usuarioCadastrado.getId(),usuarioCadastrado.getFirstName(), usuarioCadastrado.getLastName(),
                usuarioCadastrado.getEmail(),usuarioCadastrado.getBirthday(),usuarioCadastrado.getLogin(), usuarioCadastrado.getPhone(),
                usuarioCadastrado.getCars()
        );

        return usuario;
    }

    private static Users converterDadosAtualizacaoUsers(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        Users usuario = new Users();
        usuario.setFirstName(dadosAtualizacaoUsuario.firstName());
        usuario.setLastName(dadosAtualizacaoUsuario.lastName());
        usuario.setEmail(dadosAtualizacaoUsuario.email());
        usuario.setBirthday(dadosAtualizacaoUsuario.birthday());
        usuario.setLogin(dadosAtualizacaoUsuario.login());
        usuario.setPassword(dadosAtualizacaoUsuario.password());
        usuario.setPhone(dadosAtualizacaoUsuario.phone());
        usuario.setCars(dadosAtualizacaoUsuario.cars());
        return usuario;
    }



    private Users buscarUsuarioDb(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));
    }


    private Users converterCadastroEntidade(DadosCadasUsuario  cadastroUsuario){
        Users users = new Users();
        users.setFirstName(cadastroUsuario.firstName());
        users.setLastName(cadastroUsuario.lastName());
        users.setEmail(cadastroUsuario.email());
        users.setBirthday(cadastroUsuario.birthday());
        users.setLogin(cadastroUsuario.login());
        users.setPassword(cadastroUsuario.password());
        users.setPhone(cadastroUsuario.phone());
        return users;
    }



}
