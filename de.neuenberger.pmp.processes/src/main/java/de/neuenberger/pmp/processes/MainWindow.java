/**
 * 
 */
package de.neuenberger.pmp.processes;

import generated.CplxProcessGroups;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.xml.bind.JAXB;

import de.neuenberger.pmp.processes.generator.OverallQuestionGenerator;
import de.neuenberger.pmp.processes.model.KnowledgeAreaFactory;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class MainWindow extends JFrame {

	MainWindow(final QuestionController controller) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(controller.getQuestionComposite(), BorderLayout.CENTER);
	}

	public static void main(final String[] argv) throws IOException {
		final InputStream stream = MainWindow.class.getResource("pmp_processes.xml").openStream();
		final CplxProcessGroups cplxProcessGroups = JAXB.unmarshal(stream, CplxProcessGroups.class);
		new KnowledgeAreaFactory().process(cplxProcessGroups);
		final QuestionComposite questionComposite = new QuestionComposite();
		final QuestionController controller = new QuestionController(null, new OverallQuestionGenerator(cplxProcessGroups),
				questionComposite);
		MainWindow mainWindow = new MainWindow(controller);
		mainWindow.setSize(300, 200);
		mainWindow.setVisible(true);
	}
}
