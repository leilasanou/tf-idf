package classeInitiale;
import java.util.Vector;
import java.io.File; //pour créér un objet de type file contenant le chemin du fichier
import java.util.Scanner;//pour lire dans un fichier
import enums.DataSets;
import exception.CorpusException;
import java.io.*;
import java.util.List;


@SuppressWarnings("serial")


public class Corpus extends Vector<Document> {
    private String titre;
    private DataSets provenance;
    private Object unique; //pouvant etre une instance de bm25 ou tdidf
    @SuppressWarnings("resource")
	public Corpus(String cheminFichier, DataSets provenance, Object unique) throws CorpusException, IOException {
        super();
        this.titre = cheminFichier;
        this.provenance = provenance;
        this.unique = unique;
        File Obj = new File(cheminFichier);  
        Scanner lecteur = new Scanner(Obj);  

        while (lecteur.hasNextLine()) {
            String ligne = lecteur.nextLine().trim();
            String[] caractere = ligne.split("\\|\\|\\|", 2); 
            if (caractere.length < 2) {
                throw new CorpusException("Erreur : " + ligne);  
            }
            String ti = caractere[0];
            Document doc = new Document(ti);
           
            String contenu = caractere[1];

 
           
            for (String i : contenu.split(" ")) {
                doc.putMot(i);   
            }
            this.add(doc);
        }
      lecteur.close();
    }
    

    public Object getUnique() {
        return unique;
    }

    
    public void setUnique(Object unique) {
        this.unique = unique;
    }
public String getTitre() {
	return titre;
}

public void setTitre(String titre) {
	this.titre = titre;
}

public List<Document> getDocuments() {
    return this;
}



public DataSets getProvenance() {
	return provenance;
}

public void setProvenance(DataSets provenance) {
	this.provenance = provenance;
}
//pour ajouter des documents
public void addDocument(Document document) {
    if (document != null) {
        this.add(document);  
    }
}

/*cette methode crée un objet de type string et parcours tous les documents du corpus
 * puis les stockent dans ma variable en séparant le titre et les mots grace à toString préalablement
 * redéfini dans la classe document 
 */


@Override
public String toString() {
    String result = "";


    for (Document doc : this) {
        result += doc.toString() + "\n";
     
    }

    return result.trim();
}
 /*cette fonction (taille) prend en argument un objet et verifie sa classe (TailleDocument ou TailleMot)
  * grace à la methode getclass afin de pouvoir les convertir  en sa classe afin d'appeler la 
  * fonction calculer
  */
public int taille(Object calcul) {
     
    if (calcul.getClass() == TailleDocument.class) {
    	TailleDocument convertisseurD = (TailleDocument) calcul;
        return (convertisseurD.calculer(this)); 
    }
   
    else if (calcul.getClass() == TailleMot.class) {
    	TailleMot convertisseurM = (TailleMot) calcul;
        return (convertisseurM.calculer(this)); 
    }
    
    else {
        return 0; 
    }
}
/*
  methode getFeatures  qui en fonction de la class de m
appelera la bonne fonction processCorpus à appeler
*/ 
public Object getFeatures (Object m) {
	 if (m.getClass() == TfIdf.class) {
		 TfIdf tfidf = (TfIdf) m;
         return tfidf.processCorpus(this);
	   }
	 if (m.getClass() == Bm25.class) {
		 Bm25 bm25 = (Bm25) m;
         return bm25.processCorpus(this);
	   }
	 return null;
}


}

