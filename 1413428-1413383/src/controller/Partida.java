package controller;

import java.io.IOException;
import view.ChessPanel;
import controller.Principal;
import model.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Partida {
	
	static Tabuleiro t;
	private static BufferedReader bufferedReader;
	
	public Partida() {
		t = Tabuleiro.getTabuleiro();
	}
	
	public static void comecarPartida(ChessPanel p, String input) {
		Casa[][] c;
		c = t.getMatrizCasas();
		
		switch (input) {
        case "1":
        		// INICIALIZA A MATRIZ COM ROQUE CURTO
    			getSavedGame( Principal.path + "src/files/1.txt", c);
            break;
            
        case "2":
    			// INICIALIZA A MATRIZ COM ROQUE LONGO
			getSavedGame( Principal.path + "src/files/2.txt", c);
			break;
            
        case "3":
            System.out.println("Terça-feira");
            break;
            
        case "4":
            System.out.println("Quarta-feira");
            break;
            
        case "5":
            System.out.println("Quinta-feira");
            break;
            
        case "6":
			// INICIALIZA A MATRIZ COM ROQUE LONGO
        		getSavedGame( Principal.path + "src/files/6.txt", c);
        		break;
            
        case "7":
        		// INICIALIZA A MATRIZ DE NOVO JOGO
	        	for(int i = 0;i<8;i++) {
	    			switch(i) {
	    			case 0: c[i][0].setPeca(new Torre(  'p'));break;
	    			case 1: c[i][0].setPeca(new Cavalo( 'p'));break;
	    			case 2: c[i][0].setPeca(new Bispo(  'p'));break;
	    			case 3: c[i][0].setPeca(new Dama(   'p'));break;
	    			case 4: c[i][0].setPeca(new Rei(    'p'));break;
	    			case 5: c[i][0].setPeca(new Bispo(  'p'));break;
	    			case 6: c[i][0].setPeca(new Cavalo( 'p'));break;
	    			case 7: c[i][0].setPeca(new Torre(  'p'));break;
	    			
	    			}
	    		}
	    		for(int i = 0;i<8;i++) {
	    			switch(i) {
	    			case 0: c[i][7].setPeca(new Torre(  'b'));break;
	    			case 1: c[i][7].setPeca(new Cavalo( 'b'));break;
	    			case 2: c[i][7].setPeca(new Bispo(  'b'));break;
	    			case 3: c[i][7].setPeca(new Dama(    'b'));break;
	    			case 4: c[i][7].setPeca(new Rei(   'b'));break;
	    			case 5: c[i][7].setPeca(new Bispo(  'b'));break;
	    			case 6: c[i][7].setPeca(new Cavalo( 'b'));break;
	    			case 7: c[i][7].setPeca(new Torre(  'b'));break;
	    			}
	    		}
	    		for(int i=0;i<8;i++) {
	    			c[i][1].setPeca(new Peao('p'));
	    			c[i][6].setPeca(new Peao('b'));
	    		}
            break;
        case "8":
        		// INICIALIZA A MATRIZ DE ULTIMO JOGO
        		getSavedGame( Principal.path + "src/files/7.txt", c);
            break;
        default:
             System.out.println("Este não é um dia válido!");
     }	
	}
	

	
	public void save(String filePath) {		
		try {
			PrintWriter writer = new PrintWriter(filePath + ".txt", "UTF-8");
			Casa[][] matriz = t.getMatrizCasas();
			for (int i = 0; i < 8; i++) {	
				for(int j = 0; j < 8; j++) {
					
					Peca auxPeca = matriz[j][i].getPeca();
					if(auxPeca != null) {
						writer.printf( auxPeca.getTipo().getTipo() +  " " + auxPeca.getCor());
						writer.println();
					} else {
						writer.printf( "nulo" + " " + "nulo");
						writer.println();
					}	
				}
			}
			writer.close();
		} 
		catch (IOException e) {
			System.out.println("Partida : save : error = " + e.getMessage());
			System.exit(1);
		}
	}
	
	public static void getSavedGame(String filePath, Casa[][] c) {

		try {
				bufferedReader = new BufferedReader(new FileReader(filePath));
			    String currentLine = bufferedReader.readLine();
			    String[] currentComponents = currentLine.split(" ");
			    int i = 0;
			    int j = 0;
			    int nLinhas = 0;
		
			    while (currentLine != null) {
		
			    	nLinhas++;
			    	char cor = currentComponents[1].charAt(0);
			    	switch(currentComponents[0]) {
				    	case "torre": c[i][j].setPeca(new Torre( cor )); break;
				    	case "bispo": c[i][j].setPeca(new Bispo( cor )); break;
				 	case "cavalo":c[i][j].setPeca(new Cavalo( cor ));break;
				    	case "dama":  c[i][j].setPeca(new Dama( cor )); break;
				    	case "rei":   c[i][j].setPeca(new Rei( cor ));    break;
				    	case "peao":  c[i][j].setPeca(new Peao( cor ));  break;
				    	case "nulo": break;
				}
		    		
		    		currentLine = bufferedReader.readLine();
	
		    		//evita problema: nao pode fazer split na proxima linha depois da ultima linha 
		    		if(currentLine != null) {
		    			currentComponents = currentLine.split(" ");
		    		}
		    		
		    		i++;
		    		if(i==8) {
		    			j++;
		    			i=0;
		    		}
		    		
		    }//fim do While
			    
		    System.out.printf("Numero de linhas lidas = %d",nLinhas);
		}//fim do Try
		
		catch (IOException e) {
			System.out.println("GameController : retrieveSavedGame : error = " + e.getMessage());
			System.exit(1);			
		}//fim do catch
		
	}// fim da funcao getSavedGame()
	
}//fim da classe Partida
