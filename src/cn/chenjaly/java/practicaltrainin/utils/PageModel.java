package cn.chenjaly.java.practicaltrainin.utils;

public class PageModel {
	//瑜版挸澧犳い鐢电垳
	private Integer pageIndex;
	
	//濮ｅ繘銆夐弰鍓с仛閺佷即鍣�
	public static final Integer pageSize = 10;
	
	//閹銆夐惍锟�
	private Integer totalPageSum;
	
	//閹槒顔囪ぐ鏇熸殶
	private Integer totalRecordSum;

	public Integer getPageIndex() {
		//婵″倹鐏夋潏鎾冲弳閻ㄥ嫭鏆熺亸蹇庣艾缁涘绨�1
		this.pageIndex = this.pageIndex <= 1 ? 1:this.pageIndex;
		
		//婵″倹鐏夋潏鎾冲弳閻ㄥ嫬缍嬮崜宥夈�夐惍浣规Ц婢堆傜艾閹存牜鐡戞禍搴拷濠氥�夐惍锟� 
		this.pageIndex = this.pageIndex >= getTotalPageSum() ? getTotalPageSum():this.pageIndex;
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTotalPageSum() {
		// 30閺壜ゎ唶瑜帮拷 濮ｅ繘銆�10閺夛拷 30/10=3 29/10=2 閸氬酣娼版潻娆戭潚閹懎鍠� 鐟曚礁濮�1
		this.totalPageSum = getTotalRecordSum() % getPagesize() == 0 ? getTotalRecordSum()/getPagesize():
			getTotalRecordSum()/getPagesize()+1;
		return totalPageSum;
	}

	public void setTotalPageSum(Integer totalPageSum) {
		this.totalPageSum = totalPageSum;
	} 

	public Integer getTotalRecordSum() {
		return totalRecordSum;
	}

	public void setTotalRecordSum(Integer totalRecordSum) {
		this.totalRecordSum = totalRecordSum;
	}

	public static Integer getPagesize() {
		return pageSize;
	}
	
	//閹绘劒绶垫稉锟芥稉顏呮煙濞夋洝顓哥粻妤勬崳婵顢�
	public int getStartRow() {
		int start = (getPageIndex() -1 ) >= 1 ? (getPageIndex() -1) * getPagesize() : 0;
		return start;
	}
	
	
}
