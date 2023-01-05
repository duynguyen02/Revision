import java.util.Scanner;

public class Bai4 {
    public static boolean isLeapYear(int year){
        return ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)));
    }
    public static int numInMonth(int month, int year){
        if (month < 1 || month > 12){
            return -1;
        }

        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return 31;
            }
            case 2 -> {
                return isLeapYear(year) ? 29 : 28;
            }
            default -> {
                return 30;
            }
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap thang: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap nam: ");
        int year = Integer.parseInt(scanner.nextLine());
        int numInMonth = numInMonth(month, year);
        if (numInMonth != -1){
            System.out.println("So ngay trong thang la: " + numInMonth);
        }
        else {
            System.out.println("Gia tri khong hop le");
        }

    }
}
