package es.uam.eps.ads.p4.Interfaces;

import es.uam.eps.ads.p4.Classes.Recomendacion;
import es.uam.eps.ads.p4.Exceptions.RecomendacionInvalida;

public interface Recomendador{
	
	public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida;
}