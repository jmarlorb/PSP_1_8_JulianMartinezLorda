package suplementarias;

import java.util.List;

public class LineaCargaCargadorDePaquetes extends Thread {

	private List<Paquete> fuentePaquetes;
	private List<Paquete> contenidoLineaCarga;
	public LineaCargaCargadorDePaquetes(List<Paquete> fuentePaquetes, List<Paquete> contenidoLineaCarga) {
		super();
		this.fuentePaquetes = fuentePaquetes;
		this.contenidoLineaCarga = contenidoLineaCarga;
	}
	
	public void run() {
		while (true) {
			synchronized(this.fuentePaquetes) {
				if (this.fuentePaquetes.isEmpty()) break;
				else {
					this.contenidoLineaCarga.add(this.fuentePaquetes.removeFirst());
						}
					}
		}
	}
}
