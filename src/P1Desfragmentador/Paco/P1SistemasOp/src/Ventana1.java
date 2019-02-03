/**
 * Las clases siguientes, son clases de ayuda para realizar la primera prctica de
 * la materia de sistemass operativos 19I
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
*
* @author Francisco Javier Carrillo Pacheco
*/

public final class Ventana1 extends JFrame implements ActionListener {

	private JButton[][] casilla;//Matriz de botones
	private final Object[][][] Tablero;//Tablero tridimencional de dos capas (digitos y colores), Nos servira para manipular todo
	private JButton jbutton1;
	private JButton jbutton2;
	private JButton jbutton3;
	private JMenuBar barra_Menu;
	private JMenu menu;
	private JMenuItem nuevaSimulacion;
	private JMenuItem salir;
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
		Random na = new Random();
		Random na2 = new Random();
		int nai, naj;
		private final Color color;//Color de la primera ilera de colores
		private int n_digitos = na.nextInt((n*m)/5);//Este valor debe de ser aleatorio por cada color aleatorio entre 1 a (#total de casillas/#de colores)
		/**
		 * Contsructor de la clase cola_de_Digitos
		 * @param color: Color de  cada columna
		 * @param digito: Se inicia con cero para ir aumentando en el for del constructor hasta el 9
		 * @param j: Numero de la columna(1-5)
		 * La variable i del for que esta dentro del constructor, es la fila
		 */
		public cola_de_Digitos(Color color, int digito, int j) {
			this.color=color;
			int rn;
			   int conty = 0,contr = 0,conto = 0,contg = 0,contb = 0,contw = 0;
			   for (int i = 0; i < 5; i++) {
					for (int j1 = 0; j1 < 10; j1++) {
						
						rn = na.nextInt()%6;
						while (rn<0){
							rn = na.nextInt()%6;
						}
						System.out.println(rn);
						/*if (rn==4){
							System.err.println(contb);
							++contb;
							casilla[i][j].setText(""+contb);
						}*/
							switch(rn){
							case 0:
							casilla[i][j1].setText(String.valueOf (++conty));
							//Tablero[0][i][j] = conty;//Se le asigna los digitos por culumna en la capa de digitos
							break;
							case 1:
								casilla[i][j1].setText(String.valueOf (++contr));
								break;
							case 2:
								casilla[i][j1].setText(String.valueOf (++conto));
								break;
							case 3:
								casilla[i][j1].setText(String.valueOf (++contg));
								break;
							case 4:
								//casilla[i][j1].setText(String.valueOf (++contb));
								break;
							case 5:
								++contw;
								//casilla[i][j].setText(String.valueOf (++contw));
								break;
							}
						Color colors = colores[rn];
						System.out.println(colors);
						
						//casilla[i][j1].setText(String.valueOf(Tablero[0][nai][naj]));//Esos mismos digitos los tendran los botones
						//Tablero[1][nai][naj] = color;//Se le asigna un color a una columna a la capa de color del tablero
						casilla[i][j1].setBackground(colors);
						casilla[i][j1].setForeground(Color.BLACK);//color de texto del boton
						casilla[i][j1].setBorder(new javax.swing.border.LineBorder(Color.BLACK));
						
						
						
					}
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
	
	public Ventana1(int n, int m) {
		this.n = n;
		this.m = m;
		Tablero = new Object[2][n][m];//Se crean las capas
		colores = new Color[6];//Se crea una arreglo de colores (una por cada columna)
		colores[0] = Color.YELLOW;
		colores[1] = Color.RED;
		colores[2] = Color.ORANGE;
		colores[3] = Color.LIGHT_GRAY;
		colores[4] = Color.blue;
		colores[5] = Color.WHITE;
		iniciaComponentes();
	}

	/**
	 * Este metodo inica y configura los componentes graficos de la app
	 */
	public void iniciaComponentes() {
		setLayout(null);
		setTitle("Desfragmentador");
		Font fuente = new Font("Arial", Font.BOLD, 12);
		casilla = new JButton[n][m];
		Panel_Matriz = new JPanel();
		Panel_Matriz.setFont(fuente);
		
		Panel_Matriz.setBorder(BorderFactory.createTitledBorder("Simulacion: Desfragmentador"));
		Panel_Matriz.setBounds(0, 0, 1077, 560);
		Panel_Matriz.setLayout(new GridLayout(n, m));
		Panel_Matriz.setVisible(true);
		
		iniciaDigitos(n, m);//Importante 
		getContentPane().add(Panel_Matriz);

		fuente = new Font("Arial", Font.BOLD, 16);
		jbutton1 = new JButton("Decifrado");
		jbutton1.setFont(fuente);
		jbutton1.addActionListener(this);//Se le agrega la personalidad al primer boton
		
		jbutton2 = new JButton("Nuevo Candado");
		jbutton2.setFont(fuente);
		jbutton2.addActionListener(this);//Se le agrega la personalidad al segundo boton
		
		jbutton3 = new JButton("Salir");
		jbutton3.setFont(fuente);
		jbutton3.addActionListener(this);//Se le agrega la personalidad al tercer boton
		
		barra_Menu = new JMenuBar();
		menu = new JMenu("Simulador");
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		barra_Menu.add(menu);
		
		
		nuevaSimulacion = new JMenuItem("Nueva Simulacion");
		nuevaSimulacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuevaSimulacion.addActionListener(this);
		nuevaSimulacion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));//Atajo
		menu.add(nuevaSimulacion);
		
		
		salir = new JMenuItem("Salir");
		salir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salir.addActionListener(this);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));//Atajo
		
		menu.add(salir);
		
		setBounds(0, 0, 1100, 660);//Ventana principal
		
		
		jbutton1.setBounds(50, 560, 150, 30);
		this.add(jbutton1);
		jbutton2.setBounds(520, 560, 180, 30);
		this.add(jbutton2);
		jbutton3.setBounds(850, 560, 110, 30);
		this.add(jbutton3);
		
		this.setJMenuBar(barra_Menu);
		setSize(1080,660);
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
		Font fuente = new Font("Arial", Font.BOLD, 14);
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
			cola_de_Digitos obj = new cola_de_Digitos(colores[i], 1, i);
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
		}else if(e.getSource()==nuevaSimulacion) {
			NuevaSimulacion vN = new NuevaSimulacion();
			vN.setVisible(true);
			
			setVisible(false);
			
		}else if(e.getSource()==salir) {
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
//				if (!(String.valueOf(Tablero[0][pos[j]][j]).equals(Contraseña[j]))) {
//
//					break;
//				}
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
				casilla[i][j].setForeground(Color.BLACK);
			}
		}

		// Inicializamos el arreglo para los archivos que seran desfragmentados
		candado = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			cola_de_Digitos obj = new cola_de_Digitos(colores[i], 0, i);
			candado.add(obj);

		}
	}// cierra nuevaSimulacion
	
	
	public class NuevaSimulacion extends JFrame {
		private JFrame ventanaEmergente;
		private JPanel contentPane;
		private JRadioButton rb1;
		private JRadioButton rb2;
		private JRadioButton rb3;
		private final ButtonGroup buttonGroup = new ButtonGroup();
		
		

		

		/**
		 * Create the frame.
		 */
		public NuevaSimulacion() {
			
			setTitle("Nueva Simulacion");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 622, 355);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
			
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(rb1.isSelected()) {
						//Una nueva ventana 15X30
						Ventana1 ventana = new Ventana1(15, 30);
						setVisible(false);
						
					}else if (rb2.isSelected()) {
						//Una nueva ventana 10X20
						Ventana1 ventana = new Ventana1(10, 20);
						ventana.setVisible(true);
						setVisible(false);
						
					}else if (rb3.isSelected()) {
						Ventana1 ventana = new Ventana1(5, 10);
						ventana.setVisible(true);
						setVisible(false);
					}
					
					
				}
			});
			
			JButton btnNewButton = new JButton("Cancelar");
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					contentPane.setVisible(true);
					
					
					
				}
			});
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(97)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton))
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(83, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(99)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addGap(48)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(43))
			);
			
			rb1 = new JRadioButton("Matriz 15x30");
			buttonGroup.add(rb1);
			
			rb2 = new JRadioButton("Matriz 10X20");
			buttonGroup.add(rb2);
			
			rb3 = new JRadioButton("Matriz 5X10");
			buttonGroup.add(rb3);
			
			
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(27)
						.addComponent(rb1)
						.addGap(51)
						.addComponent(rb2)
						.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
						.addComponent(rb3)
						.addGap(21))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(31)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(rb1)
							.addComponent(rb2)
							.addComponent(rb3))
						.addContainerGap(22, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
			contentPane.setLayout(gl_contentPane);
		}
	}


	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			new Ventana1(15, 30).setVisible(true);
		});
	}
}
