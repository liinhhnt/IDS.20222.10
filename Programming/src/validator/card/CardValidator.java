package validator.card;

import entity.card.Card;
import validator.BaseValidator;
import validator.ValidatorType;

public class CardValidator extends BaseValidator{
	public void validateString(String name) throws Exception {
		super.setStr(name);
		super.validateField(ValidatorType.REQUIRED);
		super.validateField(ValidatorType.TEXT);
	}

	public void validateStringNumber(String stringNumber) throws Exception {
		super.setStr(stringNumber);
		super.validateField(ValidatorType.REQUIRED);
		super.validateField(ValidatorType.NUMBER);
	}

	public void validateCard(Card card) throws Exception {
		validateString(card.getCardHolder());
        validateStringNumber(card.getExpirationDay());
        validateStringNumber(card.getcardNumber());
        validateStringNumber(card.getSecurityCode());
	}
}
