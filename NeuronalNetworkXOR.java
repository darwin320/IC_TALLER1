package models;

/**
 * 
 * @author Darwin
 *Esta clase esta encargada de la generacion de la red neuronal para la funcion XOR
 */
public class NeuronalNetworkXOR {

	public static double learning = 0.25;
	public static int[][] dataSetNeuronOne = { { 0, 0, 0 }, { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
	public static int[][] dataSetNeuronTwo = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };
	public double weightNeuronOneX1, weightNeuronOneX2, weightNeuronTwoX1, weightNeuronTwoX2, baiasOne, baiasTwo;

	/**
	 * Constructor de la clase aqui se inicializan los valores de los pesos para con la finalidad 
	 * de empezar a iterar sobre ellos 
	 */
	public NeuronalNetworkXOR() {
		weightNeuronOneX1 = 0.20;
		weightNeuronOneX2 = 1;
		weightNeuronTwoX1 = 0.20;
		weightNeuronTwoX2 = -0.20;
		baiasOne = 0.05;
		baiasTwo = 0.05;
	}

	/**
	 * Metodo encargado de la Funcion de activacion
	 * 
	 * @param  valor a evaluar con hardlimit
	 * @return 0 o 1 segun corresponda lo que me va a permitir generar un valor booleano
	 */
	private int hardLimit(double value) {
		if (value >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Metodo encargado de realizar el aprendizaje de la neurona llama a la funcion
	 * evaluar pesos que permite realizar esta funcion.
	 * 
	 */

	/**
	 * Se encarga de evaluar el los data set de cada neurona, con ello evalua existe
	 *  un error y calcula de nuevamente  los valores de los pesos y el baias
	 * dependiendo del factor de aprendizaje 
	 * 
	 * @param matriz  data set de la neurona 1
	 * @param matriz2 data set de la neurona 2
	 */
	private void evaluarPesos(int[][] matriz, int[][] matriz2) {

		for (int i = 0; i < matriz.length; i++) {

			double y1 = matriz[i][0] * weightNeuronOneX1 + matriz[i][1] * weightNeuronOneX2 - baiasOne;
			double y2 = matriz2[i][0] * weightNeuronTwoX1 + matriz2[i][1] * weightNeuronTwoX2 - baiasTwo;

			int hardLimit1 = hardLimit(y1);
			int hardLimit2 = hardLimit(y2);

			int error1 = matriz[i][2] - hardLimit1;
			int error2 = matriz2[i][2] - hardLimit2;

			double delta1 = learning * error1 * matriz[i][0];
			double delta2 = learning * error1 * matriz[i][1];

			double delta3 = learning * error2 * matriz2[i][0];
			double delta4 = learning * error2 * matriz2[i][1];

			weightNeuronOneX1 = weightNeuronOneX1 + delta1;
			weightNeuronOneX2 = weightNeuronOneX2 + delta2;
			baiasOne = baiasOne - (learning * error1);

			weightNeuronTwoX1 = weightNeuronTwoX1 + delta3;
			weightNeuronTwoX2 = weightNeuronTwoX2 + delta4;
			baiasTwo = baiasTwo - (learning * error2);
		}
	}

	/**
	 * Metodo encargado de mostrar por pantalla los valores de las ecuaciones de las
	 * neuronas propuestas en este caso 2
	 */
	public void mostrarEcuaciones() {
		System.out.println(
				"Ecuacion neurona 1: " + weightNeuronOneX1 + " * X1 + " + weightNeuronOneX2 + " * X2 - " + baiasOne);
		System.out.println(
				"Ecuacion neurona 2: " + weightNeuronTwoX1 + " * X1 + " + weightNeuronTwoX2 + " * X2 - " + baiasTwo);
	}

	public void aprendizaje() {

		for (int i = 0; i < 100; i++) {
			evaluarPesos(dataSetNeuronOne, dataSetNeuronTwo);
		}
		imprimirSalida();
	}

	/**
	 * Metodo encargado de mostrarme la salida formateada de la evaluacion  de la funcion XOR
	 */
	private void imprimirSalida() {
		for (int i = 0; i < dataSetNeuronOne.length; i++) {
			int hardLimitN1 = hardLimit(
					dataSetNeuronOne[i][0] * weightNeuronOneX1 + dataSetNeuronOne[i][1] * weightNeuronOneX2 - baiasOne);
			int hardLimitN2 = hardLimit(
					dataSetNeuronTwo[i][0] * weightNeuronTwoX1 + dataSetNeuronTwo[i][1] * weightNeuronTwoX2 - baiasTwo);

			if (i == 1) {
				System.out.println(hardLimitN2 + " | " + hardLimitN1 + " = " + resultado(hardLimitN2, hardLimitN1));
			} else {
				System.out.println(hardLimitN1 + " | " + hardLimitN2 + " = " + resultado(hardLimitN1, hardLimitN2));
			}
		}
	}

	
	
	private int resultado(int x1, int x2) {
		return x1 == 1 && x2 == 0 || x1 == 0 && x2 == 1 ? 1 : 0;
	}

	public static void main(String[] args) {
		NeuronalNetworkXOR xor = new NeuronalNetworkXOR();
		xor.aprendizaje();
		xor.mostrarEcuaciones();
	}

}
