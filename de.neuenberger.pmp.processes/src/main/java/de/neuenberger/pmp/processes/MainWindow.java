/**
 * 
 */
package de.neuenberger.pmp.processes;

import generated.CplxProcessGroups;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.xml.bind.JAXB;

import de.neuenberger.pmp.processes.model.KnowledgeAreaFactory;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class MainWindow extends JFrame {

	public static void main() throws IOException {
		final InputStream stream = MainWindow.class.getResource(
				"pmp_processes.xml").openStream();
		final CplxProcessGroups cplxProcessGroups = JAXB.unmarshal(stream,
				CplxProcessGroups.class);
		new KnowledgeAreaFactory().process(cplxProcessGroups);

	}
}
