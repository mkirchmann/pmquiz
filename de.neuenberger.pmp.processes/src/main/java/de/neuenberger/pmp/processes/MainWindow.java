/**
 * 
 */
package de.neuenberger.pmp.processes;

import generated.CplxProcessGroups;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.xml.bind.JAXB;

import de.neuenberger.pmp.processes.generator.OverallQuestionDrawer;
import de.neuenberger.pmp.processes.model.KnowledgeAreaFactory;
import de.neuenberger.pmp.processes.ui.QuestionComposite;
import de.neuenberger.pmp.processes.ui.QuestionController;
import de.neuenberger.pmp.processes.ui.SelectQuestionContainerComposite;
import de.neuenberger.pmp.processes.ui.SelectQuestionContainerController;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class MainWindow extends JFrame {

	MainWindow(final QuestionController controller1, final SelectQuestionContainerController controller2) {
		setTitle("PMP Quiz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab("Quiz", controller1.getQuestionComposite());
		tabPane.addTab("Question Container Selection", controller2.getComponent());
		
		this.add(tabPane, BorderLayout.CENTER);
	}

	public static void main(final String[] argv) throws IOException {
		final InputStream stream = MainWindow.class.getResource("pmp_processes.xml").openStream();
		final CplxProcessGroups cplxProcessGroups = JAXB.unmarshal(stream, CplxProcessGroups.class);
		new KnowledgeAreaFactory().process(cplxProcessGroups);
		final QuestionComposite questionComposite = new QuestionComposite();
		OverallQuestionDrawer questionDrawer = new OverallQuestionDrawer(cplxProcessGroups);
		final QuestionController controller = new QuestionController(questionDrawer, questionComposite);
		SelectQuestionContainerController controller2 = new SelectQuestionContainerController(new SelectQuestionContainerComposite(), questionDrawer);
		
		MainWindow mainWindow = new MainWindow(controller, controller2);
		mainWindow.setSize(400, 300);
		mainWindow.setVisible(true);
	}
}
