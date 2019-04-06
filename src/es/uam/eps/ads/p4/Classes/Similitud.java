package es.uam.eps.ads.p4.Classes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import es.uam.eps.ads.p4.Interfaces.ModeloDatos;
import es.uam.eps.ads.p4.Interfaces.SimilitudIn;


public class Similitud implements SimilitudIn {

	private ModeloDatos mod;

	public Similitud(ModeloDatos mod) {
		this.mod = mod;
	}

	public double sim(Long u1, Long u2) {
		Map<Long, Double> prefU1 = mod.getPreferenciasUsuario(u1);
		Map<Long, Double> prefU2 = mod.getPreferenciasUsuario(u2);
		//Set<Long> items = mod.getItemsUnicos();

		Set<Long> intersec = new HashSet<Long>(prefU1.keySet());
		intersec.retainAll(prefU2.keySet());

		Double numerator = 0.0;
		Double denUser1 = 0.0;
		Double denUser2 = 0.0;


		if(prefU1 == null || prefU2 == null)
			return 0.0;
		
		for(Long l : intersec){
			numerator += prefU1.get(l) * prefU2.get(l);
		}

		for(Long l : prefU1.keySet()){
			denUser1 += prefU1.get(l) * prefU1.get(l);
		}

		for(Long l : prefU2.keySet()){
			denUser2 += prefU2.get(l) * prefU2.get(l);
		}

		return numerator / Math.sqrt(denUser1 * denUser2);
	} 
}