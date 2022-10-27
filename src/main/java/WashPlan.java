public enum WashPlan {
	F4(false, 4, 250.25),
	I4(true, 4, 270.25),
	F8(false, 8, 230.25),
	I8(true, 8, 250.25),
	F10(false, 10, 220.10),
	I10(true, 10, 240.10),
	F15(false, 4, 210.0),
	I15(true, 4, 230.0);

	public final boolean isIron;
	public final int numWashes;
	public final double costPerWash;
	
	private WashPlan(boolean isIron, int numWashes, double costPerWash) {
		this.isIron = isIron;
		this.numWashes = numWashes;
		this.costPerWash = costPerWash;
	}
}
