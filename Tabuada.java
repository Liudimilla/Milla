// Ex3.: Faça um programa que solicite que o usuario coloque um numero e o sistema gere a tabuada com esse numero.
import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        int contador = 1, result = 0;
        System.out.println("Entre com o numero da tabuada: ");
        int nt = x.nextInt();

        while (contador <=10) {
            result = nt * contador;
            System.out.println(nt +" X " +contador +" = " +result );
            contador ++;
        }
    }
}
