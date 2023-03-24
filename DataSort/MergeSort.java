package DataSort;

public class MergeSort {
    public void mergesort(int[] arr, int initial, int last) {
        if (initial < last) {
            int mid = (initial + last) / 2;
            mergesort(arr, initial, mid);
            mergesort(arr, mid + 1, last);
            merge(arr, last, mid, mid);
        }
    }

    public void merge(int[] arr, int left, int mid, int right) {

    }

    public static void main(String[] args) {
        int[] array = { 1, 45, 64, 56, 43, 25 };

        
    }
}
