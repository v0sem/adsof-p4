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
			Recomendador rec = new RecomendadorDeVecinos(mod, 2);
			System.out.println(rec.recomienda(190L, 2));
		}
		catch(IOException | RecomendacionInvalida f){
			System.out.println(f);
		}
	}
}