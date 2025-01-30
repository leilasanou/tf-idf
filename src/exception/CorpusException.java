package exception;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
	public class CorpusException extends MoteurRechercheException {  
	    public CorpusException(String message) {
	        super(message);  
	    }

	    public static void creation(String fileA) throws IOException {
	        File file = new File(fileA);
	        if (!file.exists()) {
	            if (file.createNewFile()) {
	                System.out.println("Fichier créé avec succès : " + fileA);
	            } else {
	                throw new IOException("Impossible  : " + fileA);
	            }
	        } else {
	            System.out.println("fichier : " + fileA);
	        }
	    }

	    @Override
	    public String toString() {
	        return "CorpusException: " + getMessage();
	    }
	}


