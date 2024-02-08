package ciutada;

public class Ciutada implements Comparable<Ciutada> {
	private String nom, cognom, dni;

	public Ciutada(String nom, String cognom, String dni) {
		this.nom = nom;
		this.cognom = cognom;
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	/**
	 * Métode per comparar si dos elements tenen el mateix dni
	 * 
	 * @param other -> element (Ciutada) el qual es vol comparar
	 * @return resultat -> si resultat=0 els dos dni són iguals, en cas contrari
	 *         resultat=1
	 */
	@Override
	public int compareTo(Ciutada other) {
		int resultat;
		if (this.dni.equalsIgnoreCase(other.dni)) {
			resultat = 0;
		} else
			resultat = 1;
		return resultat;
	}

	@Override
	public String toString() {
		return "Ciutada [nom=" + nom + ", cognom=" + cognom + ", dni=" + dni + "]";
	}
}
