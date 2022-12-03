import java.util.ArrayList;
public class Plan {
	private final WashPlan washPlan;
	private double expense;
	private int numWashGiven;
	private double extraCharge;
	private ArrayList<Wash> washes=new ArrayList<>();
	
	Plan(WashPlan washPlan,double Expense,int numWashGiven){
		this.washPlan=washPlan;
		this.expense=expense;
		this.numWashGiven=numWashGiven;
		extraCharge=0;
	}
	
	public WashPlan getWashPlan() {
		return washPlan;
	}
	
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense=expense;
	}
	
	public int getNumWashGiven() {
		return numWashGiven;
	}
	public void incrementNumWashGiven() {
		numWashGiven++;
	}
	
	public double getExtraCharge() {
		return extraCharge;
	}
	public void incrementExtraCharge(double extraCharge) {
		this.extraCharge+=extraCharge;
	}
	
	public void addWash(Wash wash) {
		washes.add(wash);
	}
	public ArrayList<Wash> getWashList() {
		return washes;
	}
	
}

class Wash{
	private String DateGiven;
	private String status;
	double cost;
	
	Wash(String DateGiven,String status,double cost){
		this.DateGiven=DateGiven;
		this.status=status;
		this.cost=cost;
	}
	
	
	public String getDateGiven() {
		return DateGiven;
	}
	public void setDateGiven(String DateGiven) {
		this.DateGiven=DateGiven;
	}
	
	public String getstatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status=status;
	}	
}
