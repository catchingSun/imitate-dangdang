package com.lemon;

public class practice {

	/**
	 * @param args
	 */
	public Boolean isHappyNumber(int i){
		int a = i;
		int b;
		int sum = 0;
		int count = 0;
		while(a > 0){
			b = a % 10;
			a = a / 10;
			sum += b * b;
			if(a == 0 && sum == 1){
				return true;
			}else if (a == 0 && sum != 1){
				//System.out.println("***********" + sum);
				a = sum;
				sum = 0;
				count ++;
			}else if(count > 100){
				break;
			}
		}
		
		return false;
	}
	
	public void findHappyNumber(){
		for(int i = 1 ; i < 100; i++){
			Boolean isHappyNumber = isHappyNumber(i);
			if(isHappyNumber){
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		practice p = new practice();
		p.findHappyNumber();
	}

}
