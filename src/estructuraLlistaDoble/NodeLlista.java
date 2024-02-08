package estructuraLlistaDoble;

/**
 * 
 * @author RUBÉN
 *
 *         Classe node de la llista
 */

public class NodeLlista<T> {
	private NodeLlista<T> seguent;
	private NodeLlista<T> anterior;
	private T valor;

	/**
	 * Métode constructor de la classe node
	 * 
	 * @param valor -> valor que conté el node actual de la llista
	 */
	public NodeLlista(T valor) {
		this.valor = valor; // valor del node
		this.seguent = null; // següent node de la llista
		this.anterior = null; // anterior node de la llista
	}

	/**
	 * Métode get per obtenir el valor següent del node de la llista
	 * 
	 * @return seguent -> el node següent
	 */
	public NodeLlista<T> getSeguent() {
		return seguent;
	}

	/**
	 * Métode set per modificar el node següent
	 * 
	 * @param sig -> node al qual es vol modificar el node següent actual
	 */
	public void setSeguent(NodeLlista<T> sig) {
		this.seguent = sig;
	}

	/**
	 * Métode get per obtenir el valor anterior del node de la llista
	 * 
	 * @return anterior -> el node anterior
	 */
	public NodeLlista<T> getAnterior() {
		return anterior;
	}

	/**
	 * Métode set per modificar el node anterior
	 * 
	 * @param ant -> node al qual es vol modificar el node anterior actual
	 */
	public void setAnterior(NodeLlista<T> ant) {
		this.anterior = ant;
	}

	/**
	 * Métode get per obtenir el valor del node actual de la llista
	 * 
	 * @return valor -> valor guardat en el node actual
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * Métode set per modificar el valor que conté el node actual pel valor que es
	 * passa per paràmetre
	 * 
	 * @param val -> valor del node al qual es vol modificar
	 */
	public void setValor(T val) {
		this.valor = val;
	}
}
