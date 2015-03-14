// PackageTest.java
package jmri.util.swing.sdi;

import javax.swing.JButton;
import javax.swing.JFrame;
import jmri.util.JmriJFrame;
import jmri.util.swing.ButtonTestAction;
import junit.framework.Assert;
import junit.framework.Test;
import org.netbeans.junit.NbTestCase;
import org.netbeans.junit.NbTestSuite;

/**
 * Invokes complete set of tests in the jmri.util.swing.sdi tree
 *
 * @author	Bob Jacobsen Copyright 2010
 * @version $Revision$
 */
public class PackageTest extends NbTestCase {

    public void testAction() {
        JmriJFrame f = new JmriJFrame("SDI test");
        JButton b = new JButton(new ButtonTestAction(
                "new frame", new jmri.util.swing.sdi.JmriJFrameInterface()));
        f.add(b);
        f.pack();
        f.setVisible(true);
    }

    public void testFrameCreation() {
        JFrame f = jmri.util.JmriJFrame.getFrame("SDI test");
        Assert.assertTrue("found frame", f != null);
        f.dispose();
    }

    // from here down is testing infrastructure
    public PackageTest(String s) {
        super(s);
    }

    // Main entry point
    static public void main(String[] args) {
        String[] testCaseName = {"-noloading", PackageTest.class.getName()};
        junit.swingui.TestRunner.main(testCaseName);
    }

    // test suite from all defined tests
    public static NbTestSuite suite() {
        NbTestSuite suite = new NbTestSuite(PackageTest.class);

        suite.addTest(SdiJfcUnitTests.suite());

        return suite;
    }

    // The minimal setup for log4J
    protected void setUp() {
        apps.tests.Log4JFixture.setUp();
    }

    protected void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }

}
