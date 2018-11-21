package test;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import org.hibernate.Transaction;
import org.hibernate.Query;
public class Acc_main_test 
{


	public static void main(String[] args) 
	{


		
		int ch=0;
		SessionFactory sf=null; 
		Session sess=null;
		Query query=null;
		String str=null;
		sf = ((Configuration) new Configuration().configure()).addAnnotatedClass(Account.class).buildSessionFactory();
		
		//new AnnotationConfiguration().configure.
		System.out.println("Connection established....");
		Scanner sc=new Scanner(System.in);
		do
		{
			sess=sf.openSession();
			Transaction tr=sess.beginTransaction();
			System.out.println("Transaction Started......");
			System.out.println("Enter your Choice.....");
			System.out.println("1-Save a Record.\n2 -Delete a Record.\n3-Show a record.\n4-update a record.\n5-Show all Records.");
			ch=sc.nextInt();
			Account i=new Account();//trasient State
			switch(ch)
			{
			case 1:
				//saving a record
				System.out.println("enter customerID");
				int custID=sc.nextInt();
				System.out.println("enter customer name");
				String name=sc.next();
				System.out.println("Enter Balance");
				double bal=sc.nextDouble();
				Account i1=new Account();
				i1.setCustid(custID);
				i1.setName(name);
				i1.setBal(bal);
			
				sess.save(i1);
				
				
				
				sess.flush();
				tr.commit();
				sess.close();
				
			//	System.out.println("Record save Successfully....");
				break;
			case 2:
				//delete 
				System.out.println("enter customerID to delete");
				int custID1=sc.nextInt();
				i.setCustid(custID1);
				sess.delete(i);
				sess.flush();
				tr.commit();
				sess.close();
				System.out.println("Record deleted Successfully....");
				break;

			case 3:
				//Search a record
				System.out.println("enter "
						+ "customerID to view record ");
				int ID1=sc.nextInt();
				Account usr1=(Account) sess.get(Account.class,new Integer(ID1));
				if(usr1==null)
				{
					System.out.println("record not found in the table......");
				}
				else
				{
					System.out.println("record found ,its details are :-");
					System.out.println("\tCustomer ID:" + usr1.getCustid());
					System.out.println("\t Customer name :" +usr1.getName());
					System.out.println("\t Balance :"+usr1.getBal());
					System.out.println("\n");

				}

				sess.flush();
				tr.commit();
				sess.close();
				System.out.println("Record save Successfully....");
				break;
			case 4:
				//saving a record
				System.out.println("enter details to update");
				System.out.println("Enter Customer ID :");

				int id=sc.nextInt();//101
				Account user2=(Account)sess.get(Account.class, new Integer(id));
				System.out.println("enter customer name :");
				String name1=sc.next();

				System.out.println("Enter Balance");
				double bal1=sc.nextDouble();
				user2.setName(name1);
				user2.setBal(bal1);

				sess.update(user2);
				sess.flush();
				tr.commit();
				sess.close();

				break;


			case 5:
				//Display all records
				query=sess.createQuery("from Account");//form "className"
				List<Account> list =query.list();
				for(Account a:list)
				{
					System.out.println("\t---------------------------------------------------------");
					System.out.println("\t Customer ID:" +a.getCustid());
					System.out.println("\t Customer Name :"+a.getName());
					System.out.println("\t Balance :"+a.getBal());
					System.out.println("\t----------------------------------------------------------");
					System.out.println("\n");
				}
				sess.close();
				break;
			default:
				System.out.println("Invalid option....");
			}
			System.out.println("Do you want to continue Transaction?\n(yes/no)");
			str=sc.next();
		}while(str.equalsIgnoreCase("yes"));
	}
}

