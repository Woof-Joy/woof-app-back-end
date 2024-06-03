package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.domain.entity.Cliente;
import org.woof.woofjoybackend.dto.RelatorioDTO;
import org.woof.woofjoybackend.dto.ServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.mapper.ServicoMapper;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Servico;
import org.woof.woofjoybackend.repository.ServicoRepository;
import org.woof.woofjoybackend.service.users.ServiceUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServico {
    private final ServicoRepository servicoRepository;
    private final ServiceFichaServico serviceFichaServico;
    private final ServiceUser serviceUser;


    public Servico post(ServicoCriacaoDTO servico) {
        FichaServico ficha = serviceFichaServico.getByArgs(servico.getIdParceiro(), servico.getTipoServico()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Cliente cliente = serviceUser.findByClientId(servico.getIdCliente()).getCliente().orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Servico servicoSalvo = servicoRepository.save(ServicoMapper.toEntity(servico, ficha, cliente));
        return servicoSalvo;
    }

    public RelatorioDTO putRelatorio(String mensagem, Integer idServico){
        if (!servicoRepository.existsById(idServico)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), mensagem = "Servico não encontrado");
        }
        Servico servicoFinalizado = servicoRepository.getById(idServico);
        servicoFinalizado.setRelatorio(mensagem);
        RelatorioDTO relatorio = new RelatorioDTO();
        relatorio.setRelatorioTxt(servicoFinalizado.getRelatorio());
        servicoRepository.save(servicoFinalizado);

        return relatorio;
    }


    public Servico patch(Integer id) {
        Servico servicoOriginal = servicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

        if (servicoOriginal.getStatus().equalsIgnoreCase("Aguardando Confirmação")) {
            servicoOriginal.setStatus("Em andamento");
        } else  {
            servicoOriginal.setStatus("Concluído");
        }
        return servicoRepository.save(servicoOriginal);
    }

    public Servico getById(Integer id) {
        return servicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public List<Servico> getByIdCliente(Integer id) {
        Cliente c = new Cliente();
        c.setIdCliente(id);
        return servicoRepository.findByCliente(c);
    }

    public List<Servico> getAll() {
        return servicoRepository.findAll();
    }

    public void delete(Integer id) {
        if (!servicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        servicoRepository.deleteById(id);
    }
}
