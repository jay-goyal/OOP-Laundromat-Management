public enum WashPlan {
	F4(false, 8, 200,2),
	I4(true, 8, 220,2),
	F8(false, 8, 300,4),
	I8(true, 8, 320,4),
	F10(false, 12, 200,2),
	I10(true, 12, 220,2),
	F15(false, 12, 300,4),
	I15(true, 12, 320,4);

	public final boolean isIron;
	public final int numWashes;
	public final double costPerWash;
	public final double weightPerWash;
	
	private WashPlan(boolean isIron, int numWashes, double costPerWash,double weightPerWash) {
		this.isIron = isIron;
		this.numWashes = numWashes;
		this.costPerWash = costPerWash;
		this.weightPerWash = weightPerWash;
	}
}
