import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    // aula 9 e 10 introducao ao junit
    @Test
    public void testSomar() {
        Calculadora calc = new Calculadora();
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
        Calculadora calc = new Calculadora();
        float resultado = calc.dividir(10 , 3);
        assertEquals(3.3333332538604736, resultado);
        assertEquals(3.33, resultado, 0.01);
    }

    @Test
    public void deveRetornarZeroComNumeradorZeroNaDivisao() {
        Calculadora calc = new Calculadora();
        float resultado = calc.dividir(0, 2);
        assertEquals(0, resultado);
    }
    @Test
    public void deveLancarExcecaoQuandoDividirPorZero() {
        System.out.println("comecou");
        try {
            float resultado = 10 / 10;
            fail("Deveria ser lancado uma excecao na divisao");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
        System.out.println("terminou");
    }



}
