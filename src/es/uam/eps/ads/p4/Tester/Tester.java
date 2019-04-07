package es.uam.eps.ads.p4.Tester;

import es.uam.eps.ads.p4.Interfaces.*;

import java.io.IOException;

import es.uam.eps.ads.p4.Classes.*;
import es.uam.eps.ads.p4.Exceptions.RecomendacionInvalida;
import es.uam.eps.ads.p4.Exceptions.UsuarioNoRelevante;

public class Tester {

	public static void main(String[] args) {
		
		ModeloDatos mod1 = new Datos();
		ModeloDatos mod2 = new Datos();
		ModeloDatos mod3 = new Datos();
		
		// Reading all of the txt files
		try {
			mod1.leeFicheroPreferencias("txt/PruebaTraining.txt");
			mod2.leeFicheroPreferencias("txt/training.txt");
			mod3.leeFicheroPreferencias("txt/test.txt");
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		
		// Trying out all of the Recomenders
		try{
			Recomendador rec1 = new RecomendadorDeVecinos(mod1, 2);
			System.out.println(rec1.recomienda(190L, 3));
			Recomendador rec2 = new RecomendadorDePopularidad(mod1);
			System.out.println(rec2.recomienda(175L, 3));
		}
		catch(RecomendacionInvalida ri01){
			ri01.printStackTrace();
		}
		
		// checking how good the metric is
		Recomendador metricRec = new RecomendadorDeVecinos(mod2, 100);
		Metrica metric = new MetricaPrecisionN(mod3, 3.0);
		int userTotal = mod3.getUsuariosUnicos().size();
		Double metricValue;
		Double metricValueTotal = 0.0;
		
		for(Long user : mod3.getUsuariosUnicos()) {
			try {
				Recomendacion r = metricRec.recomienda(user, 5);
				metricValue = metric.evalua(r, 5);
				metricValueTotal += metricValue;
			}
			catch(RecomendacionInvalida | UsuarioNoRelevante met01) {
				met01.printStackTrace();
			}
		}
		
		System.out.println("Metric average value for Recomendador de Vecinos: " + metricValueTotal / (double)userTotal);
		
	}
}