package cn.kane.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class SortorsTest extends TestCase {

	private Integer[] srcArray ;
	private Sortors<Integer> sortor ;
	
	@Before
	public void setUp(){
		int arrayLength = 21 ;
		int dataRange = 1000 ;
		srcArray = new Integer[arrayLength] ;
		Random random = new Random() ;
		for(int i=0 ; i < arrayLength; i++){
			srcArray[i] = random.nextInt(dataRange) ;
		}
		System.out.println("[SourceArray]"+Arrays.toString(srcArray));
	}
	
	@Test
	public void testInsertSortor(){
		sortor = new InsertSort<Integer>() ;
		sortor.sort(srcArray);
		System.out.println("[InsertSort]"+Arrays.toString(srcArray));
	}

	@Test
	public void testSwitchSortor(){
		sortor = new BubbleSort<Integer>() ;
		sortor.sort(srcArray);
		System.out.println("[BubbleSort]"+Arrays.toString(srcArray));
	}

	@Test
	public void testQuickSortor(){
		sortor = new QuickSort<Integer>() ;
		sortor.sort(srcArray);
		System.out.println("[QuickSort]"+Arrays.toString(srcArray));
	}
	
}
