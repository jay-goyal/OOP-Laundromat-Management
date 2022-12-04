public enum WashPlan {
	A8(false, 8, 200,2),
	B8(true, 8, 220,2),
	C8(false, 8, 300,4),
	D8(true, 8, 320,4),
	A12(false, 12, 200,2),
	B12(true, 12, 220,2),
	C12(false, 12, 300,4),
	D12(true, 12, 320,4);

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
