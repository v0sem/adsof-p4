package es.uam.eps.ads.p4.Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recomendacion {

	private Long usuario;
	private List<Tupla> recomendaciones;

	public Recomendacion(Long u) {
		this.usuario = u;
		this.recomendaciones = new ArrayList<Tupla>();
	}
	
	public String toString() {
		return "Recomendacion [usuario=" + usuario + ", recomendaciones ="
				+ recomendaciones;
	}
	
	public List<Tupla> getRecomendaciones() {
		return recomendaciones;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void insertRecommendation(Long id, Double scr) {
		recomendaciones.add(new Tupla(id, scr));
	}

	public void onlyBest(int i){
		Collections.sort(recomendaciones);
		ArrayList<Tupla> nueva = new ArrayList<Tupla>();
		
		if(i > recomendaciones.size())
			i = recomendaciones.size();
		
		for(int j=0; j<i; j++){
			nueva.add(recomendaciones.remove(0));
		}
		
		recomendaciones = nueva;
	}
	
}