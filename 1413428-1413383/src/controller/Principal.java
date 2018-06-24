package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.ChessFrame;
import view.ChessPanel;

public class Principal {
	
	public static String path = "";
	public static Partida p;

	public static void main(String[] args) {
		
		
		ChessFrame f=new ChessFrame("XADREZ",680,700);
		ChessPanel cp = new ChessPanel();
		
		String input = (String)JOptionPane.showInputDialog(null, "Escolha a opcao e digite abaixo: \n "+ 
				"1 - Roque curto\r\n" + 
				"2 - Roque longo\r\n" + 
				"3 - Xeque\r\n" + 
				"4 - Xeque-mate\r\n" + 
				"5 - Empate por falta de opção de jogada (congelamento)\r\n" + 
				"6 - Promoção de peões\r\n" + 
				"7 - Nova partida \r\n" + 
				"8 - Recuperar Última partida \r\n" + 
				"",
				"Please enter new quantity", JOptionPane.QUESTION_MESSAGE,null,null,"");
		
		
		p = new Partida();
		p.comecarPartida(cp, input);
		f.getContentPane().add(cp);
		f.setVisible(true);
		
		
		f.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent we) {
		        int result = JOptionPane.showConfirmDialog(f,
		            "Deseja salvar a partida ?", "Exit Confirmation : ",
		            JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION) {
		          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		          p.save(path + "src/files/7");
		        }
		        else if (result == JOptionPane.NO_OPTION)
		          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      }
		    });
		
		

		
		

	}

}
