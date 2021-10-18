import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class Ventana extends JFrame{
    Board board;
    JPanel container;
    JPanel boardPanel;
    JPanel menu;
    JButton one, two, three, four, five, six, seven, eight, nine;
    JLabel titulo;
    JButton botonJugar;
    int numberTurns = 1;

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
        menu.setLayout(new GridLayout(4, 1));
        menu.add(new JLabel(""));
        titulo = new JLabel("Tic Tac Toe");
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setHorizontalAlignment( SwingConstants.CENTER );
        botonJugar = new JButton();
        botonJugar.setText("Jugar");
        botonJugar.addActionListener(new BotonJugarListener());
        menu.add(titulo);
        menu.add(botonJugar);
        menu.add(new JLabel(""));
        container.add(menu);
        add(container);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

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

    public class BotonJugarListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            board = new Board();
            numberTurns = 1;
            container.remove(menu);
            container.add(boardPanel);
            AI();
            paintBoard();
            revalidate();
            repaint();
        }
    }

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

    public void AI(){
        numberTurns++;
        Board auxB = board;
        int bestScore = Integer.MIN_VALUE;
        int[] selecction = new int[2];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(auxB.getSquares()[i][j] == Square.vacio){
                    auxB.getSquares()[i][j] = Square.X;
                    int score = minmax(auxB, numberTurns, false);
                    if(score > bestScore){
                        bestScore = score;
                        selecction[0] = i;
                        selecction[1] = j;
                    }
                    auxB.getSquares()[i][j] = Square.vacio;
                }
            }
        }
        board.getSquares()[selecction[0]][selecction[1]] = Square.X;
        checkIfWinner();
    }

    private void checkIfWinner(){
        int res = FindMatchWinner(board);
        if(numberTurns == 9 || res != 0){
            FindWinner();
        }
    }

    public int minmax(Board auxB, int filled, boolean turn){
        if(filled == 9) return FindMatchWinner(auxB);
        if(turn){
            int bestScore = Integer.MIN_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(auxB.getSquares()[i][j] == Square.vacio){
                        auxB.getSquares()[i][j] = Square.X;
                        int score = minmax(auxB, filled+1, false);
                        auxB.getSquares()[i][j] = Square.vacio;
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        }else {
            int bestScore = Integer.MAX_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(auxB.getSquares()[i][j] == Square.vacio){
                        auxB.getSquares()[i][j] = Square.O;
                        int score = minmax(auxB, filled+1, true);
                        auxB.getSquares()[i][j] = Square.vacio;
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }

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
