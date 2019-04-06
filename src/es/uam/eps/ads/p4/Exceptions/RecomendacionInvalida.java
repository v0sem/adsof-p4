package es.uam.eps.ads.p4.Exceptions;

public class RecomendacionInvalida extends Throwable {

	private static final long serialVersionUID = 610632911721530918L;
	private int id;

	public RecomendacionInvalida(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		if(this.id == 1)
			return "Invalid User (" + this.id + ")";
		else
			return "Recommendation lenght 0 or less (" + this.id +")";
	}
}