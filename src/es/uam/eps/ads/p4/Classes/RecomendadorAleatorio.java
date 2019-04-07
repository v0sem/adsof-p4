package es.uam.eps.ads.p4.Classes;

import es.uam.eps.ads.p4.Exceptions.RecomendacionInvalida;
import es.uam.eps.ads.p4.Interfaces.ModeloDatos;
import es.uam.eps.ads.p4.Interfaces.Recomendador;

public class RecomendadorAleatorio implements Recomendador {

	private ModeloDatos datos;
	
	public RecomendadorAleatorio(ModeloDatos mod) {
		datos = mod;
	}
	
	public Recomendacion recomienda(Long u, int longitudRecomendacion) 
			throws RecomendacionInvalida {
				int max = 5;
				int min = 1;
				int range = max - min + 1;

				//lanzamos la excepcion si 
				if(longitudRecomendacion <= 0)
					throw new RecomendacionInvalida(0);
				else if(!this.datos.isInIdUsuarios(u))
					throw new RecomendacionInvalida(1);


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
