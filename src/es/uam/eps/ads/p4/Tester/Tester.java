package es.uam.eps.ads.p4.Tester;

import es.uam.eps.ads.p4.Interfaces.*;

import java.io.IOException;

import es.uam.eps.ads.p4.Classes.*;
import es.uam.eps.ads.p4.Exceptions.RecomendacionInvalida;

public class Tester {

	public static void main(String[] args) {
		
		ModeloDatos mod = new Datos(); 
		
		try{
			mod.leeFicheroPreferencias("txt/PruebaTraining.txt");
			Recomendador rec1 = new RecomendadorDeVecinos(mod, 2);
			System.out.println(rec1.recomienda(190L, 3));
			Recomendador rec2 = new RecomendadorDePopularidad(mod);
			System.out.println(rec2.recomienda(175L, 3));
		}
		catch(IOException | RecomendacionInvalida f){
			System.out.println(f);
		}
	}
}