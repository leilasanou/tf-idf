package classeInitiale;

import java.util.Objects;

public class Mot {
private String mot;

public Mot(String mot) {
	super();
	this.mot = mot;
}

public String getMot() {
	return mot;
}

public void setMot(String mot) {
	this.mot = mot;
}

@Override
public String toString() {
	return   this.mot + " ";
}

@Override
public int hashCode() {
	return Objects.hash(mot);
}
//redefinition de la methode equal car j'arrivais à comparer deux mots
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Mot other = (Mot) obj;
	return Objects.equals(mot, other.mot);
}

}
