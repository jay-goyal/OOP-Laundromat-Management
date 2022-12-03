//Added a constructor for enum Hostel to note the days corresponding to each hostel

public enum Hostel {
	
	SR("Mon"),
	RM("Tue"),
	BD("Wed"),
	KR("Thu"),
	GN("Fri"),
	SK("Sat"),
	VY("Sun"),
	BG("Mon"),
	MSA("Tue"),
	CVR("Wed"),
	MR("Thu");
	
	private String day;
	private String time;
	
	private Hostel(String day) {
		this.day=day;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day=day;
	}
	
}