package cn.kane.algorithm.sort;

import java.util.Comparator;

/**
 * 简单选择排序算法:每次选择最大/小的对象，与指定位置上的对象交换位置
 * 
 * @author jzj
 * @date 2009-12-5
 * 
 * @param <E>
 */
public class SelectSort<E extends Comparable<E>> extends Sortors<E> {
	/**
	 * 排序算法的实现，对数组中指定的元素进行排序
	 * 
	 * @param array
	 * @param from
	 * @param end
	 * @param c
	 */
	public void sort(E[] array, int from, int end, Comparator<E> c) {
		int minlIndex;// 最小索引
		/*
		 * 循环整个数组（其实这里的上界为 array.length - 1 即可，因为当 i= array.length-1
		 * 时，最后一个元素就已是最大的了，如果为array.length时，内层循环将不再循环），每轮假设
		 * 第一个元素为最小元素，如果从第一元素后能选出比第一个元素更小元素，则让让最小元素与第一 个元素交换
		 */
		for (int i = from; i <= end; i++) {
			minlIndex = i;// 假设每轮第一个元素为最小元素
			// 从假设的最小元素的下一元素开始循环
			for (int j = i + 1; j <= end; j++) {
				// 如果发现有比当前array[smallIndex]更小元素，则记下该元素的索引于smallIndex
				if (c.compare(array[j], array[minlIndex]) < 0) {
					minlIndex = j;
				}
			}
			// 先前只是记录最小元素索引，当最小元素索引确定后，再与每轮的第一个元素交换
			swap(array, i, minlIndex);
		}
	}

}