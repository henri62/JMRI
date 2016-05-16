package jmri.jmrix.cmri.serial.sim.configurexml;

import jmri.InstanceManager;
import jmri.jmrix.cmri.serial.sim.ConnectionConfig;
import jmri.jmrix.cmri.serial.sim.SimDriverAdapter;

/**
 * Handle XML persistance of layout connections by persisting
 * the SimDriverAdapter (and connections). Note this is
 * named as the XML version of a ConnectionConfig object,
 * but it's actually persisting the SimDriverAdapter.
 * <P>
 * This class is invoked from jmrix.JmrixConfigPaneXml on write,
 * as that class is the one actually registered. Reads are brought
 * here directly via the class attribute in the XML.
 * <p>
 * This inherits from the cmri.serial.serialdriver version, so
 * it can reuse (and benefit from changes to) that code.
 *
 * @author Bob Jacobsen Copyright: Copyright (c) 2003, 2008
 * @version $Revision: 17977 $
 */
@edu.umd.cs.findbugs.annotations.SuppressWarnings(value="NM_SAME_SIMPLE_NAME_AS_SUPERCLASS") // OK by convention
public class ConnectionConfigXml extends jmri.jmrix.cmri.serial.serialdriver.configurexml.ConnectionConfigXml {

    public ConnectionConfigXml() {
        super();
    }
	
    protected void getInstance() {
        adapter = SimDriverAdapter.instance();
    }

    protected void register() {
        InstanceManager.configureManagerInstance().registerPref(new ConnectionConfig(adapter));
    }
     

    // initialize logging
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConnectionConfigXml.class.getName());

}