package models;


/**
	 * Esta clase crea e instancia un perceptron que puede diferenciar basado en ciertas caractertisticas un hombre y una mujer
	 *
	 */
	public class Neuronal {

		public static double learning = 0.25;
		private int[][] dataSetSeed = { { 0, 0, 1, 0, 1, 0, 2,1}, 
									{ 1, 1,	0, 0, 0, 1, 2, 1}, 
									{ 1, 0, 1, 1, 1, 0,	1, 0},
									{ 2, 1,	0, 1, 0, 1,	1, 1}, 
									{ 3, 0,	1, 1, 1, 0,	0, 0}, 
									{ 2, 0,	0, 1, 1, 0,	1, 0}, 
									{ 3, 1,	0, 0, 0, 1,	2, 1},
									{ 1, 0,	1, 0, 1, 0,	1, 0}, 
									{ 2, 1,	1, 0, 0, 1,	2, 1}, 
									{ 3, 0,	0, 0, 0, 1,	1, 1}, 
									{ 3, 0,	0, 0, 0, 1,	2, 1},
									{ 2, 0,	1, 1, 1, 0,	1, 0}, 
									{ 0, 0,	0, 1, 1, 0,	1, 0}, 
									{ 1, 0,	0, 1, 1, 0,	0, 0 }, 
									{ 2, 0,	1, 1, 1, 1,	2, 1 },
									{ 3, 0,	1, 0, 0, 0,	1, 0},
									{ 2, 1,	1, 0, 0, 1,	1, 1},
									{ 0, 1,	0, 0, 0, 1,	2, 1},
									{ 0, 1,	1, 1, 1, 0,	1, 0},
									{ 2, 1,	0, 1, 1, 0,	1, 0}
									};
		private int[][] dataSetTest = { { 1, 0,	1, 0, 1, 0,	1,0 }, 
										{ 0, 0,0, 1, 1, 0, 1,0 }, 
										{ 3, 0,	1, 1, 1, 0,	0,0 },
										{ 3, 0,	1, 0, 0, 0,	1,0 }, 
										{ 2, 1,	0, 1, 1, 0,	1,1 },
										{ 3, 0,	0, 0, 0, 1,	2,1 },
										{ 3, 1,	0, 0, 0, 1,	2,0},
										{ 2, 1,	0, 1, 0, 1,	1,1 },
										{ 1, 0,	0, 1, 1, 0,	0,1},
										{ 0, 1,	0, 0, 0, 1,	2,1},
										};
		public double weightX1, weightX2, weightX3, weightX4, weightX5, weightX6,weightX7, baias;

		/**
		 * Metodo constructor de la clase, inicializa las variables de los pesos y el
		 * baias, en primera instacia se colocan con un valor de cero (0)
		 */
		public Neuronal() {
			this.weightX1 = 0;
			this.weightX2 = 0;
			this.weightX3 = 0;
			this.weightX4 = 0;
			this.weightX5 = 0;
			this.weightX6 = 0;
			this.weightX7 = 0;
			this.baias = 0;
		}

		/**
		 * Metodo para crear la funcion de activacion hardLimit
		 * 
		 * @param value valor a convertir para activar
		 * @return el valor de la activacion
		 */
		private int hardLimit(double value) {
			if (value >= 0) {
				return 1;
			} else {
				return 0;
			}
		}

		/**
		 * Metodo en donde se inicia el aprendizaje del peceptron planteado 
		 * 
		 * @param recibe el numero de iteraciones que desean realizarse
		 */
		public void learning(int iteratios) {
			for (int i = 0; i < iteratios; i++) {
				evaluateWeights();
			}
			
		}

		/**
		 * Evalua la funcion conseguida en el metodo de aprendizaje con unos datos de prueba para
		 * calcular el porcentaje de efectividad del perceptron planteado 
		 * 
		 * @return porcentaje de efectividad del perceptron planteado
		 */
		public double winRate() {
			double count = 0;
			for (int i = 0; i < dataSetTest.length; i++) {
				int value = hardLimit(calculeYValue(dataSetTest[i][0], dataSetTest[i][1], dataSetTest[i][2],
						dataSetTest[i][3], dataSetTest[i][5], dataSetTest[i][5],dataSetTest[i][6]));
				if (value == dataSetTest[i][7]) {
					count++;
				}
			}
			System.out.println(count);
			System.out.println(dataSetTest.length);
			
			System.out.println(count/dataSetTest.length);
			return (count / dataSetTest.length) * 100;
		}

		/**
		 * Calcula el valor y evaluado en la ecuacion de aprendizaje Los parametros son
		 * cada uno de los valores por los cuales se multiplican los pesos y nos dan un resultado a evaluar
		 * 
		 * @param x1
		 * @param x2
		 * @param x3
		 * @param x4
		 * @param x5
		 * @param x6
		 * @return el valor de y evaluado en la ecuacion de aprendizaje
		 */
		private double calculeYValue(int x1, int x2, int x3, int x4, int x5, int x6,int x7) {
			return x1 * weightX1 + x2 * weightX2 + x3 * weightX3 + x4 * weightX4 + x5 * weightX5 + x6 * weightX6 + x7*weightX7 - baias;
		}

		/**
		 * Recorre el data set y va calculando valores y de cada dato del data set de
		 * entrenamiento y modifica los valores de los pesos y el baias si en algun
		 * momento se presenta un error al evaluar el aprendizaje
		 */
		private void evaluateWeights() {

			for (int i = 0; i < dataSetSeed.length; i++) {

				double y = calculeYValue(dataSetSeed[i][0], dataSetSeed[i][1], dataSetSeed[i][2], dataSetSeed[i][3], dataSetSeed[i][4],
						dataSetSeed[i][5],dataSetSeed[i][6]);

				int hardLimit = hardLimit(y);

				int error = dataSetSeed[i][7] - hardLimit;

				double delta1 = learning * error * dataSetSeed[i][0];
				double delta2 = learning * error * dataSetSeed[i][1];
				double delta3 = learning * error * dataSetSeed[i][2];
				double delta4 = learning * error * dataSetSeed[i][3];
				double delta5 = learning * error * dataSetSeed[i][4];
				double delta6 = learning * error * dataSetSeed[i][5];

				weightX1 = weightX1 + delta1;
				weightX2 = weightX2 + delta2;
				weightX3 = weightX3 + delta3;
				weightX4 = weightX4 + delta4;
				weightX5 = weightX5 + delta5;
				weightX6 = weightX6 + delta6;
				baias = baias - (learning * error);

				textPerceptron(dataSetSeed[i][0], dataSetSeed[i][1], dataSetSeed[i][2], dataSetSeed[i][3], dataSetSeed[i][4], dataSetSeed[i][5]
						,dataSetSeed[i][6]);
			}
		}

		/**
		 * Se encarga de concatenar y mostar por pantalla la ecuacion de el perceptron planteado
		 */
		public void showEquations() {
			System.out.println("Ecuacion de la neurona 1: (" + weightX1 + " * X1 + " + weightX2 + " * X2 + " + weightX3
					+ " * X3 + " + weightX4 + " * X4 + " + weightX5 + " * X5 + " + weightX6 + " * X6 - " + baias);
		}

		/**
		 * Se encarga de evaluar si una serie de caracteristicas ingresadas corresponden
		 * a un hombre o a una mujer, de acuerdo con el aprendizaje la funcion de
		 * activacion y la ecuacion del perceptron
		 * 
		 * Los parametros son las caracteristicas a evaluar
		 * 
		 * @param x1
		 * @param x2
		 * @param x3
		 * @param x4
		 * @param x5
		 * @param x6
		 * @return hombre o mujer dependiendo del resultado de la evaluacion 
		 */
		public String textPerceptron(int x1, int x2, int x3, int x4, int x5, int x6,int x7) {
			return (hardLimit(calculeYValue(x1, x2, x3, x4, x5, x6,x7)) == 1) ? "Hombre" : "Mujer";
		}

		public static void main(String[] args) {
			Neuronal perceptron = new Neuronal();
			perceptron.learning(100);
			perceptron.showEquations();
			System.out.println("Win Rate :" + perceptron.winRate() + "%");
			System.out.println(perceptron.textPerceptron(0, 1,	0, 0, 0, 1,	2)); //Hombre
			System.out.println(perceptron.textPerceptron(2, 0,	0, 0, 0, 0,	1)); // Mujer
		}
	

}