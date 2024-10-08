package barriga.service;


import barriga.domain.Conta;
import barriga.domain.exceptions.ValidationException;
import barriga.service.external.ContaEvent;
import barriga.service.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;

import static barriga.domain.builders.ContaBuilder.umaConta;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ContaServiceTest {
    @InjectMocks
    private ContaService service;
    @Mock
    private ContaRepository repository;
    @Mock private ContaEvent event;


    @Test
    public void deveSalvarPrimeiraContaComSucesso() {
        Conta contaToSave = umaConta().comID(null).agora();
        Mockito.when(repository.salvar(contaToSave)).thenReturn(umaConta().agora());
        Mockito.doNothing().when(event).dispatch(umaConta().agora(), ContaEvent.EventType.CREATED);

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.getId());
    }

    @Test
    public void deveRejeitarContaRepetida() {
        Conta contaToSave = umaConta().comID(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.getUsuario().getID())).thenReturn(Arrays.asList(umaConta().agora()));
//        Mockito.when(repository.salvar(contaToSave)).thenReturn(umaConta().agora());

        String mensagem = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(contaToSave)).getMessage();

        Assertions.assertEquals("Usuário já possui uma conta com este nome", mensagem);
    }

    @Test
    public void deveSalvarSegundaContaComSucesso() {
        Conta contaToSave = umaConta().comID(null).agora();
        Mockito.when(repository.obterContasPorUsuario(contaToSave.getUsuario().getID()))
                .thenReturn(Arrays.asList(umaConta().comNome("Outra conta").agora()));
        Mockito.when(repository.salvar(contaToSave)).thenReturn(umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.getId());
    }

    @Test
    public void naoDeveManterContaSemEvento() {
        Conta contaToSave = umaConta().comID(null).agora();
        Conta contaSalva = umaConta().agora();
        Mockito.when(repository.salvar(contaToSave)).thenReturn(contaSalva);
        Mockito.doThrow(new Exception("Falha catastrófica")).when(event).dispatch(umaConta().agora(), ContaEvent.EventType.CREATED);

        String mensagem = Assertions.assertThrows(Exception.class, () ->
                service.salvar(contaToSave)).getMessage();

        Assertions.assertEquals("Falha na criação da conta, tente novamente", mensagem);

        Mockito.verify(repository).delete(contaSalva);
    }
 }
