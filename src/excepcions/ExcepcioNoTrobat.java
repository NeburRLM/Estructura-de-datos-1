package excepcions;

public class ExcepcioNoTrobat extends Exception {
	private static final long serialVersionUID = 1L;

	public ExcepcioNoTrobat(int i) {
		super("No s'ha trobat l'element. S'han accedit a " + i + " elements");
	}
}
