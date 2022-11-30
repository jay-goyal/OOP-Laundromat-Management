//Added a constructor for enum Hostel to note the days corresponding to each hostel

public enum Hostel {
	
	SR("Monday"),
	RM("Tuesday"),
	BD("Wednesday"),
	KR("Thursday"),
	GN("Friday"),
	SK("Saturday"),
	VY("Sunday"),
	BG("Monday"),
	MSA("Tuesday"),
	CVR("Wednesday"),
	MR("Thursday");
	
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
