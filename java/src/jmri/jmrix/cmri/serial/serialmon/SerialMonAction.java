// SerialMonAction.java

package jmri.jmrix.cmri.serial.serialmon;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import jmri.jmrix.cmri.serial.SerialMessage;

/**
 * Swing action to create and register a
 *       			SerialMonFrame object
 *
 * @author			Bob Jacobsen    Copyright (C) 2001
 * @version			$Revision: 17977 $
 */
public class SerialMonAction extends AbstractAction {

	public SerialMonAction(String s) { super(s);}

    public SerialMonAction() {
        this("CMRInet monitor");
    }

    public void actionPerformed(ActionEvent e) {
		// create a SerialMonFrame
		SerialMonFrame f = new SerialMonFrame() {

            public void SerialMonFrame(SerialMessage m) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
		try {
			f.initComponents();
			}
		catch (Exception ex) {
			log.warn("SerialMonAction starting SerialMonFrame: Exception: "+ex.toString());
			}
		f.setVisible(true);
	}

	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SerialMonAction.class.getName());

}


/* @(#)SerialMonAction.java */
