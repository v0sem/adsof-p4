package es.uam.eps.ads.p4.Classes;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import es.uam.eps.ads.p4.Exceptions.UsuarioNoRelevante;
import es.uam.eps.ads.p4.Interfaces.MetricaIn;
import es.uam.eps.ads.p4.Interfaces.ModeloDatos;

public abstract class Metrica implements MetricaIn{

	private ModeloDatos datos;
	private Double notaMinima;
	
	public Metrica(ModeloDatos mod, Double nota) {
		datos = mod;
		notaMinima = nota;
	}
	
	public abstract double evalua(Recomendacion rec, int n) throws UsuarioNoRelevante;
	
	public Set<Long> getItemsRelevantes(Long u) {
		if(!datos.getUsuariosUnicos().contains(u))
			return null;
		if(notaMinima > 5 || notaMinima < 1)
			return null;
		
		Set<Long> relevantItems = new TreeSet<Long>();
		
		for(Map.Entry<Long, Double> e : datos.getPreferenciasUsuario(u).entrySet()) {
			if(e.getValue() >= notaMinima)
				relevantItems.add(e.getKey());
		}
		
		return relevantItems;	
	}
}
