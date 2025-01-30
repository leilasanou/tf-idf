package enums;

public enum DataSets {
WIKIPEDIA ("Wikipedia"),
OUVRAGES ("Ouvrage");
	
private final String nom; //car une fois le nom initialiser, on ne va plus le modifier

private DataSets(String nom) {
	this.nom = nom;
}

public String getNom() {
	return nom;
} 
	
	
	
}
