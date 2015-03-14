// LI100FTest.java
package jmri.jmrix.lenz.li100f;

import junit.framework.Test;
import org.netbeans.junit.NbTestCase;
import org.netbeans.junit.NbTestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for the jmri.jmrix.lenz.li100f package
 *
 * @author Paul Bender
 * @version $Revision$
 */
public class LI100FTest extends NbTestCase {

    // from here down is testing infrastructure
    public LI100FTest(String s) {
        super(s);
    }

    // Main entry point
    static public void main(String[] args) {
        String[] testCaseName = {LI100FTest.class.getName()};
        junit.swingui.TestRunner.main(testCaseName);
    }

    // test suite from all defined tests
    public static NbTestSuite suite() {
        NbTestSuite suite = new NbTestSuite("jmri.jmrix.lenz.li100f.LI100FTest");  // no tests in this class itself
        suite.addTest(new NbTestSuite(LI100AdapterTest.class));
        return suite;
    }

    static Logger log = LoggerFactory.getLogger(LI100FTest.class.getName());

}
