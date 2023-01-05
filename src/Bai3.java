import java.util.Scanner;

public class Bai3 {
    public static boolean isLeapYear(int year){
        return ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap nam: ");
        int year = Integer.parseInt(scanner.nextLine());
        if (isLeapYear(year)){
            System.out.println("Nhuan");
        }
        else {
            System.out.println("Khong nhuan");
        }
    }
}
