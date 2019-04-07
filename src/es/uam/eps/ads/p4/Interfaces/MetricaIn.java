package es.uam.eps.ads.p4.Interfaces;

import java.util.Set;

import es.uam.eps.ads.p4.Classes.Recomendacion;
import es.uam.eps.ads.p4.Exceptions.UsuarioNoRelevante;

public interface MetricaIn {
	
	public double evalua(Recomendacion rec, int n) throws UsuarioNoRelevante;
	public Set<Long> getItemsRelevantes(Long u);
}