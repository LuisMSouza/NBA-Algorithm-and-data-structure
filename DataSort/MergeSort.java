package DataSort;

public class MergeSort {
    public void mergesort(int[] arr, int initial, int last) {
        if (initial < last) {
            int mid = (initial + last) / 2;
            mergesort(arr, initial, mid);
            mergesort(arr, mid + 1, last);
            sort();
        }
    }

    public void sort() {
        
    }

    public static void main(String[] args) {

    }
}
