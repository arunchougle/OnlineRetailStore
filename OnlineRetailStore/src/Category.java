package entity;

/**
 * 
 * @author arun.chougule
 * Enum to hold product category and respective sales tax %
 * Product type A - 10%
 * Product type B - 20%
 * Product tyo\pe C - 0%
 */
public enum Category {
	
	A(10),B(20),C(0);
	
	private int tax;
	
	Category(int tax)
	{
		this.tax=tax;
	}
	
	public int getTax()
	{
		return tax;
	}
}
