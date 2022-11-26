package OOP_project;
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
	
	public final String day;
	
	private Hostel(String day) {
		this.day=day;
	}
}
