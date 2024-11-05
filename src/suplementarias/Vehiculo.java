package suplementarias;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {

	private String idVehiculo;
	private int capacidadRestantePaquetes;
	private List<Paquete> contenido;
	private LineaCarga lineaCarga;
	public Vehiculo(String id) {
		super();
		this.idVehiculo = id;
		this.capacidadRestantePaquetes = (int)(9*(Math.random()/Math.nextDown(1.0)+1));
		this.contenido = new ArrayList<Paquete>();
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
	public List<Paquete> getContenido() {
		return contenido;
	}
	public void setContenido(List<Paquete> contenido) {
		this.contenido = contenido;
	}
	public boolean hasLineaCarga() {
		return this.lineaCarga != null;
	}
	
	public void setLineaCarga(LineaCarga ln) {
		this.lineaCarga = ln;
	}
	
	
	
}
