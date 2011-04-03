import java.util.*;

public class Sort {

	static Random rnd = new Random();

	public static void qSort(int[] a, int low, int high) {
		int i = low;
		int j = high;
		int x = a[low + rnd.nextInt(high - low + 1)];
		do {
			while (a[i] < x)
				++i;
			while (x < a[j])
				--j;
			if (i <= j) {
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
				++i;
				--j;
			}
		} while (i <= j);
		if (low < j)
			qSort(a, low, j);
		if (i < high)
			qSort(a, i, high);
	}

	public static void qSort2(int[] a, int low, int high) {
		if (high - low > 1) {
			int p = randomizedPartition(a, low, high);
			qSort2(a, low, p);
			qSort2(a, p, high);
		}
	}

	static int randomizedPartition(int[] a, int low, int high) {
		swap(a, low + rnd.nextInt(high - low), high - 1);
		return partition(a, low, high);
	}

	static int partition(int[] a, int low, int high) {
		int x = a[high - 1];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (a[j] <= x) {
				++i;
				swap(a, i, j);
			}
		}
		return i;
	}

	static void swap(int[] a, int i, int j) {
		int t = a[j];
		a[j] = a[i];
		a[i] = t;
	}

	public static void mergeSort(int[] a, int low, int high) {
		if (low + 1 < high) {
			int mid = (low + high) >>> 1;
			mergeSort(a, low, mid);
			mergeSort(a, mid, high);

			int size = high - low;
			int[] b = new int[size];
			int i = low;
			int j = mid;
			for (int k = 0; k < size; k++) {
				if (j >= high || i < mid && a[i] < a[j]) {
					b[k] = a[i++];
				} else {
					b[k] = a[j++];
				}
			}
			System.arraycopy(b, 0, a, low, size);
		}
	}

	public static void bubbleSort(int[] a) {
		for (int i = 0; i + 1 < a.length; i++) {
			for (int j = 0; j + 1 < a.length; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
				}
			}
		}
	}

	public static void selectionSort(int[] a) {
		int n = a.length;
		int[] p = new int[n];
		for (int i = 0; i < n; i++)
			p[i] = i;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (a[p[i]] > a[p[j]]) {
					swap(p, i, j);
				}
			}
		}
		int[] b = a.clone();
		for (int i = 0; i < n; i++)
			a[i] = b[p[i]];
	}

	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
				}
			}
		}
	}

	public static void countingSort(int[] a) {
		int max = 0;
		for (int x : a) {
			max = Math.max(max, x);
		}
		int[] cnt = new int[max + 1];
		for (int x : a) {
			++cnt[x];
		}
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		int n = a.length;
		int[] b = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			b[--cnt[a[i]]] = a[i];
		}
		System.arraycopy(b, 0, a, 0, n);
	}

	// Usage example
	public static void main(String[] args) {
		int[] p = { 4, 1, 3, 2 };
		int[] s = p.clone();
		qSort(s, 0, s.length - 1);
		System.out.println(Arrays.toString(s));

		s = p.clone();
		qSort2(s, 0, s.length);
		System.out.println(Arrays.toString(s));

		s = p.clone();
		mergeSort(s, 0, s.length);
		System.out.println(Arrays.toString(s));

		s = p.clone();
		bubbleSort(s);
		System.out.println(Arrays.toString(s));

		s = p.clone();
		selectionSort(s);
		System.out.println(Arrays.toString(s));

		s = p.clone();
		insertionSort(s);
		System.out.println(Arrays.toString(s));

		s = p.clone();
		countingSort(s);
		System.out.println(Arrays.toString(s));
	}
}