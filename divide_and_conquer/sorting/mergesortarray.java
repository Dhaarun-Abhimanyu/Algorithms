package divide_and_conquer.sorting;
import java.util.Scanner;
public class mergesortarray {

    static void merge(int arr[], int left, int right, int mid){
        int n1[] = new int[mid-left+1];
        int n2[] = new int[right-mid];
        System.out.print("\n\nn1 : ");
        for(int i=0;i<n1.length;i++){
            n1[i] = arr[i+left];
            System.out.print(n1[i]+" ");
        }
        System.out.print("\nn2 : ");
        for(int i=0;i<n2.length;i++){
            n2[i] = arr[mid+i+1];
            System.out.print(n2[i]+" ");
        }
        int pn1=0,pn2=0,ind=0;
        while(pn1 < n1.length && pn2 < n2.length){
            if(n1[pn1] < n2[pn2]){
                arr[left+ind++] = n1[pn1++];
            }else{
                arr[left+ind++] = n2[pn2++];
            }
        }
        while(pn1 < n1.length){ arr[left+ind++] = n1[pn1++]; }
        while(pn2 < n2.length){ arr[left+ind++] = n2[pn2++]; }
        System.out.print("\n\nMerged array : ");
        for(int i=left; i<=right;i++){
            System.out.print(arr[i]+" ");
        }
    }

    static void mergesort(int arr[],int left, int right){
        if(left < right){
            int mid = (left+right)/2;
            mergesort(arr, left, mid);
            mergesort(arr, mid+1, right);
            merge(arr, left, right, mid);
        }
    }

    static void printArray(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter number of elements : ");
        int n =sc.nextInt();
        int arr[] = new int[n];
        System.out.print("\nEnter "+n+" elements : ");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.print("\nArray before sorting : ");
        printArray(arr);
        mergesort(arr, 0, arr.length-1);
        System.out.print("\nAfter sorting : ");
        printArray(arr);
        sc.close();
    }
}
