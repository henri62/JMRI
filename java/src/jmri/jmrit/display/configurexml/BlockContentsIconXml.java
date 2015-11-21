package jmri.jmrit.display.configurexml;

import java.util.List;
import jmri.Block;
import jmri.jmrit.catalog.NamedIcon;
import jmri.jmrit.display.BlockContentsIcon;
import jmri.jmrit.display.Editor;
import jmri.jmrit.display.layoutEditor.LayoutEditor;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle configuration for display.BlockContentsIcon objects.
 *
 * @author Bob Jacobsen Copyright: Copyright (c) 2004
 * @version $Revision: 22821 $
 */
public class BlockContentsIconXml extends PositionableLabelXml {

    public BlockContentsIconXml() {
    }

    /**
     * Default implementation for storing the contents of a BlockContentsIcon
     *
     * @param o Object to store, of type BlockContentsIcon
     * @return Element containing the complete info
     */
    public Element store(Object o) {

        BlockContentsIcon p = (BlockContentsIcon) o;

        Element element = new Element("BlockContentsIcon");

        // include attributes
        element.setAttribute("blockcontents", p.getNamedBlock().getName());
        storeCommonAttributes(p, element);
        storeTextInfo(p, element);

        //If the fixed width option is not set and the justification is not left
        //Then we need to replace the x, y values with the original ones.
        if (p.getPopupUtility().getFixedWidth() == 0 && p.getPopupUtility().getJustification() != 0) {
            element.setAttribute("x", "" + p.getOriginalX());
            element.setAttribute("y", "" + p.getOriginalY());
        }
        element.setAttribute("selectable", (p.isSelectable() ? "yes" : "no"));

        element.setAttribute("class", "jmri.jmrit.display.configurexml.BlockContentsIconXml");
        if (p.getDefaultIcon() != null) {
            element.setAttribute("defaulticon", p.getDefaultIcon().getURL());
        }

        // include contents
        java.util.HashMap<String, NamedIcon> map = p.getMap();
        if (map != null) {

            java.util.Iterator<java.util.Map.Entry<String, NamedIcon>> iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                java.util.Map.Entry<String, NamedIcon> mi = iterator.next();
                String key = mi.getKey().toString();
                String value = mi.getValue().getName();

                Element e2 = new Element("blockstate");
                e2.setAttribute("value", key);
                e2.setAttribute("icon", value);
                element.addContent(e2);
            }
        }
        return element;
    }

    /**
     * Load, starting with the BlockContentsIcon element, then all the
     * value-icon pairs
     *
     * @param element Top level Element to unpack.
     * @param o       an Editor as an Object
     */
    public void load(Element element, Object o) {

        Editor ed = null;
        BlockContentsIcon l;
        if (o instanceof LayoutEditor) {
            ed = (LayoutEditor) o;
            l = new jmri.jmrit.display.layoutEditor.BlockContentsIcon("   ", (LayoutEditor) ed);
        } else if (o instanceof jmri.jmrit.display.Editor) {
            ed = (Editor) o;
            l = new BlockContentsIcon("", ed);
        } else {
            log.error("Unrecognizable class - " + o.getClass().getName());
            return;
        }

        String name;
        Attribute attr = element.getAttribute("blockcontents");
        if (attr == null) {
            log.error("incorrect information for a block contents; must use block name");
            ed.loadFailed();
            return;
        } else {
            name = attr.getValue();
        }

        loadTextInfo(l, element);

        Block m = jmri.InstanceManager.blockManagerInstance().getBlock(name);
        if (m != null) {
            l.setBlock(name);
        } else {
            log.error("Block named '" + attr.getValue() + "' not found.");
            ed.loadFailed();
        }

        Attribute a = element.getAttribute("selectable");
        if (a != null && a.getValue().equals("yes")) {
            l.setSelectable(true);
        } else {
            l.setSelectable(false);
        }

        // get the icon pairs
        List<Element> items = element.getChildren("blockstate");
        for (int i = 0; i < items.size(); i++) {
            // get the class, hence the adapter object to do loading
            Element item = items.get(i);
            String iconName = item.getAttribute("icon").getValue();
            NamedIcon icon = NamedIcon.getIconByName(iconName);
            if (icon == null) {
                icon = ed.loadFailed("Memory " + name, iconName);
                if (icon == null) {
                    log.info("Memory \"" + name + "\" icon removed for url= " + iconName);
                }
            }
            if (icon != null) {
                String keyValue = item.getAttribute("value").getValue();
                l.addKeyAndIcon(icon, keyValue);
            }
        }
        ed.putItem(l);
        // load individual item's option settings after editor has set its global settings
        loadCommonAttributes(l, Editor.MEMORIES, element);
        int x = 0;
        int y = 0;
        try {
            x = element.getAttribute("x").getIntValue();
            y = element.getAttribute("y").getIntValue();
        } catch (org.jdom2.DataConversionException e) {
            log.error("failed to convert positional attribute");
        }
        l.setOriginalLocation(x, y);
        l.displayState();
    }

    static Logger log = LoggerFactory.getLogger(BlockContentsIconXml.class.getName());
}
