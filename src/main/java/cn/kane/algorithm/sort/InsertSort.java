package cn.kane.algorithm.sort;

import java.util.Comparator;

/**
 * 直接插入排序算法:将数组分为无序区和有序区两个区，然后不断将无序区的第一个元素按大小顺序插入到有序区中去，最终将所有无序区元素都移动到有序区完成排序。
 * 
 * @author jzj
 * @date 2009-12-5
 * 
 * @param <E>
 */
public class InsertSort<E extends Comparable<E>> extends Sortors<E> {
	/**
	 * 排序算法的实现，对数组中指定的元素进行排序
	 * 
	 * @param array
	 * @param from
	 * @param end
	 * @param c
	 */
	public  void sort(E[] array, int from, int end, Comparator<E> c) {
		/*
		 * 第一层循环：对待插入（排序）的元素进行循环 从待排序数组断的第二个元素开始循环，到最后一个元素（包括）止
		 */
		for (int i = from + 1; i <= end; i++) {
			/*
			 * 第二层循环：对有序数组进行循环，且从有序数组最第一个元素开始向后循环 找到第一个大于待插入的元素
			 * 有序数组初始元素只有一个，且为源数组的第一个元素，一个元素数组总是有序的
			 */
			for (int j = 0; j < i; j++) {
				E insertedElem = array[i];// 待插入到有序数组的元素
				// 从有序数组中最一个元素开始查找第一个大于待插入的元素
				if (c.compare(array[j], insertedElem) >= 0) {
					// 找到插入点后，从插入点开始向后所有元素后移一位
					move(array, j, i - 1);
					// 将待排序元素插入到有序数组中
					array[j] = insertedElem;
					break;
				}
			}
		}
	}

}