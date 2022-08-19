package acm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 牛客ACM模式练习：https://ac.nowcoder.com/acm/contest/5652
 *
 * @author dks233
 * @create 2022-08-19-10:43
 */
public class AcmMode {
    public static class A {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    System.out.println(a + b);
                }
            }
        }
    }

    public static class B {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int t = scanner.nextInt();
                for (int count = 0; count < t; count++) {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    System.out.println(a + b);
                }
            }
        }
    }

    public static class C {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    if (a == 0 && b == 0) {
                        break;
                    }
                    System.out.println(a + b);
                }
            }
        }
    }

    public static class D {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextInt()) {
                    int count = scanner.nextInt();
                    if (count == 0) {
                        break;
                    }
                    int sum = 0;
                    for (int i = 0; i < count; i++) {
                        sum += scanner.nextInt();
                    }
                    System.out.println(sum);
                }
            }
        }
    }

    public static class E {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int count = scanner.nextInt();
                for (int i = 0; i < count; i++) {
                    int singleCount = scanner.nextInt();
                    int sum = 0;
                    for (int j = 0; j < singleCount; j++) {
                        sum += scanner.nextInt();
                    }
                    System.out.println(sum);
                }
            }
        }
    }

    public static class F {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String[] strs = scanner.nextLine().split(" ");
                    int sum = 0;
                    for (int i = 1; i < strs.length; i++) {
                        sum += Integer.parseInt(strs[i]);
                    }
                    System.out.println(sum);
                }
            }
        }
    }

    public static class G {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String[] strs = scanner.nextLine().split(" ");
                    int sum = 0;
                    for (int i = 0; i < strs.length; i++) {
                        sum += Integer.parseInt(strs[i]);
                    }
                    System.out.println(sum);
                }
            }
        }
    }

    public static class H {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int count = scanner.nextInt();
                String[] strs = new String[count];
                for (int i = 0; i < strs.length; i++) {
                    strs[i] = scanner.next();
                }
                Arrays.sort(strs);
                for (int i = 0; i < strs.length; i++) {
                    System.out.print(strs[i] + " ");
                }
            }
        }
    }

    public static class I {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String[] strs = scanner.nextLine().split(" ");
                    Arrays.sort(strs);
                    for (int index = 0; index < strs.length; index++) {
                        System.out.print(strs[index] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static class J {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String[] strs = scanner.nextLine().split(",");
                    Arrays.sort(strs);
                    for (int index = 0; index < strs.length - 1; index++) {
                        System.out.print(strs[index] + ",");
                    }
                    System.out.print(strs[strs.length - 1]);
                    System.out.println();
                }
            }
        }
    }

    public static class K {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String[] strs = scanner.nextLine().split(" ");
                    long sum = 0;
                    for (int i = 0; i < strs.length; i++) {
                        sum += Long.parseLong(strs[i]);
                    }
                    System.out.println(sum);
                }
            }
        }
    }
}
