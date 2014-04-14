package cn.kane.DatasStructures.string;

import java.util.ArrayList;
import java.util.List;

public class StringPatternOfKMP {

	public static int[] getRebackPosArrOfPattern(char[] patternArr){
		//init reback-pos-arr
		int[] rebackArr = new int[patternArr.length] ;
		rebackArr[0] =  0 ;//the first char's reback pos will be 0
		
		int curRebackPos = 0 ;//current char's reback pos 
		//visit-all-chars
		for(int i=1 ; i<rebackArr.length ; i++){
			//char at [i]&[curRebackPos] not equal;reback curRebackPos's reback-pos
			while(curRebackPos>0 && patternArr[i]!=patternArr[curRebackPos])
				curRebackPos = rebackArr[curRebackPos-1] ;
			//char at[i]&[curRebackPos] equal;continue(compare i+1 & curRebackPos)
			if(patternArr[i] == patternArr[curRebackPos])
				curRebackPos++ ;
			rebackArr[i] = curRebackPos ;
		}
		
		return rebackArr ;
	}
	
	public static List<Integer> getIndexsOfPatternWithKMP(String targetStr,String patternStr) throws Exception{
		if(null==targetStr || null==patternStr)
			throw new Exception("target-string & pattern-string cannot be null!!");
		if(targetStr.length()<patternStr.length())
			throw new Exception("target-string should longger than pattern-string!!");
		
		char[] targetChars = targetStr.toCharArray() ;
		char[] patternChars = patternStr.toCharArray() ;
		List<Integer> indexLst = new ArrayList<Integer>() ;
		
		System.out.println("-------------------------------------------------");
		System.out.println("TARGET -STRING: "+targetStr);
		System.out.println("PATTERN-STRING: "+patternStr);
		int[] rebackArr = getRebackPosArrOfPattern(patternChars);
		System.out.print("REBACK-POSITION-ARRAY: "+patternStr+" :");
		for(int index : rebackArr)
			System.out.print(index+",");
		System.out.println(" ");
		
		int curPatternPos = 0 ;
		for(int i=0 ; i<targetChars.length ; i++){
			while(curPatternPos>0 && targetChars[i]!=patternChars[curPatternPos])
				curPatternPos = rebackArr[curPatternPos-1] ;
			if(targetChars[i] == patternChars[curPatternPos])
				curPatternPos++ ;
			
			if(curPatternPos == patternChars.length){
				indexLst.add(i-patternChars.length+1) ;
				curPatternPos = rebackArr[curPatternPos-1] ;
			}
		}
		
		System.out.print("PATTERN-INDEX: ");
		for(int index : indexLst)
			System.out.print(index+",");
		System.out.println("");
		return indexLst ;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		getIndexsOfPatternWithKMP("abcdabceabcf", "abc");//3
		getIndexsOfPatternWithKMP("abcdeg,abcdehabcdef", "abcdef");//1
		getIndexsOfPatternWithKMP("ababacabacbababababacabcbabababaca", "ababaca");//3
		getIndexsOfPatternWithKMP("Test ititi ititititit! Test ititit!","ititit");//4
	}

}
