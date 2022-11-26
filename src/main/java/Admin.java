import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Admin extends User {
	public final String userName;
	private String fullName;
	private String password;
	private String secretWord;

	static {
		ScheduledExecutorService executorService;
		executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(Admin::checkDelivery, 0, 1, TimeUnit.DAYS);
	}

	public Admin(String userName, String fullName, String password, String secretWord) {
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.secretWord = secretWord;
	}

//	public void changeStudentPlan(Student student, boolean isIron, int numWashes, double costPerWash) {
//		ArrayList<Student.Plan> plans = student.getPlans();
//		Student.Plan activePlan = plans.get(plans.size() - 1);
//		if (activePlan.getWashesGiven() >= numWashes) {
//			student.setPlans(isIron, activePlan.getWashesGiven() + 1, costPerWash);
//		}
//		student.setPlans(isIron, numWashes, costPerWash);
//	}

	public void getWeekLaundry(Student[] students) {
		ArrayList<Student.Wash> washes = new ArrayList();
		for (Student student : students) {
			if (ChronoUnit.DAYS.between(student.getLastWash().getDateGiven(), LocalDate.now()) < 7) {
				washes.add(student.getLastWash());
			}
		}
	}

	public static void checkDelivery() {
				Student[] students = new Student[10];
		for (Student student : students) {
			if (ChronoUnit.DAYS.between(student.getLastWash().getDateGiven(), LocalDate.now()) >= 2) {
				ArrayList<Student.Plan> plans = student.getPlans();
				Student.Plan activePlan = plans.get(plans.size() - 1);
				activePlan.returnWash();
			}
		}
	}

	public double getRevenue(Student[] students) {
		double revenue = 0;
		for (Student student: students) {
			revenue += student.cost();
		}
		return revenue;
	}  
}
