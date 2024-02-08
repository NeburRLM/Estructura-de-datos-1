package aplicacio;

import estructuraTaulaHash.TaulaHash;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import estructuraLlistaDoble.LlistaDobleEncadenada;

public class AplicacioAnalitzarCostos {
	public static void main(String args[]) throws IOException {

		LlistaDobleEncadenada<Integer> ld;
		TaulaHash<Integer, Integer> th;

		// creem fitxers
		FileWriter llistaDoble = new FileWriter("llistaDoble.csv");
		FileWriter taulaHash = new FileWriter("taulaHash.csv");

		int valorIns, buscarRandom;
		int costosLlista[], costosTaula[];
		int sumaCostos = 0;
		double mitja = 0;
		double sumaDesv;
		double var = 0;
		double desv = 0;

		// LLISTA DOBLE ENCADENADA
		llistaDoble.write("TAMANY");
		llistaDoble.write(";");
		llistaDoble.write("MITJA");
		llistaDoble.write(";");
		llistaDoble.write("DESVIACIO");
		llistaDoble.write("\n");

		for (int tamany = 1000; tamany <= 50000; tamany = tamany + 1000) { // tamany primera llista=1000; increment
																			// +10000; tamany final=50000;
			ld = new LlistaDobleEncadenada<Integer>();
			costosLlista = new int[tamany]; // taula on guardarem els N costos de cada búsqueda
			llistaDoble.write(NumberFormat.getInstance(Locale.getDefault()).format((tamany))); // escribim el tamany a
																								// tractar en el fitxer
																								// csv
			llistaDoble.write(";");

			// INSERIM els N valors randoms segon el tamany que s'està tractant
			for (int j = 0; j < tamany; j++) {
				valorIns = (int) Math.floor(Math.random() * ((tamany / 2) - 1.0 + 1) + 1.0); // Valor entre M y N, els
																								// dos inclosos
				ld.inserir(valorIns); // Valor inserit entre tamany/2 i 1, els dos inclosos
			}

			// BUSQUEM els N valors randoms segon el tamany que s'està tractant
			for (int j = 0; j < tamany; j++) {
				buscarRandom = (int) Math.floor(Math.random() * ((tamany / 2) - 1.0 + 1) + 1.0); // valor random a
																									// buscar
				costosLlista[j] = ld.buscar(buscarRandom); // guardem el costos de cada búsqueda en una taula
			}

			// SUMEM els costos de cada búsqueda guardats en la taula anterior
			for (int i = 0; i < costosLlista.length; i++) {
				sumaCostos = (sumaCostos + costosLlista[i]);
			}

			// MITJA dels costos de les búsquedas realitzades
			mitja = (double) sumaCostos / tamany;
			double arrodMitja = Math.rint(mitja * 1000) / 1000; // funció per arrodonir el resultat amb 3 decimals
			llistaDoble.write(NumberFormat.getInstance(Locale.getDefault()).format((arrodMitja))); // escritura del
																									// resultat (part
																									// decimal indicada
																									// amb ,)
			llistaDoble.write(";");

			// VARIACIÓ (part de la fórmula per calcuar la desviació)
			for (int i = 0; i < tamany; i++) {
				sumaDesv = Math.pow(costosLlista[i] - mitja, 2);
				var = var + sumaDesv;
			}
			var = var / (tamany - 1);

			// DESVIACIÓ de la mostra
			desv = Math.sqrt(var);
			double arrodDesv = Math.rint(desv * 1000) / 1000;
			llistaDoble.write(NumberFormat.getInstance(Locale.getDefault()).format((arrodDesv))); // escritura del
																									// resultat (part
																									// decimal indicada
																									// amb ,)
			llistaDoble.write(";");

			ld = null;
			costosLlista = null;
			sumaCostos = 0;
			var = 0;
			llistaDoble.write("\n");
		}

		// TAULA HASH
		taulaHash.write("TAMANY");
		taulaHash.write(";");
		taulaHash.write("MITJA");
		taulaHash.write(";");
		taulaHash.write("DESVIACIO");
		taulaHash.write("\n");
		sumaCostos = 0;
		var = 0;

		for (int tamany = 1000; tamany <= 50000; tamany = tamany + 1000) { // tamany primera llista=1000; increment
																			// +10000; tamany final=50000;
			th = new TaulaHash<Integer, Integer>(tamany);
			costosTaula = new int[tamany]; // taula on guardarem els N costos de cada búsqueda
			taulaHash.write(NumberFormat.getInstance(Locale.getDefault()).format((tamany))); // escribim el tamany a
																								// tractar en el fitxer
																								// csv
			taulaHash.write(";");

			// INSERIM els N valors randoms segon el tamany que s'està tractant
			for (int j = 0; j < tamany; j++) {
				valorIns = (int) Math.floor(Math.random() * ((tamany / 2) - 1.0 + 1) + 1.0); // Valor entre M y N, els
																								// dos inclosos
				th.inserir(valorIns, valorIns); // Valor inserit entre tamany/2 i 1, els dos inclosos
			}

			// BUSQUEM els N valors randoms segon el tamany que s'està tractant
			for (int j = 0; j < tamany; j++) {
				buscarRandom = (int) Math.floor(Math.random() * ((tamany / 2) - 1.0 + 1) + 1.0); // valor random a
																									// buscar
				costosTaula[j] = th.buscar(buscarRandom); // guardem el costos de cada búsqueda en una taula
			}

			// SUMEM els costos de cada búsqueda guardats en la taula anterior
			for (int i = 0; i < costosTaula.length; i++) {
				sumaCostos = (sumaCostos + costosTaula[i]);
			}

			// MITJA dels costos de les búsquedas realitzades
			mitja = (double) sumaCostos / tamany;
			double arrodMitja = Math.rint(mitja * 1000) / 1000;
			taulaHash.write(NumberFormat.getInstance(Locale.getDefault()).format((arrodMitja)));
			taulaHash.write(";");

			// VARIACIÓ (part de la fórmula per calcuar la desviació)
			for (int i = 0; i < tamany; i++) {
				sumaDesv = Math.pow(costosTaula[i] - mitja, 2);
				var = var + sumaDesv;
			}
			var = var / (tamany - 1);

			// DESVIACIÓ de la mostra
			desv = Math.sqrt(var);
			double arrodDesv = Math.rint(desv * 1000) / 1000;
			taulaHash.write(NumberFormat.getInstance(Locale.getDefault()).format((arrodDesv)));
			taulaHash.write(";");

			th = null;
			costosTaula = null;
			sumaCostos = 0;
			var = 0;
			taulaHash.write("\n");
		}
		// tanquem fitxers
		llistaDoble.close();
		taulaHash.close();

	}
}