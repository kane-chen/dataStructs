package cn.kane.algorithm.stringschemal;

public class StringOfCharArray {

	private int capacity ;
	private int length ;
	private char[] datas ;
	
	public StringOfCharArray(int capacity){
		this.capacity = capacity ;
		this.datas = new char[this.capacity] ;
	}
	
	public StringOfCharArray(char[] datas){
		this(1024) ;
		for(int i=0;i<datas.length;i++)
			this.datas[i] = datas[i] ;
		this.length = datas.length ;
	}
	
	
	public boolean isEmpty(){
		return this.length == 0 ;
	}
	
	public int size(){
		return this.length ;
	}
	
	public char[] getCharByIndex(int offset,int counts) throws Exception{
		if(offset<0 || offset+counts>this.length)
			throw new Exception("index must be [0~"+length+")");
		char[] result = new char[counts];
		for(int i=0;i<counts;i++)
			result[i] =datas[offset+i] ;
		return result ;
	}
	
	public int getIndexByChars (char[] chars) throws Exception{
		if(chars.length>this.length)
			throw new Exception("target-char-array is less than param");
		int index = -1 ;
		for(int i=0 ;i<this.length;i++){
			int j = 0 ;
			for( ;j<chars.length;j++){
				if(chars[j] == datas[i+j])
					continue;
				else
					break ;
			}
			if(j==chars.length)
				return i ;
		}
		return index ;
	}
	
	public void insertCharsByIndex(int index,char[] chars)throws Exception{
		if(this.length+chars.length >= this.capacity)
			throw new Exception("char-array was full!");
		for(int i=this.length+chars.length-1;i>index+chars.length-1;i--){
			datas[i] = datas[i-chars.length] ;
		}
		for(int i=0;i<chars.length;i++){
			datas[index+i] = chars[i] ; 
		}
		this.length += chars.length ; 
	}
	
	public void removeCharsByIndex(int offset,int counts)throws Exception{
		if(offset+counts>this.length)
			throw new Exception("not-so-much-ems");
		for(int i=offset;i<this.length-counts;i++){
			datas[i] = datas[i+counts] ;
		}
		this.length-=counts ;
	}
	
	public int compare(char[] chars){
		if(chars.length==this.length){
			for(int i=0 ;i<this.length;i++){
				if(datas[i] == chars[i])
					continue ;
				else
					return datas[i]-chars[i] ;
			}
			return 0 ;
		}else{
			return datas.length-chars.length ;
		}
	}

	public void replace(char[] orgChars,char[] newChars) throws Exception{
		int index = this.getIndexByChars(orgChars) ;
		if(orgChars.length>newChars.length){
			int diff = orgChars.length - newChars.length ;
			for(int i=0 ; i<newChars.length ; i++)
				this.datas[index+i] = newChars[i];
			for(int i=index+newChars.length;i<this.length-diff;i++)
				this.datas[i] = this.datas[i+diff] ;
			this.length = this.length - diff ;
		}else{
			int diff = newChars.length - orgChars.length ;
			if(this.length+diff>this.capacity)
				throw new Exception("exceed the-max-capacity!");
			for(int i=this.length+diff;i>=index+newChars.length;i--)
				this.datas[i] = this.datas[i-diff] ;
			for(int i=0;i<newChars.length;i++)
				this.datas[index+i] = newChars[i] ; 
			this.length = this.length + diff ;
		}
	}
	
	public void printAllDatas(){
		System.out.println("PRINT-ALL-DATAS: "+new String(datas,0,length));
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		StringOfCharArray strArr = new StringOfCharArray("hello".toCharArray());
		strArr.printAllDatas();
		strArr.insertCharsByIndex(3, "world".toCharArray());
		strArr.printAllDatas();
		strArr.removeCharsByIndex(3, 5);
		strArr.printAllDatas();
		System.out.println(strArr.getIndexByChars("lo".toCharArray()));
		
		strArr.replace("o".toCharArray(), "oooo".toCharArray());
		strArr.printAllDatas();
		strArr.replace("e".toCharArray(), "eeee".toCharArray());
		strArr.printAllDatas();
		strArr.replace("ll".toCharArray(), "l".toCharArray());
		strArr.printAllDatas();
	}

}
