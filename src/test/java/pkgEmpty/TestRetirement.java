package pkgEmpty;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import pkgCore.Retirement;

public class TestRetirement {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRetirement() {
		int iYearsToWork = 40;
		double dAnnualReturnWorking = 0.07;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 0.02;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;
		
		Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired,
				dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);
		
		DecimalFormat df = new DecimalFormat("###,##0.00");
		
		assertEquals("1,454,485.55", df.format(-retirement.TotalAmountSaved()));
		assertEquals("554.13", df.format(retirement.AmountToSave()));
	}

}
