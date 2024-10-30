package main;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import suplementarias.Paquete;
import suplementarias.Vehiculo;

public class E1_JML {

	public static void main(String[] args) {
		ArrayList<Paquete> lineaCarga = new ArrayList<Paquete>();
		ArrayList<Paquete> lineaCarga2 = new ArrayList<Paquete>();
		ArrayList<Paquete> lineaCarga3 = new ArrayList<Paquete>();
		Semaphore semSalida = new Semaphore(1);
		for (int i = 0; i < 15; i++) {
			lineaCarga.add(new Paquete());
			lineaCarga2.add(new Paquete());
			lineaCarga3.add(new Paquete());
		}
		Vehiculo v1 = new Vehiculo("CA-1",lineaCarga,semSalida);
		Vehiculo v2 = new Vehiculo("CA-2",lineaCarga,semSalida);
		Vehiculo v3 = new Vehiculo("CA-3",lineaCarga2,semSalida);
		Vehiculo v4 = new Vehiculo("CA-4",lineaCarga2,semSalida);
		Vehiculo v5 = new Vehiculo("CA-5",lineaCarga3,semSalida);
		Vehiculo v6 = new Vehiculo("CA-6",lineaCarga,semSalida);
		v1.start();
		v2.start();
		v3.start();
		v4.start();
		v5.start();
		v6.start();
		try {
			v1.join();
			v2.join();
			v3.join();
			v4.join();
			v5.join();
			v6.join();
			if (lineaCarga.size()==0 && lineaCarga2.size()==0 && lineaCarga3.size()==0) {
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
