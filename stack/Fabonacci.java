package cn.kane.DatasStructures.stack;

import java.util.Scanner;

public class Fabonacci {

	
	private static int[] getFabonacciArray(int depth) throws Exception{
		if(depth<0)
			throw new Exception("length cannot less than 0 !");
		int[] fabonacciDatas = new int[depth+1] ;
		fabonacci(depth,fabonacciDatas);
		return fabonacciDatas ;
		
	}
	
	private static int fabonacci(int length,int[] fabonacciDatas) throws Exception{
		int result = -1 ;
		if(length==0 || length == 1){
			fabonacciDatas[0] = 1 ;
			result = 1 ;
		}
		else{
			result = fabonacci(length-1,fabonacciDatas)+fabonacci(length-2,fabonacciDatas) ;
			
		}
		fabonacciDatas[length] = result ;
		return result ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String inputStr = "exit" ;
		while(!(inputStr = input.nextLine()).equals("exit")){
			try {
				int depth = Integer.parseInt(inputStr) ;
				int[] fabonacciDatas = getFabonacciArray(depth);
				for(int result : fabonacciDatas)
					System.out.print(result+" ");
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
