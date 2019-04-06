package es.uam.eps.ads.p4.Classes;


public class Tupla implements Comparable<Tupla>{
	
	private Long itemId;
	private Double score;

	public Tupla(Long id, Double score){
		this.itemId = id;
		this.score = score;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Tupla [elemento=" + itemId + ", valor =" + score + "]";
	}

	public Double getScore(){
		return score;
	}

	public int compareTo(Tupla t){
		return t.score.compareTo(this.score);
	}

}