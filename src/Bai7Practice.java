import java.io.*;
import java.util.*;

public class Bai7Practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager1 studentManager1 = new StudentManager1();

        int option;
        while (true){
            try {
                System.out.println("Them [1]");
                System.out.println("Sua [2]");
                System.out.println("Xoa [3]");
                System.out.println("Sap xep theo ten [4]");
                System.out.println("Sap xep theo GPA [5]");
                System.out.println("Hien thi ds hoc sinh [6]");
                System.out.println("Thoat [0]");
                System.out.println("Nhap lua chon: ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 0 -> {
                        System.exit(0);
                        break;
                    }
                    case 1 -> {
                        studentManager1.add();
                        break;
                    }
                    case 2 -> {
                        System.out.println("Nhap id can sua");
                        int id = Integer.parseInt(scanner.nextLine());
                        studentManager1.edit(id);
                        break;
                    }
                    case 3 -> {
                        System.out.println("Nhap id can xoa");
                        int id = Integer.parseInt(scanner.nextLine());
                        studentManager1.delete(id);
                        break;
                    }
                    case 4 -> {
                        studentManager1.sortByName();
                        break;
                    }
                    case 5 -> {
                        studentManager1.sortByGPa();
                        break;
                    }
                    case 6 -> {
                        studentManager1.show();
                        break;
                    }
                    default -> {
                        throw new Exception();
                    }
                }
            }catch (Exception e){
                System.out.println("Gia tri khong hop le!");
                e.printStackTrace();
            }
        }

    }
}
class Student1 implements Serializable {
    private int id;
    private String name;
    private String address;
    private float gpa;

    public Student1(int id, String name, String address, float gpa) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}

class StudentDao1{
    public static String STUDENT_FILE_PATH = "student.txt";

    public void write(List<Student1> student1s){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(STUDENT_FILE_PATH);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(student1s);

            fileOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Student1> read(){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        List<Student1> student1s = null;

        if (!new File(STUDENT_FILE_PATH).exists()){
            return new ArrayList<>();
        }

        try {
            fileInputStream = new FileInputStream(STUDENT_FILE_PATH);
            objectInputStream = new ObjectInputStream(fileInputStream);

            student1s = (List<Student1>) objectInputStream.readObject();

            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return student1s;

    }

}

class SortStudentByGPA1 implements Comparator<Student1>{

    @Override
    public int compare(Student1 o1, Student1 o2) {
        if (o1.getGpa() == o2.getGpa()){
            return 0;
        }
        return (o1.getGpa() < o2.getGpa() ? 1 : -1);
    }
}

class SortStudentByName1 implements Comparator<Student1>{

    @Override
    public int compare(Student1 o1, Student1 o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class StudentManager1{
    public static Scanner scanner = new Scanner(System.in);
    private List<Student1> student1List;
    private StudentDao1 studentDao;

    public StudentManager1(){
        studentDao = new StudentDao1();
        student1List = studentDao.read();
    }

    public Student1 inputStudentInfo(int id) throws Exception {
        System.out.println("Nhap ten: ");
        String name = scanner.nextLine();
        System.out.println("Nhap tuoi: ");
        int age = Integer.parseInt(scanner.nextLine());
        if (age < 1){
            throw new Exception();
        }
        System.out.println("Nhap dia chi: ");
        String address = scanner.nextLine();
        System.out.println("Nhap GPA: ");
        float gpa = Float.parseFloat(scanner.nextLine());
        if (gpa < 0 || gpa > 10){
            throw new Exception();
        }

        return new Student1(id, name, address, gpa);

    }

    public void add(){
        try {
            int id = (student1List.size() == 0) ? 1 : student1List.get(student1List.size() - 1).getId() + 1;
            student1List.add(inputStudentInfo(id));
            studentDao.write(student1List);

        }catch (Exception e){
            System.out.println("Gia tri khong hop le");
            e.printStackTrace();
        }

    }

    public void edit(int id){
        for (Student1 student1 : student1List){
            if(student1.getId() == id){
                try {
                    Student1 student1Temp = inputStudentInfo(id);
                    student1.setName(student1Temp.getName());
                    student1.setAddress(student1Temp.getAddress());
                    student1.setGpa(student1Temp.getGpa());
                    studentDao.write(student1List);
                    System.out.println("Sua thanh cong!");
                    return;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Khong tim thay id!");
    }

    public void delete(int id){
        Student1 student1Temp = null;
        for(Student1 student1 : student1List){
            if(student1.getId() == id){
                student1Temp = student1;
                break;
            }
        }
        if (student1Temp != null){
            student1List.remove(student1Temp);
            System.out.println("Xoa thanh cong!");
        }
        else {
            System.out.println("Khong tim thay id!");
        }
    }

    public void sortByName(){
        student1List.sort(new SortStudentByName1());
        System.out.println("Sap xep thanh cong");
    }

    public void sortByGPa(){
        student1List.sort(new SortStudentByGPA1());
        System.out.println("Sap xep thanh cong");
    }

    public void show(){
        for(Student1 student1 : student1List){
            System.out.println(student1);
        }
    }


}