package validator;

import java.util.HashMap;
import validator.ValidatorType;
public class BaseValidator{
	
	private String str;
	private HashMap<ValidatorType, String> errorMsg = new HashMap<ValidatorType, String>();
	
	public BaseValidator() {
		// Initialize error messages
		errorMsg.put(ValidatorType.REQUIRED, "This field must be filled in");
		errorMsg.put(ValidatorType.UNIQUE, "This value is already in use");
		errorMsg.put(ValidatorType.NUMBER, "Number is required");
		errorMsg.put(ValidatorType.POSITIVE_NUMBER, "Positive number is required");
		errorMsg.put(ValidatorType.TEXT, "Require only text");
	}

	public void validateField(ValidatorType type) throws Exception {
		switch(type) {
		case REQUIRED:
			if (str == "")
				throw new Exception(handleReturnMessage(errorMsg.get(type)));
			break;
		case NUMBER:
			if (!str.matches("^[0-9]*$"))
				throw new Exception(handleReturnMessage(errorMsg.get(type)));
			break;
		case TEXT:
			if (!str.matches("^[a-zA-Z]*$"))
				throw new Exception(handleReturnMessage(errorMsg.get(type)));
			break;
		default:
			break;
		}
	}

	private String handleReturnMessage(String errorMsg) {
		// TODO Auto-generated method stub
		return errorMsg;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
