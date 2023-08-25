package entity.card;

public class Card {
	private String cardHolder;
	private String cardNumber;
	private String securityCode;
	private String expirationDay;
	private Boolean isBeingUsed = false;
	public Card(String cardHolder, String cardNumber, String securityCode, String expirationDay) {
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.expirationDay = expirationDay;
	}
	public String getCardHolder() {
		return cardHolder;
	}

	public String getcardNumber() {
		return cardNumber;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public String getExpirationDay() {
		return expirationDay;
	}
}
