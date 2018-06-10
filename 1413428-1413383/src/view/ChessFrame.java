package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessFrame extends JFrame {
	
	

	
	
	public ChessFrame(String s, int larg, int alt) {
		super(s);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-larg/2;
		int y=sa/2-alt/2;
		
		setBounds(x,y,larg,alt);
		
		
		setSize(larg,alt);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	

}
