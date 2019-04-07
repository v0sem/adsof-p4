package es.uam.eps.ads.p4.Classes;

import es.uam.eps.ads.p4.Exceptions.UsuarioNoRelevante;

import java.util.Set;
import java.util.TreeSet;

import es.uam.eps.ads.p4.Classes.Metrica;
import es.uam.eps.ads.p4.Interfaces.ModeloDatos;

public class MetricaPrecisionN extends Metrica{

	public MetricaPrecisionN(ModeloDatos mod, Double nota) {
		super(mod, nota);
	}
	
	public double evalua(Recomendacion rec, int n) throws UsuarioNoRelevante {
		
		if(rec == null || n <=0)
			throw new UsuarioNoRelevante();
		
		Set<Long> RelItems = super.getItemsRelevantes(rec.getUsuario());
		if(RelItems == null || RelItems.size() == 0)
			throw new UsuarioNoRelevante();
		
		rec.onlyBest(n);
		
		Set<Long> RecU = new TreeSet<Long>();
		for(Tupla t : rec.getRecomendaciones()) {
			RecU.add(t.getItemId());
		}
		
		RelItems.retainAll(RecU);
		
		return (double)RelItems.size()/(double)n;
	}

}
