package es.uam.eps.ads.p4.Exceptions;

public class UsuarioNoRelevante extends Exception {

	private static final long serialVersionUID = 919937515513416439L;
	
	public UsuarioNoRelevante() {
	}
	
	@Override
	public String toString() {
		return "Relevant items cannot be empty";
	}

}
