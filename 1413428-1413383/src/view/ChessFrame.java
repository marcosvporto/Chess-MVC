package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessFrame extends JFrame {
	
	
	private final int LARG_DEFAULT=1000;
	private final int ALT_DEFAULT=1000;
	
	
	public ChessFrame(String s) {
		super(s);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		
		
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	

}
