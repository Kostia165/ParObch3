package com.company.parobch3;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


public class FindAmountLetter{
	
	int amThreads;
	char wishedLetter;
	String stroka;
	
	public FindAmountLetter(int amThreads, char wishedLetter, String stroka) {
		this.amThreads = amThreads;
		this.wishedLetter = wishedLetter;
		this.stroka = stroka;
	}
	
	public int retAmount() {
		Data data = new Data(0);
		CountDownLatch latch = new CountDownLatch(amThreads);
		
		char[] charArray = stroka.toCharArray();
		int l = charArray.length / amThreads;
		
		for(int i = 0; i < amThreads; i++) {
			if(i == amThreads - 1) {
				AmountLetterOfSubstring findAm = new AmountLetterOfSubstring(data, latch, wishedLetter, Arrays.copyOfRange(charArray, i * l, charArray.length));
				findAm.start();
			}else {
				AmountLetterOfSubstring findAm = new AmountLetterOfSubstring(data, latch, wishedLetter, Arrays.copyOfRange(charArray, i * l, i * l + l));
				findAm.start();
			}
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return data.getRes();	
	}
}
