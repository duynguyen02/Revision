public class Bai5 {
    public static String ganZhiYear(int year){
        String []zhi = {"Thân","Dậu" ,"Tuất", "Hợi","Tý", "Sửu", "Dần", "Mão", "Thìn", "Tị", "Ngọ", "Mùi"};
        String []gan = {"Canh", "Tân", "Nhâm", "Quý","Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        return gan[year % 10] + " " + zhi[year % 12];
    }
    public static void main(String[] args) {
        System.out.println(ganZhiYear(2023));
    }
}
