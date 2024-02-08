package excepcions;

public class ExepcioPosEsborrar extends Exception {
	private static final long serialVersionUID = 1L;

	public ExepcioPosEsborrar() {
		super("No s'ha pogut esborra l'element correctament ja que la posició és invàlida");
	}
}
