import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Knappfonster extends JFrame implements ActionListener{
    private JButton knapp1;
	private JButton knapp2 = new JButton("Gul");
	private JPanel  myJPanel = new JPanel();
	private Color   bakgrund;
	
	public Knappfonster(){
	    	super("Exempel pao foenster med tvao knappar");
		knapp1 = new JButton("Roed");
		setLayout(new FlowLayout()); //Definiera hur komponenter ska placeras
		//laegg in alla komponenter
		add(knapp1);
		add(knapp2);
		add(myJPanel);
		//definiera lyssnare till knapparna
		knapp1.addActionListener(this);
		knapp2.addActionListener(this);
		myJPanel.setPreferredSize(new Dimension(200,200));
		setVisible(true);
		pack(); // i staellet foer setSize(), anpassar storleken efter komponenternas (knapparnas) storlek
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == knapp1){
			myJPanel.setBackground(Color.RED);
		}
		else if(ae.getSource() == knapp2){
			myJPanel.setBackground(Color.YELLOW);
		}
        myJPanel.repaint();
	}
	
	public static void main(String[] args){
		new Knappfonster();
	}
	
}
