import java.io.Serializable;
import java.util.ArrayList;
public class Plan implements Serializable {
	private final WashPlan washPlan;
	private double expense;
	private int numWashGiven;
	private double extraCharge;
	private ArrayList<Wash> washes=new ArrayList<>();
	
	Plan(WashPlan washPlan,double expense,int numWashGiven){
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

	public void addWash(ArrayList<Wash> wash) {
		for (Wash w : wash) {
			washes.add(w);
		}
	}
	public ArrayList<Wash> getWashList() {
		return washes;
	}
	public void returnWash() {
		washes.get(washes.size() - 1).setStatus("Delivered");
	}

	public void recvWash() {
		washes.get(washes.size() - 1).setStatus("Recvd");
	}
}

class Wash implements Serializable{
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

	@Override
	public String toString() {
		return "Given On: " + DateGiven + ", Current Status: " + status + ", Costs: " + cost;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}	
}
