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
			System.out.println(rec2.recomienda(190L, 3));
			Recomendador rec3 = new RecomendadorAleatorio(mod1);
			System.out.println(rec3.recomienda(190L, 3));
		}
		catch(RecomendacionInvalida ri01){
			ri01.printStackTrace();
		}
		
		// checking how good the metric is for Aleatorio
		System.out.println("----------------ALEATORIO-----------------");
		Recomendador metricRec2 = new RecomendadorAleatorio(mod2);
		Metrica metric2 = new MetricaPrecisionN(mod3, 3.0);
		Metrica metric2r = new MetricaRecall(mod3, 3.0);
		int userTotal2 = mod3.getUsuariosUnicos().size();
		Double metricValue2, metricValue2r;
		Double metricValueTotal2 = 0.0;
		Double metricValueTotal2r = 0.0;
		
		for(Long user : mod3.getUsuariosUnicos()) {
			try {
				Recomendacion r = metricRec2.recomienda(user, 5);
				metricValue2 = metric2.evalua(r, 5);
				metricValue2r = metric2r.evalua(r, 5);
				metricValueTotal2 += metricValue2;
				metricValueTotal2r += metricValue2r;
			}
			catch(RecomendacionInvalida | UsuarioNoRelevante met01) {
				met01.printStackTrace();
			}
		}
		
		System.out.println("Metric average Precision value for Recomendador Aleatorio: " + metricValueTotal2 / (double)userTotal2);
		System.out.println("Metric average Recall value for Recomendador Aleatorio: " + metricValueTotal2r / (double)userTotal2);
		
		// checking how good the metric is for Popularidad
		System.out.println("----------------POPULARIDAD-----------------");
		Recomendador metricRec1 = new RecomendadorDePopularidad(mod2);
		Metrica metric1 = new MetricaPrecisionN(mod3, 3.0);
		Metrica metric1r = new MetricaRecall(mod3, 3.0);
		int userTotal1 = mod3.getUsuariosUnicos().size();
		Double metricValue1, metricValue1r;
		Double metricValueTotal1 = 0.0;
		Double metricValueTotal1r = 0.0;
		
		for(Long user : mod3.getUsuariosUnicos()) {
			try {
				Recomendacion r = metricRec1.recomienda(user, 5);
				metricValue1 = metric1.evalua(r, 5);
				metricValueTotal1 += metricValue1;
				metricValue1r = metric1r.evalua(r, 5);
				metricValueTotal1 += metricValue1;
				metricValueTotal1r += metricValue1r;
			}
			catch(RecomendacionInvalida | UsuarioNoRelevante met01) {
				met01.printStackTrace();
			}
		}
		
		System.out.println("Metric average value for Recomendador Popularidad: " + metricValueTotal1 / (double)userTotal1);
		System.out.println("Metric average Recall value for Recomendador Aleatorio: " + metricValueTotal1r / (double)userTotal2);
		
		// checking how good the metric is for Vecinos
		System.out.println("----------------VECINOS-----------------");
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