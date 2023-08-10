package common.exception;;

/**
 * The AimsException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class EcoBikeRentalException extends RuntimeException {

    public EcoBikeRentalException() {

	}

	public EcoBikeRentalException(String message) {
		super(message);
	}
}