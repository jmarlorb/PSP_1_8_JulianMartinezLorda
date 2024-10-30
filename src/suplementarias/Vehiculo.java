package suplementarias;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Vehiculo extends Thread {

	private String idVehiculo;
	private int capacidadRestantePaquetes;
	private List<Paquete> lineaCarga;
	private Semaphore sem;
	public Vehiculo(String id, List<Paquete> lineaCarga, Semaphore sem) {
		super();
		this.idVehiculo = id;
		this.capacidadRestantePaquetes = (int)(9*(Math.random()/Math.nextDown(1.0)+1));
		this.lineaCarga = lineaCarga;
		this.sem = sem;
	}
	
	public void run() {
		Paquete auxActual;
		synchronized(this.lineaCarga) {
			System.out.println("El camión "+ this.idVehiculo+ " empieza a recibir paquetes de la linea de carga.");
			while (this.lineaCarga.size()>0 && this.capacidadRestantePaquetes>0) {
				auxActual=this.lineaCarga.remove(0);
				System.out.println("El camión " + this.idVehiculo+" comienza a cargar paquete.");
				try {
					Thread.sleep(auxActual.getTiempoCargaEnMilisegundos());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.capacidadRestantePaquetes--;
				System.out.println("El camión " + this.idVehiculo+ " termina de cargar paquete.");
			}
			try {
				sem.acquire();
				System.out.println("El camión " + this.idVehiculo+ " sale a reparto");
				sem.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.lineaCarga.notify();
		}
		
		
	}
	
	public String getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public int getCapacidadPaquetes() {
		return capacidadRestantePaquetes;
	}
	public void setCapacidadPaquetes(int capacidadPaquetes) {
		this.capacidadRestantePaquetes = capacidadPaquetes;
	}
	public List<Paquete> getLineaCarga() {
		return lineaCarga;
	}
	public void setLineaCarga(List<Paquete> lineaCarga) {
		this.lineaCarga = lineaCarga;
	}
	
	
	
	
}
