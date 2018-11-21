package test;
import javax.persistence.*;
@Entity
@Table(name = "Account")
public class Account 
{
	 @Id 
	 @Column(name = "custid")
	 private int custid;
	 @Column(name = "name")
	 private String  name;
	 @Column(name = "bal")
	 private double bal;
	
	 public Account() {
	super();
	}
	public int getCustid() {
		return custid;
	}
	public String getName() {
		return name;
	}
	public double getBal() {
		return bal;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
}
