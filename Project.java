package SaleTax.java;
import java.util.*;
public class project
{
	//Inventory starts here
	static String food[]={"chocolate","chocolates","brownie","brownies","snacks","drinks"};
	static String books[]={"book","HP","Kite Runner","cloths"};
	static String medicine[]={"dolo","paracetomol","pills","pill"}; //ending the inventory for predefined exempted items
	static float totalTaxes=0; //declaring and initializing total taxes is equal to zero
	static float totalPrice=0.0f; // declaring and initializing totalPrice is equal to zero
	static float ro=0.05f;
	public static void segregateItems(String item){ // this accepts each line of the user input and determines the quantity,item name and item type
		String[] attr=item.split(" ");
		float quantity=Float.parseFloat(attr[0]);
		float unitPrice=Float.parseFloat(attr[attr.length-1])/quantity;
		//from the given user input we are calculating unitPrice
		String cat="";
		String type="";
		int start;
		if (attr[1].toString().equalsIgnoreCase("imported")) { //determining if the item is imported or not
			start=2; //if the item is imported then the item name index will start from 2
			type="imported";
		}else {
			start=1; // if the item is not imported then the item name index will start from 1
		}
		for (int i=start ; i<attr.length-2; i++) {
			cat+=" " + attr[i];
		}
		String category=determineCategory(cat);
		calculateTaxes(type,category,unitPrice,quantity,cat); 
		//System.out.println( Integer.toString(quantity)+ " " + type + " " + cat + " bought at " + Float.toString(unitPrice) );
	}
	public static void calculateTaxes(String type,String category,float unitPrice,float quantity,String cat) { //calculating individual tax and total tax
		double taxes=0;
		if (type.equalsIgnoreCase("imported")) {
			taxes+=0.05*unitPrice;
		}
		if (category.equalsIgnoreCase("other")) {
			taxes+=0.1*unitPrice;
		}
		
		taxes=Math.ceil(taxes/ro)*ro;
		double price=quantity*(unitPrice+taxes);
		//rounding off the tax to nearest 0.05
		
		totalTaxes+=taxes;
		totalPrice+=price;
		//totalTaxes+=taxes;
		System.out.println(String.format("%.0f",quantity) + " " + type + " " + cat + ": " + String.format("%.2f", price)); //printing the individual item tax price
		//System.out.println("print the result here for " + category);
	}
	// Determining if the item belongs to food or books or medicine category
	public static String determineCategory(String category) {
		String[] cat=category.split(" ");
		for (int i=0; i<cat.length;i++) {
			if (Arrays.asList(food).contains(cat[i])) {
				return "food";
			}else if (Arrays.asList(books).contains(cat[i])) {
				return "books";
			}else if (Arrays.asList(medicine).contains(cat[i])) {
				return "medicine";
			}
		}
		return "other";
	}
	public static void main(String[] args)
	{
		System.out.println("Welcome to the grocery store");
		System.out.println("Please enter items in the format '<quantity> <item> at <price>'");
		System.out.println("Please enter '#' if you're done with inputs"); 
		List input=new ArrayList<String>(); // Initializing a list to hold the user inputs
		Scanner sc=new Scanner(System.in);
		String inp=sc.nextLine();
		input.add(inp);
		while (true) { // infinitely looping to seek input until # encountered 
			inp=sc.nextLine();
			if (inp.equalsIgnoreCase("#")){
				break;
			} // input terminates when the user types # key
			else{
				input.add(inp);
			}
		}
		sc.close(); // closing the scanner to mark the input
		for (int i=0; i<input.size(); i++) {
			segregateItems(input.get(i).toString()); //passing user input line by line for segregating and calculating the salestax
		}
		System.out.println("Sales Taxes: " + String.format("%.2f",totalTaxes)); //printing the sales tax
		System.out.println("Total: " + Float.toString(totalPrice));
	} // printing the totalprice
	
}