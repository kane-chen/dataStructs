package cn.kane.DatasStructures.sort;

import java.util.Random;

public class ArraySort {

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
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		
		ArraySort sortArr = new ArraySort();
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
	}

}
