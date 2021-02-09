#include <iostream>

using namespace std;

int v[25000];

// A utility function to swap two elements 
void swap(int* a, int* b)
{
    int t = *a;
    *a = *b;
    *b = t;
}

/* This function takes last element as pivot, places
   the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
   to left of pivot and all greater elements to right
   of pivot */
int partition(int arr[], int low, int high)
{
    int pivot = arr[high];    // pivot 
    int i = (low - 1);  // Index of smaller element 

    for (int j = low; j <= high - 1; j++)
    {
        // If current element is smaller than or 
        // equal to pivot 
        if (arr[j] <= pivot)
        {
            i++;    // increment index of smaller element 
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

/* The main function that implements QuickSort
 arr[] --> Array to be sorted,
  low  --> Starting index,
  high  --> Ending index */
void quickSort(int arr[], int low, int high)
{
    if (low < high)
    {
        /* pi is partitioning index, arr[p] is now
           at right place */
        int pi = partition(arr, low, high);

        // Separately sort elements before 
        // partition and after partition 
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int moda(int v[], int n) { /* Return ret */

    quickSort(v, 0, n - 1);

    int ret = v[0], maxFreq = 0, num = v[0], freq = 0, i = 0;

	while (i < n) {
        if (num == v[i]) {
            freq++;
        }
        else {
            if (freq > maxFreq) { maxFreq = freq; ret = num; }
            freq = 1;
            num = v[i];
        }
        i++;
	}

	return ret;
}

int main() {
	int n;

	cin >> n;

	while (n != 0) {
		for (int i = 0; i < n; i++) { cin >> v[i]; };

		cout << moda(v, n) << '\n';

		cin >> n;
	}



	return 0;
}