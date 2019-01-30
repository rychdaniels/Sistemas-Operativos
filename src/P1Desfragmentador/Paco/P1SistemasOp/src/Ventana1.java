/*
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
import java.util.Timer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Francisco Javier Carrillo Pacheco
 */
public class Ventana1 extends JFrame implements ActionListener {

    private JButton[][] Casilla;
    private final Object [][][]Tablero; //estructura tridimencional en capas, [digitos][colores][]
    private String[] Contraseña = {"0", "3", "2", "1", "4"};   //contraseña
    private JButton JButton1;
    private JButton JButton2;
    private JButton JButton3;
    private JPanel Panel_Matriz;
    private final Color[] colores;
    private Timer time;

    class Cola_de_Digitos implements Serializable { //clase de tipo linked list gracias al serializable, con esto podremos utilizar los metodos que tiene un linked y se hace las ligadurasde los nodos por defecto

        private final Color color;
        private int n_digitos = 10;
        /**
         * Constructor
         * @param color: color de cada columna
         * @param digito: digito de cada columna
         * @param j: la columna jajaj
         */
        public Cola_de_Digitos(Color color, int digito, int j) {
            this.color = color;
            for (int i = 0; i < n_digitos; i++) {
                Tablero[0][i][j] = digito++;// Se inicializan los digitos en el tablero (capa cero o de digitos) por cada coluna
                Casilla[i][j].setText(String.valueOf(Tablero[0][i][j]));//Se le pone ese digito a los botones de cada columna
                Tablero[1][i][j] = color;//En la capa1 o de color, se le asigna un color distinto a cada columna, por cada llamada a la clase
                Casilla[i][j].setBackground(color);  //Ese mismo color se le ponen a los botones 
                Casilla[i][j].setForeground(Color.BLACK);  //color de la variable 
            }

        }

    }

    public ArrayList<Cola_de_Digitos> candado;
    protected int n, m;//Filas y columnas de la matriz
    /**
     * Contructor de la clase Ventana1
     * @param n: numero de filas
     * @param m: numero de columnas
     */
    public Ventana1(int n, int m) {
    	this.n = n;
    	this.m = m;
    	Tablero = new Object[2][n][m];//Prguntar la capa 2
    	colores = new Color[5];
    	colores[0] = Color.yellow;
    	colores[1] = Color.RED;
    	colores[2] = Color.ORANGE;
    	colores[3] = Color.LIGHT_GRAY;
    	colores[4] = Color.blue;
    	iniciaComponentes();
    	
    }

    public void iniciaComponentes() {
        setLayout(null);
        Font fuente = new Font("Courier New", Font.BOLD, 12);  //es el tipo de letra, negritas o subrrayado, tamaño

        Casilla = new JButton[n][m];
        Panel_Matriz = new JPanel();
        Panel_Matriz.setFont(fuente);
        Panel_Matriz.setBorder(BorderFactory.createTitledBorder("Apertura de Ventana1"));
        Panel_Matriz.setBounds(20, 20, 750, 500);
        Panel_Matriz.setLayout(new GridLayout(n, m));
        Panel_Matriz.setVisible(true);

        iniciaDigitos(n, m);
        getContentPane().add(Panel_Matriz);

        fuente = new Font("Courier New", Font.BOLD, 14);
        JButton1 = new JButton("Desifrado");
        JButton1.setFont(fuente);
        JButton1.addActionListener(this);    //darle vida al boton

        JButton2 = new JButton("Nuevo Ventana1");
        JButton2.setFont(fuente);
        JButton2.addActionListener(this);    //darle vida al boton

        JButton3 = new JButton("Salir");
        JButton3.setFont(fuente);
        JButton3.addActionListener(this);    //darle vida al boton

        setBounds(0, 0, 800, 600);     //tamaño de la ventana completa
        JButton1.setBounds(25, 340, 100, 30);
        this.add(JButton1);

        JButton2.setBounds(215, 340, 180, 30);
        this.add(JButton2);

        JButton3.setBounds(425, 340, 110, 30);
        this.add(JButton3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void iniciaDigitos(int n, int m) {
        Font fuente = new Font("Courier New", Font.BOLD, 14);

        for (int i = 0; i < n; i++) {      //sirve para llenar las celdas vacias en la practica
            for (int j = 0; j < m; j++) {
                Tablero[0][i][j] = 0;
                Tablero[0][i][j] = Color.WHITE;

            }
        }

        for (int i = 0; i < n; i++) {      //sirve para llenar las celdas vacias en la practica
            for (int j = 0; j < m; j++) {
                Casilla[i][j] = new JButton("");         //inicializo y no truena el programa con el new pointer excepcion
                Casilla[i][j].setFont(fuente);
                Casilla[i][j].setBackground(Color.WHITE);
                Casilla[i][j].setForeground(Color.BLACK);
                Casilla[i][j].addActionListener(this);
                Panel_Matriz.add(Casilla[i][j]);

            }
        }

        candado = new ArrayList<>(5);
        for (int j = 0; j < 5; j++) {   //lo que esta mandado es la columna
            Cola_de_Digitos obj = new Cola_de_Digitos(colores[j], 0, j);
            candado.add(obj);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JButton1) {
            //comienzaSimulacion();
        } else if (e.getSource() == JButton2) {
            //nuevaSimulacion();
        } else if (e.getSource() == JButton3) {
            System.exit(0);
        }

    }

//    private void comienzaSimulacion() {
//
//        int i, j;
//        Color[] aux_C = new Color[5];
//        Color sol = Color.WHITE;  //Son las casillas que se iluminan blancas
//        int[] pos = new int[5];   //para regresarlos a su color
//        boolean salida = true;
//        Random r = new Random();
//        //Mostrar solucion
//        LinkedList<Object[][][]> resultado = new LinkedList<>();
//        do {
//            //primer for es para el numero de combinacion
//            for (j = 0; j < 5; j++) {
//                i = r.nextInt(10);
//                pos[j] = i;
//                aux_C[j] = (Color) Tablero[1][i][j];
//                Tablero[1][i][j] = sol;
//                Object[][][] TB = new Object[2][n][m];
//                for (int m1 = 0; m1 < 2; m1++) {
//                    for (int n1 = 0; n1 < n; n1++) {
//                        System.arraycopy(Tablero[m1][n1], 0, TB[m1][n1], 0, m);
//                    }
//                }
//               resultado.add(TB);
//            }
//            
//          
//
//        }
//        
//    
//    
//    }

}
