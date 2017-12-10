package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

	private int iYearsToWork;
	private double dAnnualReturnWorking;
	private int iYearsRetired;
	private double dAnnualReturnRetired;
	private double dRequiredIncome;
	private double dMonthlySSI;
	private double pv;
	private double pmt;
	
	private double dMonthsToWork;
	private double rAnnualReturnWorking;
	private double dMonthsRetired;
	private double rAnnaulReturnRetired;
	

	
	//TODO: Build the contructor, getters and setters for the attributes above.
	
	public Retirement (int iYearsToWork, double dAnnualReturnWorking, 
					   int iYearsRetired,double dAnnualReturnRetired,
					   double dRequiredIncome, double dMonthlySSI){
		this.iYearsToWork = iYearsToWork;
		this.dAnnualReturnWorking = dAnnualReturnWorking;
		this.iYearsRetired = iYearsRetired;
		this.dAnnualReturnRetired = dAnnualReturnRetired;
		this.dRequiredIncome = dRequiredIncome;
		this.dMonthlySSI = dMonthlySSI;
		
		this.dMonthsToWork = iYearsToWork * 12;
		this.rAnnualReturnWorking = dAnnualReturnWorking / 12;
		this.dMonthsRetired = iYearsRetired * 12;
		this.rAnnaulReturnRetired = dAnnualReturnRetired / 12;
	}
	
	public double AmountToSave()
	{
		//TODO: Determine the amount to save each month based on TotalAmountSaved, YearsToWork
		//		and Annual return while working
		pmt = FinanceLib.pmt(rAnnualReturnWorking, dMonthsToWork, 0, pv, false);
		return pmt;
	}
	
	public double TotalAmountSaved()
	{
		//	TODO: Determine amount to be saved based on Monthly SSI, Required Income, Annual return during retirement
		//		and number of years retired.
		//
		pv = FinanceLib.pv(rAnnaulReturnRetired, dMonthsRetired, dRequiredIncome - dMonthlySSI, 0, false);
		return pv;
	}
	
	public int getiYearsToWork() {
		return iYearsToWork;
	}

	public double getdAnnualReturnWorking() {
		return dAnnualReturnWorking;
	}

	public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
		this.dAnnualReturnWorking = dAnnualReturnWorking;
	}

	public void setiYearsToWork(int iYearsToWork) {
		this.iYearsToWork = iYearsToWork;
	}

	public int getiYearsRetired() {
		return iYearsRetired;
	}

	public void setiYearsRetired(int iYearsRetired) {
		this.iYearsRetired = iYearsRetired;
	}

	public double getdAnnualReturnRetired() {
		return dAnnualReturnRetired;
	}

	public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
		this.dAnnualReturnRetired = dAnnualReturnRetired;
	}

	public double getdRequiredIncome() {
		return dRequiredIncome;
	}

	public void setdRequiredIncome(double dRequiredIncome) {
		this.dRequiredIncome = dRequiredIncome;
	}

	public double getdMonthlySSI() {
		return dMonthlySSI;
	}

	public void setdMonthlySSI(double dMonthlySSI) {
		this.dMonthlySSI = dMonthlySSI;
	}
}
