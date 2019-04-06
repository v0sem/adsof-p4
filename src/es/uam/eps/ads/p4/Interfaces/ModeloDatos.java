package es.uam.eps.ads.p4.Interfaces;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import es.uam.eps.ads.p4.Classes.*;

public interface ModeloDatos {
	
	public void leeFicheroPreferencias(String ruta) throws IOException;
	
	public Map<Long, Double> getPreferenciasUsuario(Long usuario);
	
	public Map<Long, Double> getPreferenciasItem(Long item);
	
	public Set<Long> getUsuariosUnicos();
	
	public Set<Long> getItemsUnicos();
	
	//Otros metodos que los/las estudiantes crean oportuno

	public Boolean isInIdUsuarios(Long user);
}