package suplementarias;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class LineaCarga extends Thread {

	private List<Paquete> contenido;
	private List<Paquete> fuentePaquetes;
	private List<Vehiculo> destinoPaquetes;
	private Vehiculo vehiculoActual;
	private Semaphore semSalida;

	public LineaCarga(List<Paquete> fuentePaquete, List<Vehiculo> destinoPaquete, Semaphore sem) {
		super();
		this.contenido = new ArrayList<Paquete>();
		this.fuentePaquetes = fuentePaquete;
		this.destinoPaquetes = destinoPaquete;
		this.semSalida = sem;
	}

	public void run() {
		Paquete paqueteAux;
		LineaCargaCargadorDePaquetes cargador = new LineaCargaCargadorDePaquetes(fuentePaquetes, contenido);
		cargador.start(); 
		boolean sentinel = true;
		while ((cargador.isAlive() || !this.contenido.isEmpty()) && sentinel) {
			if (this.vehiculoActual == null) {
				synchronized (this.destinoPaquetes) {
					if (!this.destinoPaquetes.isEmpty()) {
						this.vehiculoActual = this.destinoPaquetes.removeFirst();
						System.out.println("El camión " + this.vehiculoActual.getIdVehiculo() + " entra en linea de carga.");
						
					} else sentinel = false;
				}
				

			} else {
				
				if (!this.contenido.isEmpty()
						&& this.vehiculoActual.getContenido().size() < this.vehiculoActual.getCapacidadPaquetes()) {
					System.out.println("El camión " + this.vehiculoActual.getIdVehiculo() + " comienza a cargar paquete.");
					paqueteAux = this.contenido.removeFirst();
					this.vehiculoActual.getContenido().add(paqueteAux);
					try {
						Thread.sleep(paqueteAux.getTiempoCargaEnMilisegundos());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("El camión " + this.vehiculoActual.getIdVehiculo() + " termina de cargar paquete.");
					
				} else if (this.vehiculoActual.getContenido().size() >= this.vehiculoActual.getCapacidadPaquetes()){
					 try {
						semSalida.acquire();
						System.out.println("El camión " + this.vehiculoActual.getIdVehiculo() + " sale a reparto");
						this.vehiculoActual = null;
						semSalida.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		if (this.vehiculoActual!= null) {
			try {
				semSalida.acquire();
				System.out.println("El camión " + this.vehiculoActual.getIdVehiculo() + " sale a reparto");
				this.vehiculoActual = null;
				semSalida.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<Paquete> getContenido() {
		return contenido;
	}

	public void setContenido(List<Paquete> contenido) {
		this.contenido = contenido;
	}

	public List<Paquete> getFuentePaquetes() {
		return fuentePaquetes;
	}

	public void setFuentePaquetes(List<Paquete> fuentePaquetes) {
		this.fuentePaquetes = fuentePaquetes;
	}

	public List<Vehiculo> getDestinoPaquetes() {
		return destinoPaquetes;
	}

	public void setDestinoPaquetes(List<Vehiculo> destinoPaquetes) {
		this.destinoPaquetes = destinoPaquetes;
	}

	public Vehiculo getVehiculoActual() {
		return vehiculoActual;
	}

	public void setVehiculoActual(Vehiculo vehiculoActual) {
		this.vehiculoActual = vehiculoActual;
	}

}
