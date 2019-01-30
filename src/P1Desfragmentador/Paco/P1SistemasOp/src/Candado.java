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

public final class Candado extends JFrame implements ActionListener {

	private JButton[][] casilla;
	private final Object[][][] Tablero;
	private final String[] Contraseña = { "0", "3", "2", "9", "4" };
	private JButton jbutton1;
	private JButton jbutton2;
	private JButton jbutton3;
	private JPanel Panel_Matriz;
	private final Color[] colores;
	private Timer timer;

	class cola_de_Digitos implements Serializable {
		private final Color color;
		private int n_digitos = 10;

		public cola_de_Digitos(Color color, int digito, int j) {
			this.color = color;
			for (int i = 0; i < n_digitos; i++) {
				Tablero[0][i][j] = digito++;
				casilla[i][j].setText(String.valueOf(Tablero[0][i][j]));
				Tablero[1][i][j] = color;
				casilla[i][j].setBackground(color);
				casilla[i][j].setForeground(Color.BLACK);
			}
		}
	}// Cierra cola_de_Digitos

	protected ArrayList<cola_de_Digitos> candado;
	protected int n, m;

	public Candado(int n, int m) {
		this.n = n;
		this.m = m;
		Tablero = new Object[2][n][m];
		colores = new Color[5];
		colores[0] = Color.YELLOW;
		colores[1] = Color.RED;
		colores[2] = Color.ORANGE;
		colores[3] = Color.LIGHT_GRAY;
		colores[4] = Color.BLUE;
		iniciaComponentes();
	}

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
		iniciaDigitos(n, m);
		getContentPane().add(Panel_Matriz);

		fuente = new Font("Courier New", Font.BOLD, 16);
		jbutton1 = new JButton("Decifrado");
		jbutton1.setFont(fuente);
		jbutton1.addActionListener(this);
		jbutton2 = new JButton("Nuevo Candado");
		jbutton2.setFont(fuente);
		jbutton2.addActionListener(this);
		jbutton3 = new JButton("Salir");
		jbutton3.setFont(fuente);
		jbutton3.addActionListener(this);
		setBounds(0, 0, 580, 460);
		jbutton1.setBounds(25, 340, 100, 30);
		this.add(jbutton1);
		jbutton2.setBounds(215, 340, 180, 30);
		this.add(jbutton2);
		jbutton3.setBounds(425, 340, 110, 30);
		this.add(jbutton3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void iniciaDigitos(int i, int j) {
		Font fuente = new Font("Courier New", Font.BOLD, 14);
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				Tablero[0][i][j] = 0;
				Tablero[1][i][j] = Color.WHITE;
			}
		}

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

		candado = new ArrayList<>(5);
		for (i = 0; i < 5; i++) {
			cola_de_Digitos obj = new cola_de_Digitos(colores[i], 0, i);
			candado.add(obj);

		}
	}// Cierra iniciaDigitos

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

	private void comienzaSimulacion() {
		int i, j;
		Color[] aux_C = new Color[5];
		Color sol = Color.WHITE;
		int[] pos = new int[5];
		boolean salida = true;
		Random r = new Random();
		// Mostrar solucion

		LinkedList<Object[][][]> resultado = new LinkedList<>();
		do {
			// Primer for para el tipo de combinacion
			for (j = 0; j < 5; j++) {
				i = r.nextInt(10);
				pos[j] = i;
				aux_C[j] = (Color) Tablero[1][i][j];
				Tablero[1][i][j] = sol;
				Object[][][] TB = new Object[2][n][m];
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
		timer = new Timer(1, new ActionListener() {
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

		// Empieza la dclaracion del tablero mediante un for doble
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
