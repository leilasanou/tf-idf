package classeInitiale;
import java.util.ArrayList;

@SuppressWarnings("serial") //ignore le message de warning
public class Document extends ArrayList<Mot> {
private String titre;

public Document(String titre) {
	super();
	this.titre = titre;
}

//prend un mot en argument et l'ajoute à la fin de la liste
public void putMot(String m) {
	Mot word = new Mot(m);
	this.add(word);
}

//retourne contenu qui contient la liste de mots 
public String getContenu() {
    String contenu = " ";
    for (Mot mot : this) {
        contenu += mot.getMot() +(" ");
    }
    return contenu.toString();
}


public String getTitre() {
	return titre;
}

public void setTitre(String titre) {
	this.titre = titre;
}

@Override
// redifinition de la methode toString qui renvoit le titre et l'ensemble des mots
public String toString() {
	String resultat = "titre:" + this.titre + "\n ";
	for (Mot i : this) {
		resultat += i + " ";
	}
	return resultat;
}


}

