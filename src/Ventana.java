/**
 * clase ventana
 * despliega la ventana de la interfaz para el usuario
 * También la lógica para la inteligencia artificial.
* @authors
 * Diego Gerardo Navarro González   A01338941
 * Alan Rodrigo Mendoza Aguilar     A01339625
 * J. Iván Morales González         A01652650
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame{
    Board board; //objeto board
    JPanel container; //panel que contiene toda la interfaz del usuario
    JPanel boardPanel; //panel que contiene los botones del tic tac toe
    JPanel menu; // panel inicial del juego
    JButton one, two, three, four, five, six, seven, eight, nine; // botones para cada casilla del juego
    JLabel titulo; //titulo del juego y desplegar el resultado del juego
    JButton botonJugar; //boton para iniciar el juego con el algoritmo minmax sin la poda alfa beta
    JButton botonPodaAlphaBeta; //boton para iniciar el juego con el algoritmo minmax con la poda alfa beta
    int numberTurns = 1; //iniciamos el número de casillas en uno porque la IA va a jugar primero
    boolean jugarConPoda = false; //esta variable se utiliza para saber le tipo de algoritmo a utilizar
    int numberCalls = 0; //desplegamos el número de llamadas a los metodos que implementan la IA

    /**
     * metodo de contrucción de la Interfaz de usuarios.
     */
    public Ventana(){
        super("Tic tac toe");
        one=new JButton("");
        one.addActionListener(new OneButtonListener());
        two = new JButton("");
        two.addActionListener(new TwoButtonListener());
        three = new JButton("");
        three.addActionListener(new ThreeButtonListener());
        four = new JButton("");
        four.addActionListener(new FourButtonListener());
        five = new JButton("");
        five.addActionListener(new FiveButtonListener());
        six = new JButton("");
        six.addActionListener(new SixButtonListener());
        seven = new JButton("");
        seven.addActionListener(new SevenButtonListener());
        eight = new JButton("");
        eight.addActionListener(new EightButtonListener());
        nine = new JButton("");
        nine.addActionListener(new NineButtonListener());
        one.setFont(new Font("", Font.BOLD, 28));
        two.setFont(new Font("", Font.BOLD, 28));
        three.setFont(new Font("", Font.BOLD, 28));
        four.setFont(new Font("", Font.BOLD, 28));
        five.setFont(new Font("", Font.BOLD, 28));
        six.setFont(new Font("", Font.BOLD, 28));
        seven.setFont(new Font("", Font.BOLD, 28));
        eight.setFont(new Font("", Font.BOLD, 28));
        nine.setFont(new Font("", Font.BOLD, 28));
        boardPanel = new JPanel();
        boardPanel= new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.add(one);
        boardPanel.add(two);
        boardPanel.add(three);
        boardPanel.add(four);
        boardPanel.add(five);
        boardPanel.add(six);
        boardPanel.add(seven);
        boardPanel.add(eight);
        boardPanel.add(nine);
        container = new JPanel();
        container.setLayout(new GridLayout(1, 1));
        menu = new JPanel();
        menu.setLayout(new GridLayout(5, 1));
        menu.add(new JLabel(""));
        titulo = new JLabel("Tic Tac Toe");
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setHorizontalAlignment( SwingConstants.CENTER );
        botonJugar = new JButton();
        botonJugar.setText("Jugar Normal");
        botonJugar.addActionListener(new BotonJugarListener());
        botonPodaAlphaBeta = new JButton();
        botonPodaAlphaBeta.setText("Jugar Poda alfa-beta");
        botonPodaAlphaBeta.addActionListener(new BotonJugarPodaListener());
        menu.add(titulo);
        menu.add(botonJugar);
        menu.add(botonPodaAlphaBeta);
        menu.add(new JLabel(""));
        container.add(menu);
        add(container);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

    /**
     * metodo para pintar en la interfaz del usuario los datos almacenados en el objeto board
     */
    public void paintBoard(){
        if(board.getSquares()[0][0] == Square.X)
            one.setText("X");
        else if(board.getSquares()[0][0] == Square.O)
            one.setText("O");
        else
            one.setText("");

        if(board.getSquares()[0][1] == Square.X)
            two.setText("X");
        else if(board.getSquares()[0][1] == Square.O)
            two.setText("O");
        else
            two.setText("");

        if(board.getSquares()[0][2] == Square.X)
            three.setText("X");
        else if(board.getSquares()[0][2] == Square.O)
            three.setText("O");
        else
            three.setText("");

        if(board.getSquares()[1][0] == Square.X)
            four.setText("X");
        else if(board.getSquares()[1][0] == Square.O)
            four.setText("O");
        else
            four.setText("");

        if(board.getSquares()[1][1] == Square.X)
            five.setText("X");
        else if(board.getSquares()[1][1] == Square.O)
            five.setText("O");
        else
            five.setText("");

        if(board.getSquares()[1][2] == Square.X)
            six.setText("X");
        else if(board.getSquares()[1][2] == Square.O)
            six.setText("O");
        else
            six.setText("");

        if(board.getSquares()[2][0] == Square.X)
            seven.setText("X");
        else if(board.getSquares()[2][0] == Square.O)
            seven.setText("O");
        else
            seven.setText("");

        if(board.getSquares()[2][1] == Square.X)
            eight.setText("X");
        else if(board.getSquares()[2][1] == Square.O)
            eight.setText("O");
        else
            eight.setText("");

        if(board.getSquares()[2][2] == Square.X)
            nine.setText("X");
        else if(board.getSquares()[2][2] == Square.O)
            nine.setText("O");
        else
            nine.setText("");
    }

    /**
     * este metodo despliega el resultado del juego.
     * cambia el tablero del juego por la pantalla inicial con el resultado
     */
    public void FindWinner(){
        int res = FindMatchWinner(board);
        switch (res){
            case 1:
                titulo.setText("AI gano!!");
                break;
            case -1:
                titulo.setText("Humano gano!!");
                break;
            default:
                titulo.setText("empate!!");
                break;
        }
        container.remove(boardPanel);
        container.add(menu);
        revalidate();
        repaint();
    }

    /**
     * sí presionamos el boton jugar se inicia el juego pero sin la poda alfa beta
     */
    public class BotonJugarListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            jugarConPoda = false;
            initiateGame();
        }
    }

    /**
     * metodo para saber sí presionamos el boton jugar se inicia el juego pero con la poda alfa beta
     */
    public class BotonJugarPodaListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            jugarConPoda = true;
            initiateGame();
        }
    }

    /**
     * metodo para iniciar el juego
     * se inician todas las variables a estados iniciales para el correcto funcionamiento.
     * quita el panel del menu y despliega el tablero para jugar
     */
    public void initiateGame(){
        numberCalls = 0;
        board = new Board();
        numberTurns = 1;
        container.remove(menu);
        container.add(boardPanel);
        AI();
        paintBoard();
        revalidate();
        repaint();
    }

    /**
     * los metodos siguientes son para sabes sí la casilla presionada por el usuario esta vacia.
     * en caso de estar vacia posiciona una O en el lugar. suma el número de llamadas. Checa sí hay un ganador.
     * en caso contrario la IA hace la jugada y pinta el tablero en la interfaz del usuario.
     * cada boton tiene un método diferente pero todos hacen más o menos lo mismo.
     */
    public class OneButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[0][0] == Square.vacio){
                board.getSquares()[0][0] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class TwoButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[0][1] == Square.vacio){
                board.getSquares()[0][1] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class ThreeButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[0][2] == Square.vacio){
                board.getSquares()[0][2] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class FourButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[1][0] == Square.vacio){
                board.getSquares()[1][0] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class FiveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[1][1] == Square.vacio){
                board.getSquares()[1][1] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class SixButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[1][2] == Square.vacio){
                board.getSquares()[1][2] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class SevenButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[2][0] == Square.vacio){
                board.getSquares()[2][0] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class EightButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[2][1] == Square.vacio){
                board.getSquares()[2][1] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    public class NineButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(board.getSquares()[2][2] == Square.vacio){
                board.getSquares()[2][2] = Square.O;
                numberTurns++;
                checkIfWinner();
                AI();
                paintBoard();
            }
        }
    }

    /**
     * Método para hacer que la Inteligencia Artificial haga su jugada.
     * para hacer esto vamos a simular todos las posibles tiradas que puede hacer la AI
     * con el tablero que esta desplegado en la IU. Todas las tiradas las analizaremos con el metodo min-max
     * encontraremos cual es la tirada con mejor resultado y colocaremos la X en el lugar que nos lleve a ese resultado.
     */
    public void AI(){
        numberTurns++; //incrementamos el número de turnos a uno ya que es el turno de la IA
        Board auxB = board; //creamos una copia del juego actual para no modificar la partida
        int bestScore = Integer.MIN_VALUE; // el mejor score es el menor valor posible de int para poder hacer las modificaciones
        int[] selecction = new int[2]; //iniciamos un arreglo para almacenar el tiro óptimo
        for(int i = 0; i < 3; i++){//recorreremos todos los renglones de la matriz del tablero
            for(int j = 0; j < 3; j++){//recorreremos todos los datos de cada renglon
                if(auxB.getSquares()[i][j] == Square.vacio){//checamos sí la casilla esta vacia
                    auxB.getSquares()[i][j] = Square.X;//pondremos una X en el lugar vacio en nuestra copia del tablero
                    int score = Integer.MIN_VALUE; //iniciamos el score con el menor int posible
                    if(jugarConPoda){//si estamos jugando con Poda alfa beta llamaremos el metodo minmax_alphabeta
                        score  = minmax_alphabeta(auxB, numberTurns, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    }
                    else {//si no estamos jugando con poda alfa beta llamamos el método normal
                        score = minmax(auxB, numberTurns, false);
                    }
                    if(score > bestScore){ //checamos si el score que obtuvimos del método es mayor que el bestscore
                        bestScore = score; //asignamos el bestscore como el score
                        selecction[0] = i; // guardamos la i que nos llevo al mejor score
                        selecction[1] = j; // guardamos la j que nos llevo al mejor score
                    }
                    auxB.getSquares()[i][j] = Square.vacio; // quitamos la X de la casilla para continuar con las siguientes tiradas.
                }
            }
        }
        //imprimimos el número de llamadas totales al algoritmo
        System.out.println("Número de llamadas " + ((jugarConPoda) ? "con" : "sin") + " poda alpha beta: " +  numberCalls);
        board.getSquares()[selecction[0]][selecction[1]] = Square.X; //ponemos la X en la posición que nos lleva a la tirada optima
        checkIfWinner(); //checamos sí ya econtramos un ganador.
    }

    /**
     * método para rechar sí ya encotnramos un ganador. En caso que sí lo encontremos termina el juego y
     * despliega la pantalla con el resultado.
     */
    private void checkIfWinner(){
        int res = FindMatchWinner(board);
        if(numberTurns == 9 || res != 0){
            FindWinner();
        }
    }

    /**
     * el metodo min max nos dice cual es la mejor tirada que podemos efectur con el tablero dado.
     * tenemos que escoger la mejor jugada dependiendo quien sea el jugador que esta tirando.
     * cuando gana el humano el juego es 1. Cuando gana la IA la tirada -1. el punto del algoritmo
     * es detectar la mejor tirada para ambos casos desplegando todas las ramas de posibilidades
     * dentro del juego inicial dado.
     *
     * @param auxB es el tablero
     * @param filled nos dice cuantas casillas estan llenas en el tablero
     * @param turn nos dice quien es el jugador que va. true es AI, false es humano
     * @return el resultado posible de esa jugada.
     */
    public int minmax(Board auxB, int filled, boolean turn){
        numberCalls++; // esta variable nos ayuda a saber cuantas veces se llamo al algoritmo por jugada
        if(filled == 9) return FindMatchWinner(auxB); // si todas las casillas en el juego estan llenas checamos quien gano
        int bestScoreHuman = Integer.MIN_VALUE; //seteamos el mejor escore del humano a el minimo valor de int
        int bestScoreAI = Integer.MAX_VALUE; //seteamos el mejor score de la IA a el maximo valor de int
        //recorremos con dos for todos los datos de la matriz.
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                //checamos sí el espacio en i, j esta vacio
                if(auxB.getSquares()[i][j] == Square.vacio) {
                    //checamos de quien es el turno
                    if(turn){
                        auxB.getSquares()[i][j] = Square.X; //en caso de IA ponemos una x en la casilla y recorremos las posibilidades pero con la tirada del humano
                        int score = minmax(auxB, filled + 1, false);//mandamos el algorimo con uan casilla más llena y ahora es turno del humano
                        bestScoreHuman = Math.max(bestScoreHuman, score); //si el valor del score es mejor que la besthumanscore la actualizamos
                    }else {
                        auxB.getSquares()[i][j] = Square.O;//en caso de humano ponemos una O en la casilla y recorremos las posibilidades pero con la tirada del IA
                        int score = minmax(auxB, filled+1, true);//mandamos el algorimo con uan casilla más llena y ahora es turno del IA
                        bestScoreAI = Math.min(bestScoreAI, score);//si el valor del score es mejor que la bestScoreAI la actualizamos
                    }
                    auxB.getSquares()[i][j] = Square.vacio; //volvemos a vaciar el espacio para poder poner las siguientes posibilidades.
                }
            }
        }
        return (turn) ? bestScoreHuman : bestScoreAI; //dependiendo de quien sea la tirada regresamos el mejor valor para ese jugador.
    }

    /**
     *  Este metodo hace lo mismo que el método minmax. La diferencia radica en que aquí tenemos dos
     *  variables (alpha y beta). alpha concierne al jugador max (IA) y beta concierne al jugador min (humano).
     *  La poda alfa beta nos ayuda a hacer el algoritmo min max de manera más eficiente ya que no tenemos que
     *  recorrer todoas las posibles ramas. recortando así ramas que ya no sean necesarias. El algoritmo toma su
     *  nombre de poda porque sí recortamos posibilidades en un analisis beta es una poda beta y en caso contrario
     *  podamos en alfa. la condición para poder podar es que alpha sea mayor o igual que beta. En caso que se
     *  cumpla esta condición ya no continuamos analizando las posibilidades.
     *
     * @param b es el tablero
     * @param filled nos dice cuantas casillas estan llenas en el tablero
     * @param turn nos dice quien es el jugador que va. true es AI, false es humano
     * @param rootAlpha es el alpha inicial de la rama actual
     * @param rootBeta el al beta inicial de la rama actual
     * @return
     */
    public int minmax_alphabeta(Board b, int filled, boolean turn, int rootAlpha, int rootBeta){
        numberCalls++; // esta variable nos ayuda a saber cuantas veces se llamo al algoritmo por jugada
        if(filled == 9) return FindMatchWinner(b);
        int alpha = rootAlpha; //seteamos la nueva alpha con la raiz alpha
        int beta = rootBeta; //seteamos la nueva beta con la raiz beta
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(b.getSquares()[i][j] == Square.vacio) {
                    if(turn){
                        b.getSquares()[i][j] = Square.X;
                        int score = minmax_alphabeta(b, filled + 1, false, alpha, beta);
                        alpha = Math.max(alpha, score);
                    }else {
                        b.getSquares()[i][j] = Square.O;
                        int score = minmax_alphabeta(b, filled+1, true, alpha, beta);
                        beta = Math.min(beta, score);
                    }
                    b.getSquares()[i][j] = Square.vacio;
                }
                //si alpha es mayor o igual a beta paramos el analisis y regresamos el valor alpha o beta dependiendo quien sea el jugaro que esta jugando.
                if(alpha >= beta){
                    return (turn) ? beta : alpha;
                }
            }
        }
        //regresamos el valor alpha sí la IA esta jugando o beta en caso que el humano juegue.
        return (turn) ? alpha : beta;
    }

    /**
     * este metodo nos ayuda a verificar quien fue el ganador del juego.
     * @param b tablero a analizar
     * @return 1 sí el ganador es el humano, -1 sí gano la IA y 0 en caso de empate.
     */
    public int FindMatchWinner(Board b){
        int diagL = 0, diagR = 0;
        int upRow = 0, midRow = 0, downRow = 0;
        int leftCol = 0, midCol = 0, rightCol = 0;

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(b.getSquares()[i][j] != Square.vacio){
                    if(i == 0 && j == 0)
                        diagL += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(i == 2 && j == 2)
                        diagL += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(i == 0 && j == 2)
                        diagR += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(i == 2 && j == 0)
                        diagR += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(i == 1 && j == 1){
                        diagR += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                        diagL += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    }
                    if(i == 0)
                        upRow += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(i == 1)
                        midRow += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(i == 2)
                        downRow += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(j == 0)
                        leftCol += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(j == 1)
                        midCol += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                    if(j == 2)
                        rightCol += (b.getSquares()[i][j] == Square.X) ? -1 : 1;
                }

                if(diagL == 3 || diagR == 3 || upRow == 3 || midRow == 3 || downRow == 3 || leftCol == 3 || midCol == 3 || rightCol == 3)
                    return -1;
                if(diagL == -3 || diagR == -3 || upRow == -3 || midRow == -3 || downRow == -3 || leftCol == -3 || midCol == -3 || rightCol == -3)
                    return 1;
            }
        }

        return 0;
    }
}
