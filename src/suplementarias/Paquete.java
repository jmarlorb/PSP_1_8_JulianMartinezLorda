package suplementarias;

public class Paquete {

	private long tiempoCargaEnMilisegundos;

	public Paquete() {
		super();
		this.tiempoCargaEnMilisegundos = (long)((Math.random()/Math.nextDown(1.0))*2000 + 1000);
		
	}

	public long getTiempoCargaEnMilisegundos() {
		return tiempoCargaEnMilisegundos;
	}

	public void setTiempoCargaEnMilisegundos(long tiempoCargaEnMilisegundos) {
		this.tiempoCargaEnMilisegundos = tiempoCargaEnMilisegundos;
	}
	
	
	
	
}
