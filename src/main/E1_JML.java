package main;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import suplementarias.LineaCarga;
import suplementarias.Paquete;
import suplementarias.Vehiculo;

public class E1_JML {

	public static void main(String[] args) {
		ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		Semaphore semSalida = new Semaphore(1);
		for (int i = 0; i < 100; i++) {
			paquetes.add(new Paquete());
		}
		Vehiculo v1 = new Vehiculo("CA-1");
		Vehiculo v2 = new Vehiculo("CA-2");
		Vehiculo v3 = new Vehiculo("CA-3");
		Vehiculo v4 = new Vehiculo("CA-4");
		Vehiculo v5 = new Vehiculo("CA-5");
		Vehiculo v6 = new Vehiculo("CA-6");
		
		vehiculos.add(v1);
		vehiculos.add(v2);
		vehiculos.add(v3);
		vehiculos.add(v4);
		vehiculos.add(v5);
		vehiculos.add(v6);
		LineaCarga l1 = new LineaCarga(paquetes, vehiculos, semSalida);
		LineaCarga l2 = new LineaCarga(paquetes, vehiculos, semSalida);
		LineaCarga l3 = new LineaCarga(paquetes, vehiculos, semSalida);
		l1.start();
		l2.start();
		l3.start();
		try {
			l1.join();
			l2.join();
			l3.join();
			if (paquetes.size()==0 && l1.getContenido().size()==0 && l2.getContenido().size()==0 && l3.getContenido().size()==0) {
				System.out.println("Todos los paquetes han sido repartidos");
			} else {
				System.out.println("Quedan paquetes por repartir.");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
