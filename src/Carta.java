import javax.swing.*;

public class Carta extends JButton{
	
	public String icoOculto;
	private int status = 1;
	public Carta() {
		setIcon(Rutinas.AjustarImagen("interrogacion.jpg", 230, 140));
	}
	
	public void setIcoOculto(String i) {
		icoOculto = i;
	}
	public void Gira() {
		if(status==1) {
			setIcon(Rutinas.AjustarImagen(icoOculto, 230, 140));
			status++;
		}else{
			setIcon(Rutinas.AjustarImagen("interrogacion.jpg", 230, 140));
			status--;
		}
	}

	
}
