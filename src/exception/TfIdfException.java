package exception;

@SuppressWarnings("serial")
public class TfIdfException extends Exception {
	public TfIdfException(String message) {
        super(message); 
    }

    @Override
    public String toString() {
        return "TfIdfException: " + getMessage();
    }
}
