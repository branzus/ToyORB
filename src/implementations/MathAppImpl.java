package implementations;

import interfaces.MathApp;

public class MathAppImpl implements MathApp {

	public int add(int a, int b) {
		System.out.println("Adding "+ a + "+" + b);
		return a+b;
	}
}
