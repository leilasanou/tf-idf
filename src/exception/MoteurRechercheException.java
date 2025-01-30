package exception;

@SuppressWarnings("serial")
public class MoteurRechercheException  extends Exception { 
	
		    public MoteurRechercheException (String message) {
		        super(message); 
		    }
		        public String toString() {
		            return "MoteurRechercheException: " + getMessage();
		        }
		    }

		  

