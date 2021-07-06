import java.util.Scanner;

public class OrdenaçaoDados {
    // Ex1.: O usuario entra com dois valores e o sistema os ordema.
    public static void main(String[] args) {
        Scanner infor = new Scanner(System.in);
        int x,y;

        System.out.println("Digite o valor de x: ");
        x = infor.nextInt();
        System.out.println("Digite o valor de y: ");
        y = infor.nextInt();

        if (x>y) {
            System.out.println(y);
            System.out.println(x);
        } else {
            System.out.println(y);
            System.out.println(x);
        }

    }
}
