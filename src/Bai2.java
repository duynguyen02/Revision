import java.text.DecimalFormat;
import java.util.Scanner;

public class Bai2 {

    public static void PTB1(int a, int b){
        if (a == 0){
            if (b == 0){
                System.out.println("Phuong trinh co vo so nghiem");
            }
            else {
                System.out.println("Phuong trinh vo nghiem");
            }
        }
        else {
            System.out.println("Phuong trinh co nghiem la: " + new DecimalFormat("#.##").format((double)  -b / a));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap a: ");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap b: ");
        int b = Integer.parseInt(scanner.nextLine());
        PTB1(a,b);

    }
}
