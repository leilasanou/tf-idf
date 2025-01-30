package exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class Bm25Exception extends MoteurRechercheException{
	 public Bm25Exception(String message) {
	        super(message);
	    }

	 public static String requete (String requete) throws IOException {
		
		 if (requete == null || requete.trim().isEmpty()) {
	          
	            return "anarch a avdoc";
	        }
	        return requete; 
	    }
	 
	 
	    @Override
	    public String toString() {
	        return "Bm25Exception: " + getMessage();
	    }
}
