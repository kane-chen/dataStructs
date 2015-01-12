package cn.kane.algorithm.sort;

import java.util.Comparator;

/**
 * 
 * @param <E>
 */
public abstract class Sortors<E extends Comparable<E>> {
	/* 顺序 */
	public final Comparator<E> DEFAULT_ORDER = new DefaultComparator();
	/* 逆序 */
	public final Comparator<E> REVERSE_ORDER = new ReverseComparator();

	/**
	 * 排序算法，需实现，对数组中指定的元素进行排序
	 * 
	 * @param array
	 * @param from
	 * @param end
	 * @param c
	 */
	public abstract void sort(E[] array, int from, int end, Comparator<E> c);

	/**
	 * 对数组中指定部分进行排序
	 * 
	 * @param from
	 * @param len
	 * @param array
	 * @param c
	 */
	public void sort(int from, int len, E[] array, Comparator<E> c) {
		sort(array, 0, array.length - 1, c);
	}

	/**
	 * 对整个数组进行排序，可以使用自己的排序比较器，也可使用该类提供的两个比较器
	 * 
	 * @param array
	 * @param c
	 */
	public final void sort(E[] array, Comparator<E> c) {
		sort(0, array.length, array, c);
	}

	/**
	 * 对整个数组进行排序，采用默认排序比较器
	 * 
	 * @param array
	 */
	public final void sort(E[] array) {
		sort(0, array.length, array, this.DEFAULT_ORDER);
	}

	// 默认比较器（一般为升序，但是否真真是升序还得看E是怎样实现Comparable接口的）
	private class DefaultComparator implements Comparator<E> {
		public int compare(E o1, E o2) {
			return o1.compareTo(o2);
		}
	}

	// 反序比较器，排序刚好与默认比较器相反
	private class ReverseComparator implements Comparator<E> {
		public int compare(E o1, E o2) {
			return o2.compareTo(o1);
		}
	}

	/**
	 * 交换数组中的两个元素的位置
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	protected final void swap(E[] array, int i, int j) {
		if (i != j) {// 只有不是同一位置时才需交换
			E tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}

	/**
	 * 数组元素后移
	 * 
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 */
	protected final void move(E[] array, int startIndex, int endIndex) {
		for (int i = endIndex; i >= startIndex; i--) {
			array[i + 1] = array[i];
		}
	}

	/**
	 * 以指定的步长将数组元素后移，步长指定每个元素间的间隔
	 * 
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 * @param step
	 */
	protected final void move(E[] array, int startIndex, int endIndex, int step) {
		for (int i = endIndex; i >= startIndex; i -= step) {
			array[i + step] = array[i];
		}
	}

}