import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Juego extends JFrame implements ActionListener{
	String imgs[] = {"ardilla.jpg","conejo.jpg","gato.jpg","pantera.jpg","perro.jpg",
						"pez.jpg","raton.jpg","tortuga.jpg","venado.jpg","zorro.jpg"};
	Carta cartas[];
	int select = 0, numCartas, numPares;
	private Carta carta1,carta2;
	
	public Juego() {
		super("Juego de Pares");
		HazInterfaz();
		HazEscuchas();
	}
	public void AsignaImagenes(){
		int n = numCartas/2;
		int j=0;
		for(int i=0;i<n;i++){
			cartas[i].icoOculto = imgs[i];
		}
		for(int i=n;i<cartas.length;i++,j++){
			cartas[i].icoOculto = imgs[j];
		}
	}
	public void Revolver(){
		Carta aux;
		int p1,p2;
		for(int i=0;i<100;i++){
			p1 = Rutinas.nextInt(0, cartas.length-1);
			p2 = Rutinas.nextInt(0, cartas.length-1);
			aux = cartas[p1];
			cartas[p1] = cartas[p2];
			cartas[p2] = aux;
		}
	}
	public void CreaPares() {
		numCartas = Rutinas.nextInt(16, 20);
		while(numCartas%2 != 0) 
			numCartas = Rutinas.nextInt(16, 20);
		cartas = new Carta[numCartas];
		for(int i=0 ; i<numCartas ; i++) {
			cartas[i] = new Carta();
		}
		AsignaImagenes();
	}
	
	
	public void HazInterfaz(){
		setLayout(new GridLayout(4,4));
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		CreaPares();
		Revolver();
		for(int i=0;i<cartas.length;i++)
			add(cartas[i]);
		
		setVisible(true);
		setResizable(false);
	}
	public void HazEscuchas(){
		for(int i=0 ; i<cartas.length ; i++)
			cartas[i].addActionListener(this);
	}
	
	public static void main(String[] args) {
		new Juego();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Carta Btn=(Carta) evt.getSource();
		Btn.Gira();
		select++;
		if (select==1)
			carta1 = Btn;
		if(select==2) {
			carta2 = Btn;
			carta2.update(carta2.getGraphics());
			try{
				Thread.sleep(800);
			} catch (Exception e){}
			Verifica();
			select = 0;
		}
		if(numPares == (numCartas/2)) {
			JOptionPane.showMessageDialog(null,"¡Has Ganado!");
		}
	}
	public void Verifica() {
		if(carta1.icoOculto.compareTo(carta2.icoOculto)!=0 || carta1==carta2) {
			carta1.Gira();
			carta2.Gira();
		}else {
			carta1.setEnabled(false);
			carta1.setDisabledIcon(carta1.getIcon());
			carta2.setEnabled(false);
			carta2.setDisabledIcon(carta1.getIcon());
			numPares++;
		}	
	}
}
