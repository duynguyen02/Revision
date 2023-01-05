import java.io.*;
import java.util.*;

public class Bai7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        int option;
        while (true) {
            System.out.println("[1] Add student.");
            System.out.println("[2] Edit student by id.");
            System.out.println("[3] Delete student by id.");
            System.out.println("[4] Sort student by gpa.");
            System.out.println("[5] Sort student by name.");
            System.out.println("[6] Show student.");
            System.out.println("[0] Exit.");
            System.out.println("Choose your option:");
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option){
                    case 1:{
                        System.out.println("Nhap ten: ");
                        String name = scanner.nextLine();
                        System.out.println("Nhap tuoi: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println("Nhap GPA: ");
                        float gpa = Float.parseFloat(scanner.nextLine());
                        if (studentManager.add(name, age, gpa)){
                            System.out.println("Them thanh cong!");
                        }
                        else {
                            System.out.println("Co loi xay ra!");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Nhap id: ");
                        int id = Integer.parseInt(scanner.nextLine());

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid option!");
            }
        }

    }
}

class Student implements Serializable {
    private int id;
    private String name;
    private int age;
    private float gpa;

    public Student(int id, String name, int age, float gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                '}';
    }
}

class StudentDao {
    private static final String STUDENTS_FILE_PATH = "student.txt";

    public void write(List<Student> studentList) {

        FileOutputStream fos = null;

        ObjectOutputStream oos = null;

        try {

            fos = new FileOutputStream(new File(STUDENTS_FILE_PATH));

            oos = new ObjectOutputStream(fos);

            oos.writeObject(studentList);

            fos.close();
            oos.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public List<Student> read() {

        List<Student> studentList = new ArrayList<>();

        FileInputStream fis = null;

        ObjectInputStream ois = null;

        try {

            fis = new FileInputStream(new File(STUDENTS_FILE_PATH));

            ois = new ObjectInputStream(fis);

            studentList = (List<Student>) ois.readObject();

            fis.close();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

        }

        return studentList;

    }
}

class SortStudentByGPA implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getGpa() == o2.getGpa()){
            return 0;
        }
        return (o1.getGpa() > o2.getGpa()) ? 1 : -1;
    }
}

class SortStudentByName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class StudentManager {
    public static Scanner scanner = new Scanner(System.in);
    private List<Student> students;
    private StudentDao studentDao;

    public StudentManager(){
        studentDao = new StudentDao();
        students = this.studentDao.read();
    }

    public boolean add(String name, int age, float GPA){
        try {
            int id = (students.size() > 0) ? (students.get(students.size() - 1).getId() + 1) : 1;
            students.add(new Student(id, name, age, GPA));
            studentDao.write(students);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean edit(int id, String name, int age, float GPA){
        for(Student student : students){
            if (student.getId() == id){
                student.setName(name);
                student.setAge(age);
                student.setGpa(GPA);
                studentDao.write(students);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id){
        Student studentTemp = null;
        for(Student student : students){
            if (student.getId() == id){
                studentTemp = student;
            }
        }

        if(studentTemp != null){
            students.remove(studentTemp);
            studentDao.write(students);
            return true;
        }

        return false;

    }

    public void sortStudentByName() {
        students.sort(new SortStudentByName());
    }

    public void sortStudentByGPA() {
        students.sort(new SortStudentByGPA());
    }

    public void show(){
        for (Student student : students){
            System.out.println(student);
        }
    }

}