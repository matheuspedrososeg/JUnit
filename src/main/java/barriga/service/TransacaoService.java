package barriga.service;

import barriga.domain.Transacao;
import barriga.domain.exceptions.ValidationException;
import barriga.service.external.ClockService;
import barriga.service.repositories.TransacaoDAO;

import java.time.LocalDateTime;

public class TransacaoService implements TransacaoDAO {

    private TransacaoDAO dao;
    private ClockService clockService;

    public Transacao salvar(Transacao transacao) {
        if (getTime().getHour() > 15) throw new RuntimeException("Tente novamente amanhã");
        validarCamposObrigatorios(transacao);

        return dao.salvar(transacao);
    }

    private static void validarCamposObrigatorios(Transacao transacao) {
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição Inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor Inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data Inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta Inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);
    }

    protected LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
