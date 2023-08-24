package entity.card;

public class Card {
	private String cardHolder;
	private String cardRent;
	private String securityCode;
	private String expirationDay;
	private Boolean isBeingUsed = false;
	public Card(String cardHolder, String cardRent, String securityCode, String expirationDay) {
		this.cardHolder = cardHolder;
		this.cardRent = cardRent;
		this.securityCode = securityCode;
		this.expirationDay = expirationDay;
	}
	public String getCardHolder() {
		return cardHolder;
	}

	public String getcardRent() {
		return cardRent;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public String getExpirationDay() {
		return expirationDay;
	}
}
