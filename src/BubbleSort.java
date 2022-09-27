import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static void printArray(int[] array) {
        for (int a : array) {
            System.out.printf("%d ", a);
        }
        System.out.println();
    }

    public static void bubbleSort(int[] array) {
        System.out.println("Start bubble sort");
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            printArray(array);
        }
    }

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] readFileToArray(String filename) throws Exception {
        try {
            Scanner scanner = new Scanner(new File(filename));
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str != "\n") {
                    arrayList.add(Integer.parseInt(str));
                }
            }
            int[] arrayTemp = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i ++) {
                arrayTemp[i] = arrayList.get(i);
            }
            return arrayTemp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeArrayToFile(int[] array, String filename) throws IOException{
        try (FileWriter writer = new FileWriter(filename);) {
            for (int a: array) {
                writer.write(a + "\n");
            }
        }
    } 

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to use Bubble Sort!");
        System.out.println("Please enter 1 or 2:\n" + 
        "1: Sort the integers in an existing file name\n" +
        "2: Generate an array of a random integers and store it in a file name");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        String filename;
        int[] array;
        if (choice == 1) {
            System.out.println("Please enter a filename:");
            filename = scanner.next();
            array = readFileToArray(filename);
            bubbleSort(array);
            System.out.println("Do you want to write the sorted array to file? (Enter yes or no)");
            String save = scanner.next();
            if (save.equals("yes")) {
                writeArrayToFile(array, "sorted.txt");
                System.out.println("The sorted array is saved in sorted.txt");
            }
        } else if (choice == 2) {
            System.out.println("Please enter the number of random integers to be generated:");
            int size = scanner.nextInt();
            array = createRandomArray(size);
            System.out.println("Please enter the filename to save:");
            filename = scanner.next();
            if (filename == "") {
                filename = "integers.txt";
            }
            writeArrayToFile(array, filename);
            bubbleSort(array);
        }
        scanner.close();
    }
}
