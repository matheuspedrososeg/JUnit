package barriga.service;

import barriga.domain.Transacao;
import barriga.domain.exceptions.ValidationException;
import barriga.service.repositories.TransacaoDAO;

public class TransacaoService implements TransacaoDAO {

    private TransacaoDAO dao;

    public Transacao salvar(Transacao transacao) {
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição Inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor Inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data Inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta Inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);
    }
}
