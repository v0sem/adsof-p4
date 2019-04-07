package es.uam.eps.ads.p4.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import es.uam.eps.ads.p4.Interfaces.ModeloDatos;
import es.uam.eps.ads.p4.Interfaces.Recomendador;
import es.uam.eps.ads.p4.Exceptions.RecomendacionInvalida;

public class RecomendadorDeVecinos implements Recomendador {

	private ModeloDatos datos;
	private Similitud sim;
	private int number;

	public RecomendadorDeVecinos(ModeloDatos mod, int k){
		this.datos = mod;
		this.sim = new Similitud(mod);
		this.number = k;
		
	}


	public Recomendacion recomienda(Long u, int longitudRecomendacion)              
		throws RecomendacionInvalida {
			if(longitudRecomendacion <= 0)
				throw new RecomendacionInvalida(0);
			else if(!this.datos.isInIdUsuarios(u))
				throw new RecomendacionInvalida(1);
			
			//TreeMap en el que vamos a almacenar todos los usuarios excepto al que vamos a recomendar  
			TreeMap<Double, Long> map = new TreeMap<Double, Long>();
			for(Long user : datos.getUsuariosUnicos()){
				if(!u.equals(user))
					map.put(sim.sim(u, user), user);
			}
			
			//guardamos en el tipo List los vecinos que han dado los scores mas altos
 			List<Long> vecinos = new ArrayList<Long>();
			for(int i=0; i < number; i++){
				Double d = map.lastKey();
				vecinos.add(map.remove(d));
			}

			Recomendacion rec = new Recomendacion(u);

			Double suma = 0.0;
			for(Long i : datos.getItemsUnicos()){
				if(!datos.getPreferenciasUsuario(u).containsKey(i)){
					for(Long v : vecinos){
						Map<Long, Double> items = datos.getPreferenciasItem(i);
						if(items != null) {
							Double rating = items.get(v);
							if(rating == null)
								rating = 0.0;
							suma += sim.sim(u, v) * rating;
						}
					}
					rec.insertRecommendation(i, suma);
				}
			}
			rec.onlyBest(longitudRecomendacion);
			return rec;
	}
	
	
	public Recomendacion recomiendaAleatoria(Long u, int longitudRecomendacion) 
		throws RecomendacionInvalida {
			int max = 5;
			int min = 1;
			int range = max - min + 1;
		
			//lanzamos la excepcion si 
			if(longitudRecomendacion <= 0)
				throw new RecomendacionInvalida(0);
			else if(!this.datos.isInIdUsuarios(u))
				throw new RecomendacionInvalida(1);
		
			//TreeMap en el que vamos a almacenar todos los usuarios excepto al que vamos a recomendar  
			TreeMap<Double, Long> map = new TreeMap<Double, Long>();
			for(Long user : datos.getUsuariosUnicos()){
				if(!u.equals(user))
					map.put(sim.sim(u, user), user);
			}
		
			//guardamos en el tipo List los vecinos que han dado los scores mas altos
				List<Long> vecinos = new ArrayList<Long>();
				for(int i=0; i < number; i++){
					Double d = map.lastKey();
					vecinos.add(map.remove(d));
				}
		
				//Creamos la recomendacion del usuario pedido
				Recomendacion rec = new Recomendacion(u);
				for(Long i : datos.getItemsUnicos()){
					if(!datos.getPreferenciasUsuario(u).containsKey(i)){
				
						//generamos un numero aleatorio entre 1 y 5
						double random = (Math.random()*range) + min;
						rec.insertRecommendation(i, random);
				
					}
				}
		
				rec.onlyBest(longitudRecomendacion);
				return rec;
	
		}
}