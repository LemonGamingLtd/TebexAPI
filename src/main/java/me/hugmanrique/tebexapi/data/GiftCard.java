package me.hugmanrique.tebexapi.data;

public class GiftCard {

	private final int id;
	private final String code;
	private final double starting;
	private final double remaining;

	public GiftCard(int id, String code, double starting, double remaining) {
		this.id = id;
		this.code = code;
		this.starting = starting;
		this.remaining = remaining;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public double getStarting() {
		return starting;
	}

	public double getRemaining() {
		return remaining;
	}

}