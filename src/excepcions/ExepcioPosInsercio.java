package excepcions;

public class ExepcioPosInsercio extends Exception {
	private static final long serialVersionUID = 1L;

	public ExepcioPosInsercio() {
		super("No s'ha pogut fer la inserció correctament ja que la posició és invàlida");
	}
}
