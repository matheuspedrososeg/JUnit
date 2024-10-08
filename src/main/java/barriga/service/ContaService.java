package barriga.service;

import barriga.domain.Conta;
import barriga.domain.exceptions.ValidationException;
import barriga.service.external.ContaEvent;
import barriga.service.repositories.ContaRepository;

import java.util.List;

public class ContaService {
    private ContaRepository repository;
    private ContaEvent event;

    public ContaService(ContaRepository repository, ContaEvent event) {
        this.repository = repository;
        this.event = event;
    }

    public Conta salvar(Conta conta) {
        List<Conta> contas = repository.obterContasPorUsuario(conta.getUsuario().getID());
        contas.stream().forEach(contaExistente -> {
            if (conta.getNome().equalsIgnoreCase(contaExistente.getNome())) throw new ValidationException("Usuário já possui uma conta com este nome");
        });
        Conta contaPersistida = repository.salvar(conta);
        event.dispatch(contaPersistida, ContaEvent.EventType.CREATED);
        return contaPersistida;
    }
}
