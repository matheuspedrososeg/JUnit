package barriga.service.repositories;

import barriga.domain.Transacao;

public interface TransacaoDAO {
    Transacao salvar(Transacao transacao);
}
