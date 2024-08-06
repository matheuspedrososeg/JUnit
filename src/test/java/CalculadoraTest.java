import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    // aula 9 e 10 introducao ao junit

    private Calculadora calc = new Calculadora();

    @Test
    public void testSomar() {
        assertTrue(calc.soma(2, 3) == 5);
        assertEquals(5, calc.soma(2, 3));
    }

    // aula 11 outras assertivas

    @Test
    public void assertivas() {
        assertEquals("Casa", "Casa");
        Assertions.assertNotEquals("Casa", "casa");
        assertTrue("casa".equalsIgnoreCase("CASA"));
        assertTrue("Casa".endsWith("sa"));
        assertTrue("Casa".startsWith("Ca"));

        List<String> s1 = new ArrayList<>();
        s1.add("coisas");
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        // Assertions.assertEquals(s1, s2);
        // Assertions.assertSame(s1, s2);
        // Assertions.assertEquals(s1, s3);

        // Assertions.assertNull(s3);
        // Assertions.fail("O teste falhou");

    }

    // aula 12 classe de equivalencia

    @Test
    public void deveRetornarNumeroDecimalNaDivisao() {
        float resultado = calc.dividir(10, 3);
        assertEquals(3.3333332538604736, resultado);
        assertEquals(3.33, resultado, 0.01);
    }

    @Test
    public void deveRetornarZeroComNumeradorZeroNaDivisao() {
        float resultado = calc.dividir(0, 2);
        assertEquals(0, resultado);
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_JUNIT4() {
        try {
            float resultado = 10 / 0;
            fail("Deveria ser lancado uma excecao na divisao");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_JUNIT5() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            float resultado = 10 / 0;
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    // aula 16 befores and afters

    @BeforeEach
    public void setup() {
        System.out.println("^^");
    }

    @AfterEach
    public void teardown() {
        System.out.println("vv");
    }

    @BeforeAll
    public static void setupAll() {
        System.out.println("--- before all ---");
    }

    @AfterAll
    public static void teardownAll() {
        System.out.println("--- Afer all ---");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "6, 2 , 3",
            "6, -2, -3",
            "10, 3, 3.3333332538604736",
            "0, 2, 0"
    })
    public void deveDividirCorretamente(int num, int den, double res) {
        float resultado = calc.dividir(num, den);
        assertEquals(res, resultado);
    }

}
