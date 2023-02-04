package Comparator;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MyComparator {
    public static class Student{
        public int id;
        public String name;
        public int age;
        public Student(int id, String name, int age){
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class AgeAscending implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static class IdAscending implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static void main(String[] args) {

        Student student1 = new Student(3, "A", 24);
        Student student2 = new Student(2, "C", 18);
        Student student3 = new Student(1, "B", 32);
        Student student4 = new Student(4, "G", 47);
        Student[] students = new Student[]{student1, student2, student3, student4};


        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id == o2.id ? o2.age-o1.age : o1.id-o2.id;
            }
        });

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println(studentList.toString());
    }
}
