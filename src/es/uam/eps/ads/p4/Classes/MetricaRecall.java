package es.uam.eps.ads.p4.Classes;

import java.util.Set;
import java.util.TreeSet;

import es.uam.eps.ads.p4.Exceptions.UsuarioNoRelevante;
import es.uam.eps.ads.p4.Interfaces.ModeloDatos;

public class MetricaRecall extends Metrica{

	public MetricaRecall(ModeloDatos mod, Double nota) {
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
		
		RecU.retainAll(RelItems);
		
		return (double)RecU.size()/(double)RelItems.size();
	}

}
