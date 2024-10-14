import barriga.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static barriga.domain.builders.UsuarioBuilder.umUsuario;

@ExtendWith(MockitoExtension.class)
public class CalculadoraMockTest {

    @Spy
    private Calculadora calc;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void test() {
        Mockito.when(calc.soma(Mockito.anyInt(), Mockito.eq(2))).thenReturn(5).thenReturn(6).thenCallRealMethod();

        Mockito.when(repository.salvar(Mockito.any())).thenReturn(umUsuario().agora());

        System.out.println(calc.soma(1, 1));
        System.out.println(calc.soma(1, 2));
        System.out.println(calc.soma(10, 2));
        System.out.println(calc.soma(14, 2));
        System.out.println(calc.soma(167, 2));
        System.out.println(repository.salvar(null));

    }
}
