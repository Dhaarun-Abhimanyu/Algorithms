package divide_and_conquer.sorting;
import java.util.Scanner;

public class QuickSort {

    static int partition(int low, int high, int arr[]){
        int i=low-1;
        int pivot = arr[high];
        for(int j=low;j<high;j++){
            if(arr[j] <= pivot){
                i+=1;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    static void quicksort(int low, int high, int arr[]){
        if(low < high){
            int pivot = partition(low, high, arr);
            quicksort(low, pivot-1, arr);
            quicksort(pivot+1, high, arr);
        }
    }

    static void printArray(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String args[]){
        int n;
        Scanner sc=new Scanner(System.in);
        System.out.print("\nEnter the number of elements : ");
        n = sc.nextInt();
        int arr[] = new int[n];
        System.out.print("\nEnter "+n+" elements : ");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.print("\nArray before sorting : ");
        printArray(arr);
        System.out.print("\nAfter sorting : ");
        quicksort(0, arr.length-1, arr);
        printArray(arr);
        sc.close();
    }
}
