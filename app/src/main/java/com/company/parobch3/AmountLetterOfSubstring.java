package com.company.parobch3;

import java.util.concurrent.CountDownLatch;

public class AmountLetterOfSubstring extends Thread{
	
	char wishedLetter;
	char[] substring;
	Data data;
	CountDownLatch latch;

	public AmountLetterOfSubstring(Data data, CountDownLatch latch, char wishedLetter, char[] substring) {
		this.wishedLetter = wishedLetter;
		this.substring = substring;
		this.data = data;
		this.latch = latch;
	}

	public void  run() {
		int delta = 0;
		
		if(wishedLetter < 91) {
			delta = 32;
		}else {
			delta = -32;
		}
		
		int amount = 0;
		
		for(int i = 0; i < substring.length; i++) {
			if(substring[i] == wishedLetter || substring[i] == wishedLetter + delta) {
				amount++;
			}
			
		}
		
		Object monitor = new Object();
		synchronized(monitor) {
			data.setRes(data.getRes() + amount);
		}
		
		latch.countDown();
		
	}
}
