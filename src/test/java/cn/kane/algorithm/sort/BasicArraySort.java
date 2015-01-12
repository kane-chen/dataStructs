package cn.kane.algorithm.sort ;

import java.util.Random;

public class BasicArraySort {

	private  int[] array = new int[10] ;
	
	public  int simpleBubbleSort(int[] sortedArr){
		int swapTimes = 0 ;
		for(int i=0;i<sortedArr.length;i++){
			for(int j=i+1;j<sortedArr.length;j++){
				if(sortedArr[i]>sortedArr[j]){
					swapTimes ++ ;
					swapValue(sortedArr,i,j) ;
				}
			}
		}
		
		for(int num : sortedArr)
			System.out.print(num+",");
		System.out.print(" simpleBubbleSort");
		
		return swapTimes ;
	}
	
	public int bubbleSort(int[] sortedArr){
		int swapTimes = 0 ;
		for(int i=0 ; i<sortedArr.length ;i++){
			for(int j=sortedArr.length-1 ; j>=i;j--){
				if(sortedArr[i]>sortedArr[j]){
					swapTimes++ ;
					swapValue(sortedArr,i,j);
				}
			}
		}
		
		for(int num : sortedArr)
			System.out.print(num+",");
		System.out.print(" bubbleSort");
		
		return swapTimes ;
	}
	
	public int bubbleSortImprove(int[] sortedArr){
		int swapTimes = 0 ;
		boolean flag = true ;
		for(int i=0 ; i<sortedArr.length && flag;i++){
			for(int j= sortedArr.length-2 ; j>=i; j--){
				flag = false ;
				if(sortedArr[j]>sortedArr[j+1]){
					swapTimes++ ;
					swapValue(sortedArr,j,j+1);
					flag = true ;
				}
			}
		}
		
		for(int num : sortedArr)
			System.out.print(num+",");
		System.out.print(" bubbleSortImprove");
		
		return swapTimes ;
	}
	
	public  int selectSort(int[] sortedArr){
		int swapTimes = 0 ;
		for(int i=0;i<sortedArr.length;i++){
			int min = sortedArr[i] ;
			int pos = i ;
			for(int j=i+1;j<sortedArr.length;j++){
				if(sortedArr[j]<min){
					min = sortedArr[j] ;
					pos = j ;
				}
			}
			if(pos!=i){
				swapTimes++ ;
				swapValue(sortedArr,i,pos) ;
			}
		}
		
		for(int num : sortedArr)
			System.out.print(num+",");
		System.out.print(" selectSort");
		
		return swapTimes ;
	}
	
	public int insertSort(int[] sortedArr){
		int swapTimes = 0 ;
		for(int i=1;i<sortedArr.length;i++){
			if(sortedArr[i]<sortedArr[i-1]){
				int value = sortedArr[i] ;
				int j=i-1 ;
				for( ; j>=0;j--){
					if(sortedArr[j]<value)
						break ;
					sortedArr[j+1] = sortedArr[j] ;
					swapTimes++ ;
				}
				sortedArr[j+1] = value ;
			}
		}
		
		for(int num : sortedArr)
			System.out.print(num+",");
		System.out.print(" insertSort");
		return swapTimes ; 
	}
	
	public int shellSort(int[] sortedArr) {  
		int swapTimes = 0 ;
        int j = 0;  
        int temp = 0;  
        for (int increment = sortedArr.length / 2; increment > 0; increment /= 2) {  
            for (int i = increment; i < sortedArr.length; i++) {  
                temp = sortedArr[i];  
                for (j = i; j >= increment; j -= increment) {  
                    if(temp < sortedArr[j - increment]){  
                        sortedArr[j] = sortedArr[j - increment]; 
                        swapTimes++ ;
                    }else{  
                        break;  
                    }  
                }   
                sortedArr[j] = temp;  
            }  
        }  
        
        for(int num : sortedArr)
			System.out.print(num+",");
		System.out.print(" shellSort");
        return swapTimes ;
    }  
	
	/**
	 * 
	 * 堆排序 原理：每次把最大的元素（即：堆根）与最后一个元素交换， 然后把前n-1个元素进行堆的重构,直到只剩一个元素为止。
	 * 
	 * 
	 * @param a
	 */
	public void heapSort(int[] a) {
		int n = a.length;
		while (n > 0) {
			int tmp = a[0];
			a[0] = a[n - 1];
			a[n - 1] = tmp;
			keepHeap(a, --n, 0);
		}
		
		 for(int num : a)
				System.out.print(num+",");
			System.out.println(" heapSort");
	}

	/**
	 * 维持一个大根堆的性质
	 * 
	 * @param heap
	 * @param from
	 * @param to
	 */
	private static void keepHeap(int[] a, int n, int i) {
		int x = a[i];
		int j = 2 * i + 1;
		while (j <= n - 1) {
			if (j < n - 1 && a[j] < a[j + 1])
				++j;
			if (a[j] > x) {
				a[i] = a[j];
				i = j;
				j = 2 * i + 1;
			} else
				break;
		}
		a[i] = x;
	}

	private void swapValue(int[] targetArr, int pos1, int pos2) {
		int temp = targetArr[pos1];
		targetArr[pos1] = targetArr[pos2];
		targetArr[pos2] = temp;
	}
	
	/**
	 * 插入排序：将数组分为无序区和有序区两个区，然后不断将无序区的第一个元素按大小顺序插入到有序区中去，最终将所有无序区元素都移动到有序区完成排序。
	 * 
	 * @param dataArr
	 * @return
	 */
	public static int[] basicInsertSort(int[] dataArr) {
		for (int unSortedIndex = 1; unSortedIndex < dataArr.length; unSortedIndex++) {
			for (int sortedIndex = 0; sortedIndex < unSortedIndex; sortedIndex++) {
				if (dataArr[sortedIndex] > dataArr[unSortedIndex]) {
					swap(dataArr, sortedIndex, unSortedIndex);
				}
			}
		}
		return dataArr;
	}

	/**
	 * 希尔排序：将数组分成step个小组，在小组内通过插入排序，初步缩小step的数值至1，此时array排序完成
	 * 
	 * @param datas
	 * @return
	 */
	public static int[] shellInsertSort(int[] datas) {
		int step = datas.length;
		while (step > 1) {
			step = step / 2;
			if (step < 1) {
				break;
			}
			shellInsertSort(datas, step);
		}
		return datas;
	}

	public static int[] shellInsertSort(int[] datas, int step) {
		for (int x = 0; x < step; x++) {
			// basic-insert
			for (int i = step + x; i < datas.length; i += step) {
				for (int j = x; j < i; j += step) {
					if (datas[j] > datas[i]) {
						swap(datas, i, j);
					}
				}
			}
		}
		return datas;
	}

	/**
	 * 冒泡交换排序：比较相邻2个元素，逆序则交换位置；以达到将最大/小元素移至无序数组的头/尾部
	 * 
	 * @param datas
	 * @return
	 */
	public static int[] bubbleSwitchSort(int[] datas) {
		for (int i = 0; i < datas.length; i++) {
			for (int j = 1; j < datas.length - i; j++) {
				if (datas[j - 1] > datas[j]) {
					swap(datas, j, j - 1);
				}
			}
		}
		return datas;
	}

	public static int[] quickSwitchSort(int[] datas) {
		int startIndex = 0;
		int endIndex = datas.length-1;
		quickSwitchSort(datas,startIndex,endIndex) ;
		return datas;

	}

	public static int[] quickSwitchSort(int[] datas,int startIndex, int endIndex) {
		if(startIndex < endIndex){
			int swapIndex = switch4QuickSwitchSort(datas, startIndex, endIndex);
			quickSwitchSort(datas, startIndex, swapIndex - 1);
			quickSwitchSort(datas, swapIndex + 1, endIndex);
		}
		return datas;
	}

	public static int switch4QuickSwitchSort(int[] datas, int startIndex, int endIndex) {
		int tmpData = datas[startIndex];
		int swapPos = startIndex ;
		// less than tmpData
		for (int i = startIndex + 1 ; i <= endIndex; i++) {
			if (datas[i] < tmpData) {
				swapPos++;
				swap(datas, i, swapPos);
			}
		}
		// tmpData
		swap(datas, startIndex, swapPos);
		return swapPos;
	}
	

	private static void swap(int[] srcArr, int pos1, int pos2) {
		int tmp = srcArr[pos1];
		srcArr[pos1] = srcArr[pos2];
		srcArr[pos2] = tmp;
	}

	private static String getDatasInArray(int[] datas) {
		StringBuffer buffer = new StringBuffer();
		for (int data : datas) {
			buffer.append(data).append(",");
		}
		return buffer.toString();
	}

	private static boolean checkSortedArray(int[] datas) {
		int i = 1;
		for (; i < datas.length; i++) {
			if (datas[i - 1] <= datas[i]) {
				continue;
			} else {
				break;
			}
		}
		if (i == datas.length) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		
		BasicArraySort sortArr = new BasicArraySort();
		for(int i =0 ;i<10;i++){
			sortArr.array[i] = rand.nextInt(100);
			System.out.print(sortArr.array[i]+",");
		}
		System.out.println("");
		System.out.println("-----------");
		
		System.out.println("-SWAP-TIMED:"+sortArr.simpleBubbleSort(sortArr.array.clone()));
		System.out.println("-SWAP-TIMED:"+sortArr.bubbleSort(sortArr.array.clone()));
		System.out.println("-SWAP-TIMED:"+sortArr.bubbleSortImprove(sortArr.array.clone()));
		System.out.println("-SWAP-TIMED:"+sortArr.selectSort(sortArr.array.clone()));
		System.out.println("-SWAP-TIMED:"+sortArr.insertSort(sortArr.array.clone()));
		System.out.println("-SWAP-TIMED:"+sortArr.shellSort(sortArr.array.clone()));
		sortArr.heapSort(sortArr.array.clone()) ;
		
		
		Random random = new Random();
		int arrayLength = 21;
		int[] array = new int[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			int data = random.nextInt(1000);
			array[i] = data;
		}
		System.out.println("[SRC]" + getDatasInArray(array));
		/* insert-sort */
//		int[] sortedArray = basicInsertSort(array);
//		int[] sortedArray = shellInsertSort(array);
		/* switch-sort */
//     	int[] sortedArray = bubbleSwitchSort(array);
		int[] sortedArray = quickSwitchSort(array);

		System.out.println("[SORTED]" + getDatasInArray(sortedArray));
		System.out.println("[isSorted]" + checkSortedArray(sortedArray));
	}

}
