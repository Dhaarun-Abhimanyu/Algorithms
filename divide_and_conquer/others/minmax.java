package divide_and_conquer.others;
import java.util.Scanner;
public class minmax {
    
    static void findMinMax(int arr[], int left, int right){
        if(left==right){
            arr[0] = (arr[0] > arr[left])?arr[left]:arr[0];
            arr[1] = (arr[1] < arr[left])?arr[left]:arr[1];
            return;
        }
        if(left < right){
            int mid = (left + right)/2;
            findMinMax(arr, left, mid);
            findMinMax(arr, mid+1, right);
        }
    }   
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no. of elements : ");
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.print("\nEnter "+n+" elements : ");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        findMinMax(arr, 0, arr.length-1);
        System.out.print("\nMin : "+arr[0]+"\nMax : "+arr[1]);
        sc.close();
    }
}
