import java.util.HashMap;

public class DeliverySchedule{
    public static HashMap<Hostel, String> hostelDay = new HashMap<>();
    public static HashMap<Hostel, String> hostelTime = new HashMap<>();

    static {
        hostelDay.put(Hostel.SR, "Wed");
        hostelDay.put(Hostel.RM, "Thu");
        hostelDay.put(Hostel.BD, "Fri");
        hostelDay.put(Hostel.KR, "Sat");
        hostelDay.put(Hostel.GN, "Sun");
        hostelDay.put(Hostel.SK, "Mon");
        hostelDay.put(Hostel.VY, "Tue");
        hostelDay.put(Hostel.BG, "Wed");
        hostelDay.put(Hostel.MSA, "Thu");
        hostelDay.put(Hostel.CVR, "Fri");
        hostelDay.put(Hostel.MR, "Thu");
        hostelTime.put(Hostel.SR, "1800");
        hostelTime.put(Hostel.RM, "1800");
        hostelTime.put(Hostel.BD, "1800");
        hostelTime.put(Hostel.KR, "1800");
        hostelTime.put(Hostel.GN, "1800");
        hostelTime.put(Hostel.SK, "1800");
        hostelTime.put(Hostel.VY, "1800");
        hostelTime.put(Hostel.BG, "1800");
        hostelTime.put(Hostel.MSA, "1800");
        hostelTime.put(Hostel.CVR, "1800");
        hostelTime.put(Hostel.MR, "1800");
    }
}
