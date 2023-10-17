package com.sistemaUsersCars.api.com.sistemaUsersCars.api.service;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.CarroDetalhamento;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosCarroAtualizar;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosCarroCadastro;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.carroDto.DadosListarCarro;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.dto.usuarioDto.DadosDetalhamentoUsers;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository.CarsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarsService {

    @Autowired
    CarsRepository carsRepository;

    public CarroDetalhamento cadastrarCarro(DadosCarroCadastro dados) {
        Cars carroCadastro = converterDtoCarroEmEntidade(dados);
        var carroCadastrado = carsRepository.save(carroCadastro);
        var carroDevolver = converderEntidadeEmDto(carroCadastrado);
        return carroDevolver;
    }


    public Page<DadosListarCarro> listarTodos(Pageable paginacao) {
        Page<Cars> pagina = carsRepository.findAll(paginacao);
        List<Cars> listaCarro = pagina.getContent();

        List<DadosListarCarro> dadosList = new ArrayList<>();
        listaCarro.forEach( carro-> {
            DadosListarCarro dados = new DadosListarCarro(carro.getId(),
                    carro.getYearCar(), carro.getLincenseCar(),
                    carro.getColor(), carro.getModel());
                dadosList.add(dados);
                });
        return  new PageImpl<>(dadosList, pagina.getPageable(), pagina.getTotalElements());
    }

    public DadosListarCarro buscarPorId(Long id) {
        var buscarCarroDb = carsRepository.getById(id);
        DadosListarCarro dados = new DadosListarCarro(buscarCarroDb.getId(),
                buscarCarroDb.getYearCar(), buscarCarroDb.getLincenseCar(),
                buscarCarroDb.getColor(), buscarCarroDb.getModel() );

        return dados;
    }
    public DadosCarroAtualizar atualizarCarro(Long id ,DadosCarroAtualizar dadosCarroAtualizar) {
        Cars carroNovo = converterDtoCarroAtualizarEmEntidade(dadosCarroAtualizar);
        var carroAntigo = carsRepository.getById(id);

        BeanUtils.copyProperties(carroNovo,carroAntigo);
        var carroAtualizado = carsRepository.save(carroAntigo);
        var devolverCarroAtt = converderCarroAttParaDto(carroAtualizado);
        return devolverCarroAtt;
    }
    public void deletar(Long id) {
        var carro = carsRepository.getById(id);
        carsRepository.delete(carro);
    }

    private DadosCarroAtualizar converderCarroAttParaDto(Cars carroAtualizado) {
        DadosCarroAtualizar dadosCarroAtualizar = new DadosCarroAtualizar(
                carroAtualizado.getId(), carroAtualizado.getYearCar(), carroAtualizado.getLincenseCar(),
                carroAtualizado.getColor(), carroAtualizado.getModel()
        );
        return dadosCarroAtualizar;
    }

    private Cars converterDtoCarroAtualizarEmEntidade(DadosCarroAtualizar dadosCarroAtualizar) {
        Cars carro = new Cars();
        carro.setYearCar(dadosCarroAtualizar.yearCar());
        carro.setLincenseCar(dadosCarroAtualizar.LincenseCar());
        carro.setColor(dadosCarroAtualizar.Color());
        carro.setModel(dadosCarroAtualizar.model());
       return carro;
    }


    private CarroDetalhamento converderEntidadeEmDto(Cars carroCadastrado) {
        CarroDetalhamento carroDetalhamento = new CarroDetalhamento(carroCadastrado.getId(),
                carroCadastrado.getYearCar(), carroCadastrado.getLincenseCar(),
                carroCadastrado.getModel(),carroCadastrado.getColor());

        return carroDetalhamento;
    }

    private Cars converterDtoCarroEmEntidade(DadosCarroCadastro dados) {
        Cars carro = new Cars();
        carro.setYearCar(dados.yearCar());
        carro.setLincenseCar(dados.LincenseCar());
        carro.setModel(dados.model());
        carro.setColor(dados.Color());
        return carro;
    }


}
