package classeInitiale;

public class TailleMot {
	
	public TailleMot() {
		super();
	}
//compte les mots qui compose un corpus
	public int  calculer ( Corpus C) {
		int total = 0;
		for (Document document : C.getDocuments()) {
           
            String contenu = document.getContenu();
            String[] mots = contenu.split("\\s+"); 
            total += mots.length;
        }

        return total;
    }
       
}
