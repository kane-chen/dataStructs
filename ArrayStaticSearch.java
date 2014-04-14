package cn.kane.DatasStructures;

public class ArrayStaticSearch {

	private int[] array ;
	
	public ArrayStaticSearch(int[] array){
		this.array = array ;
	}
	
	public int sequentialSearch(int data){
		int pos = -1 ;
		for(int i=0 ;i<array.length;i++){
			if(array[i] == data){
				pos = i ;
				break ;
			}
		}
		
		return pos ;
	}
	
	public int sequentialSearchByGuard(int data){
		int[] carray = new int[array.length+1] ;
		for(int i=0;i<array.length;i++)
			carray[i] = array[i] ;
		carray[array.length] = data ;
		int pos = 0 ;
		while(true){
			if(carray[pos] == data)
				break ;
			pos++ ;
		}
		if(pos == array.length)
			pos = -1 ;
		
		return pos ;
	}
	
	public int binarySearch(int data){
		int lowPos = 0 ;
		int highPos = array.length-1 ;
		
		while(lowPos<=highPos){
			int midPos = (lowPos+highPos) / 2 ;
			if(data == array[midPos])
				return midPos ;
			if(data < array[midPos])
				highPos = midPos-1 ;
			else
				lowPos = midPos+1 ;
		}
		
		return -1 ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {0,1,16,24,35,47,59,62,73,88,99} ;
		ArrayStaticSearch search = new ArrayStaticSearch(array) ;
		System.out.println(search.binarySearch(24));
		System.out.println(search.sequentialSearchByGuard(23));
		System.out.println(search.sequentialSearchByGuard(24));
	}

}
