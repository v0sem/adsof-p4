package es.uam.eps.ads.p4.Classes;

import es.uam.eps.ads.p4.Exceptions.RecomendacionInvalida;
import es.uam.eps.ads.p4.Interfaces.ModeloDatos;
import es.uam.eps.ads.p4.Interfaces.Recomendador;

import java.util.HashMap;
import java.util.Map;

public class RecomendadorDePopularidad implements Recomendador {

	private ModeloDatos datos;
	private Map<Long, Integer> popularidad;
	
	public RecomendadorDePopularidad(ModeloDatos mod) {
		datos = mod;
		popularidad = new HashMap<Long, Integer>();
	}
	public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida {
		if(longitudRecomendacion <= 0)
			throw new RecomendacionInvalida(0);
		else if(!this.datos.isInIdUsuarios(u))
			throw new RecomendacionInvalida(1);
		
		Recomendacion rec = new Recomendacion(u); 
		
		for(Long i : datos.getItemsUnicos()) {
			if(!datos.getPreferenciasUsuario(u).containsKey(i)) {
				Map<Long, Double> items = datos.getPreferenciasItem(i);
				rec.insertRecommendation(i, (double) items.size());
			}
		}
		
		rec.onlyBest(longitudRecomendacion);
		return rec;
	}

}
