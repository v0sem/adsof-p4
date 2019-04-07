package es.uam.eps.ads.p4.Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet; 

import es.uam.eps.ads.p4.Interfaces.*;

public class Datos implements ModeloDatos{

	private Set<Long> idUsuarios;

	private Set<Long> idItems;

	private Map<Long, Map<Long, Double>> allPreferences;

	public Datos(){
		idUsuarios = new TreeSet<Long>();
		idItems = new TreeSet<Long>();

		allPreferences = new HashMap<Long, Map<Long, Double>>();
	}
	
	public void leeFicheroPreferencias(String ruta) throws IOException{

		FileReader fr = new FileReader(ruta);
		BufferedReader br = new BufferedReader(fr);
		String sCurrentLine = null;

		while((sCurrentLine = br.readLine()) != null){
			String array[] = sCurrentLine.split("\t");
			Long usuario = Long.parseLong(array[0]);
			Long item = Long.parseLong(array[1]);
			Double rating = Double.parseDouble(array[2]); 

			if(!isInIdUsuarios(usuario))
				this.idUsuarios.add(usuario);
			
			if(!isInIdItems(item))
				this.idItems.add(item);

			Map<Long, Double> map = null;

			if((map = getPreferenciasUsuarioMod(usuario)) != null){
				map.put(item, rating);
				allPreferences.put(usuario, map);
			}
			else{
				map = new HashMap<Long, Double>();
				map.put(item, rating);
				allPreferences.put(usuario, map);
			}
		}
		
		br.close();
		fr.close();
	}
	
	public Map<Long, Double> getPreferenciasUsuario(Long usuario){

		Map<Long, Double> map = this.allPreferences.get(usuario);

		if(!isInIdUsuarios(usuario) || map == null){
			return null;
		}
		return Collections.unmodifiableMap(map);
	}
	
	private Map<Long, Double> getPreferenciasUsuarioMod(Long usuario){

		Map<Long, Double> map = this.allPreferences.get(usuario);

		if(!isInIdUsuarios(usuario) || map == null){
			return null;
		}
		return map;
	}
	
	public Map<Long, Double> getPreferenciasItem(Long item){
		
		Map<Long, Double> items = new HashMap<Long, Double>();

		if(!isInIdItems(item)){
			return null;
		}

		for(Long user : idUsuarios) {
			Double rating = getPreferenciasUsuario(user).get(item);
			if(rating != null)
				items.put(user, rating);
		}

		return Collections.unmodifiableMap(items);
	}
	
	public Set<Long> getUsuariosUnicos(){
		return Collections.unmodifiableSet(idUsuarios);
	}
	
	public Set<Long> getItemsUnicos(){
		return Collections.unmodifiableSet(idItems);
	}
	
	//Otros metodos que los/las estudiantes crean oportuno

	public Boolean isInIdUsuarios(Long usuario){

		for(Long u : this.idUsuarios){
			if(u.equals(usuario))
				return true;
		}

		return false;
	}

	private Boolean isInIdItems(Long item){
		for(Long i : this.idItems){
			if(i.equals(item))
				return true;
		}

		return false;
	}	
}