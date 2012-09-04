package com.kaishengit.util;

import java.util.List;

public class Pager {
	private int totalPages;
	private int startIndex;
	private int perSize;
	private int pagerNum;
	private int totalCounts;
	private List result;
	
	public Pager(int totalCounts,int persize,int num){
		setTotalCounts(totalCounts);
		setPerSize(persize);
		setPagerNum(num);
	}
	
	
	
	public int getTotalPages() {
		int result = getTotalCounts()/getPerSize();
		if(getTotalCounts()% getPerSize() != 0){
			result += 1;
		}
		return result;
	}
	
	public int getStartIndex() {
		int startIndex = (getPagerNum()-1)*getPerSize();
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		
		this.startIndex = startIndex;
	}
	public int getPerSize() {
		return perSize;
	}
	public void setPerSize(int perSize) {
		this.perSize = perSize;
	}
	
	
	public int getPagerNum() {
	
		return pagerNum;
	}


	public void setPagerNum(int pagerNum) {
		if(getTotalCounts() == 0){
			pagerNum = 1;
		}else{
			if(pagerNum < 1){
				pagerNum = 1;
			}else if(pagerNum >getTotalPages()){
				pagerNum = getTotalPages();
			}
		}
		
		
		this.pagerNum = pagerNum;
	}


	public int getTotalCounts() {
		return totalCounts;
	}
	
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}

}
