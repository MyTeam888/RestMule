package org.epsilonlabs.rescli.github.test.mde;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//@Ignore("to be executed manually")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MDEAnalysisUsingIterablesTest {

	@Ignore("to be executed manually")
	@Test
	public void testGMF() {

//		MDEAnalysisUsingIterables ghSearchRunner = new MDEAnalysisUsingIterables(MDE.GMF);
//		ghSearchRunner.runSearch();
	}

	@Ignore("to be executed manually")
	@Test
	public void testSirius() {

//		MDEAnalysisUsingIterables ghSearchRunner = new MDEAnalysisUsingIterables(MDE.Sirius);
//		ghSearchRunner.runSearch();
	}

	@Ignore("to be executed manually")
	@Test
	public void testEugenia() {

		MDEAnalysisUsingIterables ghSearchRunner = new MDEAnalysisUsingIterables(MDE.Eugenia);
		ghSearchRunner.runSearch();
	}

}
