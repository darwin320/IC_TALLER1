package models;

/**
 *Clase que nos va a permitir el diseño de una red neuronal con la finalidad de obtener las ecuaciones 
 *para el para de coordenadas tridimensionales dadas
 *
 */

public class Neuronal26 {

	public static double learning = 0.2;
	public static int[][] dataSetNeurona1 = { {2,0,0  }, {1,1,0 }, { 2, 2, 0 }, { 3, 3, 0  }, {3,0,0 },
			{  0, 2, 1 } };
	public static int[][] dataSetNeurona2 = { { 2,0,0}, { 3, 3, 0 }, { 2, 2, 1 }, { 1, 1, 1 }, {0, 2, 1  },
			{ 3, 0, 0 } };
	public double weightN1X1, weightN1X2, weightN2X1, weightN2X2, baiasN1, baiasN2;

	/**
	 * Se inicializa el valor correspondiente a los pesos y al baias con la finalidad de poder cambiarlos de acuerdo a los metodos que siguen
	 */
	public Neuronal26() {
		weightN1X1 = 0;
		weightN1X2 = 0;
		weightN2X1 = 0;
		weightN2X2 = 0;
		baiasN1 = 0.05;
		baiasN2 = 0.05;
	}

	/**
	 * Funcion de activacion hardlimit
	 * 
	 * @param value valor a evaluar con hardlimit
	 * @return 0 o 1 valor booleano que retorna con base en el parametro de entrada 
	 */
	private int hardLimit(double value) {
		if (value >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 *Metodo que va a realizar el proceso de aprendizaje basandose en la funcion de calcularPesos de cada uno
	 *de los factores del sistema 
	 * 
	 */
	public void learning() {
		for (int i = 0; i < 10; i++) {
			evaluateWeights(dataSetNeurona1, dataSetNeurona2);
		}
		printOutput();
	}

	/**
	 * Da un formato de los datos de cada una de las neuronas y los muestra por pantalla
	 */
	private void printOutput() {
		for (int i = 0; i < dataSetNeurona1.length; i++) {
			int hardLimitN1 = hardLimit(dataSetNeurona1[i][0] * weightN1X1 + dataSetNeurona1[i][1] * weightN1X2 - baiasN1);
			int hardLimitN2 = hardLimit(dataSetNeurona2[i][0] * weightN2X1 + dataSetNeurona2[i][1] * weightN2X2 - baiasN2);

			System.out.println("(" + dataSetNeurona1[i][0] + " , " + dataSetNeurona1[i][1] + ")  pertenece a "
					+ result(hardLimitN1, hardLimitN2));
		}
	}

	/**
	 *da un resultado con base en las variables de cada neurona 
	 * 
	 * @param x1
	 * @param x2
	 * @return el valor esperado en formato String que se basa en los parametros de entrada
	 */
	private String result(int x1, int x2) {
		return x1 == 1 && x2 == 0 || x1 == 0 && x2 == 1 ? "A" : "B";
	}

	/**
	 * Evalua los dataSet de cada una de las neuronas y calcula si es necesario realizar un ajuste en los pesos o el baias, dependiendo de 
	 * valor de aprendizaje permite conectra la neurona resutante con el metodo printOutput
	 * 
	 * @param matriz  data set de la neurona 1
	 * @param matriz2 data set de la neurona 2
	 */
	private void evaluateWeights(int[][] matriz, int[][] matriz2) {

		for (int i = 0; i < matriz.length; i++) {

			double y1 = matriz[i][0] * weightN1X1 + matriz[i][1] * weightN1X2 - baiasN1;
			double y2 = matriz2[i][0] * weightN2X1 + matriz2[i][1] * weightN2X2 - baiasN2;

			int hardLimit1 = hardLimit(y1);
			int hardLimit2 = hardLimit(y2);

			int error1 = matriz[i][2] - hardLimit1;
			int error2 = matriz2[i][2] - hardLimit2;

			double delta1 = learning * error1 * matriz[i][0];
			double delta2 = learning * error1 * matriz[i][1];

			double delta3 = learning * error2 * matriz2[i][0];
			double delta4 = learning * error2 * matriz2[i][1];

			weightN1X1 = weightN1X1 + delta1;
			weightN1X2 = weightN1X2 + delta2;
			baiasN1 = baiasN1 - (learning * error1);

			weightN2X1 = weightN2X1 + delta3;
			weightN2X2 = weightN2X2 + delta4;
			baiasN2 = baiasN2 - (learning * error2);
		}
	}

	/**
	 * Metodo encargado de mostrar por pantalla los valores obtenidos de cada una de las ecuaciones que se generaron 
	 * en este caso para las dos neuronas 
	 */
	public void mostrarEcuaciones() {
		System.out.println("La Ecuacion de la neurona 1: " + weightN1X1 + " * X1 + " + weightN1X2 + " * X2 - " + baiasN1);
		System.out.println("La Ecuacion de la neurona 2: " + weightN2X1 + " * X1 + " + weightN2X2 + " * X2 - " + baiasN2);
	}

	public static void main(String[] args) {
		Neuronal26 pointSeparator = new Neuronal26();
		pointSeparator.learning();
		pointSeparator.mostrarEcuaciones();
	}

}