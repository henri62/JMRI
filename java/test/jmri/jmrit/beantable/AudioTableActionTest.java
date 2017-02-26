package jmri.jmrit.beantable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class AudioTableActionTest extends AbstractTableActionBase {

    @Test
    public void testCTor() {
        Assert.assertNotNull("exists",a);
    }

    @Override
    public String getTableFrameName(){
        return Bundle.getMessage("TitleAudioTable");
    }

    @Test
    @Override
    @Ignore("Audio table will only be init if an audio manageris available")
    public void testGetTableDataModel(){
    }

    /**
     * Check the return value of getPanel.  The tested class provides a panel.
     */
    @Override
    @Test
    public void testGetPanel(){
         Assert.assertNotNull("AudioTableAction getPanel returns null",a.getPanel());
    }

    @Override
    @Test
    public void testGetClassDescription(){
         Assert.assertEquals("Turnout Table Action class description","Audio Table",a.getClassDescription());
    }

    /**
     * Check the return value of includeAddButton.  The table generated by 
     * this action includes an Add Button.
     */
    @Override
    @Test
    public void testIncludeAddButton(){
         Assert.assertTrue("Default include add button",a.includeAddButton());
    }

    // The minimal setup for log4J
    @Before
    @Override
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
        jmri.util.JUnitUtil.resetInstanceManager();
        jmri.util.JUnitUtil.initDefaultUserMessagePreferences();
        a = new AudioTableAction();
    }

    @After
    @Override
    public void tearDown() {
        jmri.util.JUnitUtil.resetInstanceManager();
        apps.tests.Log4JFixture.tearDown();
        a = null;
    }

    private final static Logger log = LoggerFactory.getLogger(AudioTableActionTest.class.getName());

}
