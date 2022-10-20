package controller;

public class Descenso {

	private double b;
	private double h;
	public static double jump = 0.1;
	public static int Iterations = 100;

	public Descenso(double h) {
		this.b = (Math.random() * (10 - 1) + 1);
		this.h = h;
	}

	private double firstEcuation(double x) {
		return Math.pow(x - 3.5, 2) + 2.5;
	}

	private double secondEcuation(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	private void minimizeFirstEq() {
		double delta = 0;
		for (int i = 0; i < Iterations; i++) {
			delta = (firstEcuation(b + h) - firstEcuation(b)) / ((b + h) - b);
			b = b - (jump * delta);
			System.out.println("Delta " + delta);
			System.out.println("Descenso" + b);
			System.out.println("Y = " + firstEcuation(b));
		}

	}

	private void minimizeSecondEq() {
		double delta = 0;
		for (int i = 0; i < Iterations; i++) {
			delta = (secondEcuation(b + h) - secondEcuation(b)) / ((b + h) - b);
			b = b - (jump * delta);
			System.out.println("Delta " + delta);
			System.out.println("Descenso " + b);
			System.out.println("Y = " + secondEcuation(b));
		}
	}

	private double getDeltaFirstEq() {
		return firstEcuation(b);
	}

	private double getDeltaSecondEq() {
		return secondEcuation(b);
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public static void main(String[] args) {
		Descenso descenso = new Descenso(0.001);
		
//		Minimización primera formula
		descenso.minimizeFirstEq();
		System.out.println("------------------------------------------------------------- \n");
		System.out.println("Ecuación 1: y=(x - 3.5)² + 2.5 \n");
		System.out.println("dx = " + descenso.getB() + "\n");
		System.out.println("dy= " + descenso.getDeltaFirstEq() + "\n");
		System.out.println("------------------------------------------------------------- \n");
		
		
//		Minimización segunda formula
		descenso.minimizeSecondEq();
		System.out.println("------------------------------------------------------------- \n");
		System.out.println("Ecuacion 2: y= 1/(1+e^(-x) \n");
		System.out.println("dx = "+ descenso.getB() + "\n");
		System.out.println("dy = " + descenso.getDeltaSecondEq() + "\n");
	}

}
