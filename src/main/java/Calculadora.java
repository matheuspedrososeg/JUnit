

public class Calculadora {

    public int soma(int a, int b) {
        return a + b;
    }
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println(calc.soma(2,3));
        System.out.println(calc.soma(2,4));
        System.out.println(calc.soma(2,5));
        System.out.println(calc.soma(2,6));
    }
    public float dividir(int a, int b) {
        return (float) a / b;
    }
}
