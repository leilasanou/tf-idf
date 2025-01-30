package classeInitiale;
import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Scanner;

import exception. MoteurRechercheException;

//import java.util.Objects;
public class Vocabulary {
	HashMap<Mot,Integer> vocabulaire;
	HashSet<Mot> stopList;
	private static Vocabulary unique; //création de l'unique objet
	 private int id; //necessaire pour pouvoir créer des identifiants uniques pour chaque mot (integer)de hashmap
	
	 
	 private Vocabulary() {
	        this.vocabulaire = new HashMap<>(); 
	        stopList = new HashSet<>();
	        this.id = 0;
	    }

	public Vocabulary(HashMap<Mot, Integer> vocabulaire, HashSet<Mot> stopList) {
		super();
		this.vocabulaire = vocabulaire;
		this.stopList = stopList;
	}


	public HashMap<Mot, Integer> getVocabulaire() {
		return vocabulaire;
	}



	public void setVocabulaire(HashMap<Mot, Integer> vocabulaire) {
		this.vocabulaire = vocabulaire;
	}



	public HashSet<Mot> getStopList() {
		return stopList;
	}



	public void setStopList(HashSet<Mot> stopList) {
		this.stopList = stopList;
	}
/*cette fonction permet d'ajouter un mot s'il n'est pas là dans la hashmap de vocabulary et de créer un
 * identifiant unique en incrémentant
 * utile pour la methode vocabulary de Tfldf
 */
	
 
 public void addVocabulary(Mot mot) {
     if (mot != null && !this.vocabulaire.containsKey(mot)) {  
         this.vocabulaire.put(mot, id); 
         id++;  
     }
 }

	public static Vocabulary getUnique() {
		if (unique == null) {
			unique = new Vocabulary();
		}
		return unique;
	}
//taille du vocabulaire
 public int getSize() {
	 return vocabulaire.size();
 }
 
 
 public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}



	//methode retournant l'identifiant (if) d'un mot s'il est present dans le vocabulaire ou si elle est vide

	public Integer getMotId(String mot) {
		if (mot == null  ) {
		return null;
		}
		Mot m = new Mot (mot);
		 return vocabulaire.get(m);
}
	

	public static void setUnique(Vocabulary unique) {
		Vocabulary.unique = unique;
	}
//retournant vrai si un mot se trouve dans la hashset
	
	public boolean stopWord(String word) {
		Mot i = new Mot(word);
		if (stopList.contains(i)) {
        return true;
    }
		return false;
	}
	 public HashMap<Mot, Integer> getVocabulary() {
	        return this.vocabulaire;
	    }

//method prenant un fichier et les les ajoutant dans la hashSet des stops words
	 
	 public void ajoutStopWords(String stop)  throws  MoteurRechercheException, IOException {
		 File Obj = new File(stop);  
	        Scanner lecteur = new Scanner(Obj);
	        
	        while (lecteur.hasNextLine()) {
	            String ligne = lecteur.nextLine().trim();
	            Mot i = new Mot(ligne);
	            stopList.add(i); 
	        }

	        lecteur.close();
	    }
	
	}

