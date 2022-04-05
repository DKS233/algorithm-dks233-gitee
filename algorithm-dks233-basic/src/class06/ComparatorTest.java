package class06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 比较器
 *
 * @author dks233
 * @create 2022-04-04-20:55
 */
public class ComparatorTest {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(23, 100));
        list.add(new Student(24, 99));
        list.add(new Student(24, 100));
        list.sort(new AgeComparator());
        System.out.println(list); // 23+100 24+99 24+100
        list.sort(new ScoreComparator());
        System.out.println(list); // 24+99 23+100 24+100
        list.sort(new CustomComprator());
        System.out.println(list); // 24+100 24+99 23+100
        TreeMap<Student, String> treeMap = new TreeMap<>(new CustomComprator());
        treeMap.put(new Student(23, 100), "小段");
        treeMap.put(new Student(24, 99), "中段");
        treeMap.put(new Student(24, 88), "大段");
        treeMap.put(new Student(24, 99), "大段");
        // {Student{age=24, score=99}=大段, Student{age=24, score=88}=大段, Student{age=23, score=100}=小段}
        System.out.println(treeMap);
    }

    // 比较器遵循规范（升序和降序都遵循）
    // compare方法中，返回负数，认为第一个参数应该排前面
    // compare方法中，返回正数，认为第二个参数应该排前面
    // compare方法中，返回0，认为无所谓谁排前面
    public static class AgeComparator implements Comparator<Student> {
        // age升序
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static class ScoreComparator implements Comparator<Student> {
        // score升序
        @Override
        public int compare(Student o1, Student o2) {
            return o1.score - o2.score;
        }
    }

    public static class CustomComprator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            // age不一样按照age排，age不一样按照score排
            // score和age降序
            return o1.age != o2.age ? o2.age - o1.age : o2.score - o1.score;
        }
    }

    public static class Student {
        private final int age;
        private final int score;

        private Student(int age, int score) {
            this.age = age;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", score=" + score +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return age == student.age &&
                    score == student.score;
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, score);
        }
    }
}
