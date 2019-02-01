/**
 * Las clases siguientes, son clases de ayuda para realizar la primera prctica de
 * la materia de sistemass operativos 19I
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
*
* @author Francisco Javier Carrillo Pacheco
*/

public final class Candado extends JFrame implements ActionListener {

	private JButton[][] casilla;//Matriz de botones
	private final Object[][][] Tablero;//Tablero tridimencional de tres capas (digitos, colores y posicion), Nos servira para manipular todo
	private final String[] Contraseña = { "0", "3", "2", "9", "4" };//Contraseña por default
	private JButton jbutton1;
	private JButton jbutton2;
	private JButton jbutton3;
	private JPanel Panel_Matriz;
	private final Color[] colores;
	private Timer timer;
	/**
	 * Clase cola_de_digitos
	 * Esta clase nos ayudara a crear listas, que contendran como informacion
	 * toda una columna de digitos y su respactivo color.
	 * El implements Serializable, permitira a la clase comportance como una lista
	 * @author Francisco Javier 
	 *
	 */
	class cola_de_Digitos implements Serializable {
		private final Color color;//Color de  la columna 
		private int n_digitos = 10;//Numero de digitos que tendra la columna
		/**
		 * Contsructor de la clase cola_de_Digitos
		 * @param color: Color de  cada columna
		 * @param digito: Se inicia con cero para ir aumentando en el for del constructor hasta el 9
		 * @param j: Numero de la columna(1-5)
		 * La variable i del for que esta dentro del constructor, es la fila
		 */
		public cola_de_Digitos(Color color, int digito, int j) {
			this.color = color;
			for (int i = 0; i < n_digitos; i++) {
				Tablero[0][i][j] = digito++;//Se le asigna los digitos por culumna en la capa de digitos
				casilla[i][j].setText(String.valueOf(Tablero[0][i][j]));//Esos mismos digitos los tendran los botones
				Tablero[1][i][j] = color;//Se le asigna un color a una columna a la capa de color del tablero
				casilla[i][j].setBackground(color);//Ese color tambien se le asigna al boton
				casilla[i][j].setForeground(Color.BLACK);//color de texto del boton
			}
		}
	}// Cierra cola_de_Digitos

	//IMPORTANTE: Este ya es el candado (se crea a partir de la lista de cola_de_Digitos) Las 5 columnas
	//Se configura en inicia digitos
	protected ArrayList<cola_de_Digitos> candado;
	protected int n, m;//Filas y columnas (10,5)
	/**
	 * Constructor del candado
	 * @param n: filas
	 * @param m: Columnas
	 */
	public Candado(int n, int m) {
		this.n = n;
		this.m = m;
		Tablero = new Object[2][n][m];//Se crean las capas
		colores = new Color[5];//Se crea una arreglo de colores (una por cada columna)
		colores[0] = Color.YELLOW;
		colores[1] = Color.RED;
		colores[2] = Color.ORANGE;
		colores[3] = Color.LIGHT_GRAY;
		colores[4] = Color.blue;
		iniciaComponentes();
	}

	/**
	 * Este metodo inica y configura los componentes graficos de la app
	 */
	public void iniciaComponentes() {
		setLayout(null);
		Font fuente = new Font("Courier New", Font.BOLD, 12);
		casilla = new JButton[n][m];
		Panel_Matriz = new JPanel();
		Panel_Matriz.setFont(fuente);
		
		Panel_Matriz.setBorder(BorderFactory.createTitledBorder("Apertura del candado"));
		Panel_Matriz.setBounds(20, 20, 520, 300);
		Panel_Matriz.setLayout(new GridLayout(n, m));
		Panel_Matriz.setVisible(true);
		
		iniciaDigitos(n, m);//Importante 
		getContentPane().add(Panel_Matriz);

		fuente = new Font("Courier New", Font.BOLD, 16);
		jbutton1 = new JButton("Decifrado");
		jbutton1.setFont(fuente);
		jbutton1.addActionListener(this);//Se le agrega la personalidad al primer boton
		
		jbutton2 = new JButton("Nuevo Candado");
		jbutton2.setFont(fuente);
		jbutton2.addActionListener(this);//Se le agrega la personalidad al segundo boton
		
		jbutton3 = new JButton("Salir");
		jbutton3.setFont(fuente);
		jbutton3.addActionListener(this);//Se le agrega la personalidad al tercer boton
		
		setBounds(0, 0, 580, 460);//Ventana principal
		
		jbutton1.setBounds(25, 340, 150, 30);
		this.add(jbutton1);
		jbutton2.setBounds(210, 340, 180, 30);
		this.add(jbutton2);
		jbutton3.setBounds(425, 340, 110, 30);
		this.add(jbutton3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	/**
	 * Metodo inicia digitos en el tablero y en los botones
	 * @param i: Numero de filas (10)
	 * @param j: Numero de columnas (5)
	 */
	public void iniciaDigitos(int i, int j) {
		Font fuente = new Font("Courier New", Font.BOLD, 14);
		//En la capa de digitos, la inicializa en cero y en la capa de color las inicializa en blanco
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				Tablero[0][i][j] = 0;
				Tablero[1][i][j] = Color.WHITE;
			}
		}
		//Configura la matriz de botones, dejandolos en color blanco y configurando el texto de los mismos
		//Despues los agrega en el Panel_Matriz
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				casilla[i][j] = new JButton("");
				casilla[i][j].setFont(fuente);
				casilla[i][j].setBackground(Color.WHITE);
				casilla[i][j].setForeground(Color.BLACK);
				casilla[i][j].addActionListener(this);
				Panel_Matriz.add(casilla[i][j]);
			}
		}
		//Se configura el candado por medio del constructor de cola_de_Digitos
		//Se le otorga color y los digitos, tanto a las capas como a los botones
		candado = new ArrayList<>(5);
		for (i = 0; i < 5; i++) {
			cola_de_Digitos obj = new cola_de_Digitos(colores[i], 0, i);
			candado.add(obj);
		}
	}// Cierra iniciaDigitos
/**
 * Eventos para los botones
 * (Personalidad)
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbutton1) {
			comienzaSimulacion();
		} else if (e.getSource() == jbutton2) {
			nuevaSimulacion();
		} else if (e.getSource() == jbutton3) {
			System.exit(0);
		}
	}
	/**
	 * comienzaSimulacion
	 */
	private void comienzaSimulacion() {
		int i, j;
		Color[] aux_C = new Color[5];//Arreglo auxuliar de colores
		Color sol = Color.WHITE;//La matriz de botones se pinta de blanco al elegir un boton y nuero al azar
		int[] pos = new int[5];//posicion o fila  de cada columna
		boolean salida = true;//Bandera para poder salir del while
		Random r = new Random();//Variable para generar un numero aleatorio
		// Mostrar solucion

		LinkedList<Object[][][]> resultado = new LinkedList<>();
		do {
			// Primer for para el tipo de combinacion
			//j:columnas
			for (j = 0; j < 5; j++) {
				i = r.nextInt(10);//Se genera un numero aleatorio del 0 al 9(para las filas) y se le asigna a i para ver si le pega a la contraseña
				pos[j] = i;//se guarda la posiscion aleatoria en el arreglo
				aux_C[j] = (Color) Tablero[1][i][j];//Se guarda el color de la capa de color(Tablero 1) de la posicion aleatoria
				Tablero[1][i][j] = sol;//En esa posicion aleatoria se configura para que sea blanco en la capa de color
				Object[][][] TB = new Object[2][n][m];//Se crea un tablero auxiliar de tres capas como el otro
				for (int m1 = 0; m1 < 2; m1++) {
					for (int n1 = 0; n1 < n; n1++) {
						System.arraycopy(Tablero[m1][n1], 0, TB[m1][n1], 0, m);
					}
				}
				resultado.add(TB);
			}
			

			for (j = 0; j < 5; j++) {
				Tablero[1][pos[j]][j] = aux_C[j];
			}

			for (j = 0; j < 5; j++) {
				if (!(String.valueOf(Tablero[0][pos[j]][j]).equals(Contraseña[j]))) {

					break;
				}
			}

			if (j == 5) {
				salida = false;
			} else {
				Object[][][] TB = new Object[2][n][m];
				for (int m1 = 0; m1 < 2; m1++) {
					for (int n1 = 0; n1 < n; n1++) {
						System.arraycopy(Tablero[m1][n1], 0, TB[m1][n1], 0, m);
					}

				}
				resultado.add(TB);
			}

		} while (salida);

		int tamaño = resultado.size();
		timer = new Timer(0, new ActionListener() {
			private int i = 0;

			@Override
			public void actionPerformed(ActionEvent evt) {
				if (i >= tamaño) {
					timer.stop();
					return;
				}
				desplegarTablero(resultado.get(i));
				i++;
			}
		});
		timer.start();
	}// Cierra comienza Simulacion

	public void desplegarTablero(Object[][][] T_P) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				casilla[i][j].setText(String.valueOf(T_P[0][i][j]));
				casilla[i][j].setBackground((Color) T_P[1][i][j]);
				casilla[i][j].setForeground(Color.BLACK);
			}
		}

	}// Cierra desplegarTablero

	public void nuevaSimulacion() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Tablero[0][i][j] = 0;
			}
		}

		// Empieza la declaracion del tablero mediante un for doble
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// Reiniciamos las etiquetas
				casilla[i][j].setText("");
				// Establcer el color para el fondo de casilla
				casilla[i][j].setBackground(Color.WHITE);
				// Establece un color para el texto de casilla
				casilla[i][j].setBackground(Color.BLACK);
			}
		}

		// Inicializamos el arreglo para los archivos que seran desfragmentados
		candado = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			cola_de_Digitos obj = new cola_de_Digitos(colores[i], 0, i);
			candado.add(obj);

		}
	}// cierra nuevaSimulacion

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			new Candado(10, 5).setVisible(true);
		});
	}
}
