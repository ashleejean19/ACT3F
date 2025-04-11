
import java.util.Scanner;

class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + " - " + grade;
    }
}

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please input how many students: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for student #" + (i + 1));
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Grade: ");
            double grade = scanner.nextDouble();
            scanner.nextLine();
            students[i] = new Student(name, grade);
        }

        System.out.println("\nPlease choose a sorting algorithm:");
        System.out.println("1.Bubble Sort");
        System.out.println("2.Selection Sort");
        System.out.println("3.Insertion Sort");
        System.out.println("4.Merge Sort");
        System.out.println("5.Quick Sort");
        System.out.print("Enter choice 1-5: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bubbleSort(students);
                break;
            case 2:
                selectionSort(students);
                break;
            case 3:
                insertionSort(students);
                break;
            case 4:
                mergeSort(students, 0, students.length - 1);
                break;
            case 5:
                quickSort(students, 0, students.length - 1);
                break;
            default:
                System.out.println("Invalid choice Choose 1-5");
                bubbleSort(students);
        }

        System.out.println("\nSorted Students by Grade (Ascending):");
        for (Student student : students) {
            System.out.println(student);
        }

        scanner.close();
    }

    public static void bubbleSort(Student[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].grade > arr[j + 1].grade) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void selectionSort(Student[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j].grade < arr[minIdx].grade)
                    minIdx = j;
            Student temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(Student[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].grade > key.grade) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] L = new Student[n1];
        Student[] R = new Student[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i].grade <= R[j].grade)
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }

    public static void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Student[] arr, int low, int high) {
        Student pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].grade < pivot.grade) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

