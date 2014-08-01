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
import de.neuenberger.pmp.processes.model.QuestionStatistics;
import de.neuenberger.pmp.processes.ui.Controller;
import de.neuenberger.pmp.processes.ui.QuestionComposite;
import de.neuenberger.pmp.processes.ui.QuestionController;
import de.neuenberger.pmp.processes.ui.QuestionStatisticsComposite;
import de.neuenberger.pmp.processes.ui.QuestionStatisticsController;
import de.neuenberger.pmp.processes.ui.SelectQuestionContainerComposite;
import de.neuenberger.pmp.processes.ui.SelectQuestionContainerController;

/**
 * @author Michael Kirchmann
 * 
 */
public class MainWindow extends JFrame {

	MainWindow(final Controller<?, ?>... controllers) {
		setTitle("PMP Quiz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		final JTabbedPane tabPane = new JTabbedPane();
		for (final Controller<?, ?> controller : controllers) {
			tabPane.addTab(controller.toString(), controller.getComponent());
		}
		this.add(tabPane, BorderLayout.CENTER);
	}

	public static void main(final String[] argv) throws IOException {
		final InputStream stream = MainWindow.class.getResource(
				"pmp_processes.xml").openStream();
		final CplxProcessGroups cplxProcessGroups = JAXB.unmarshal(stream,
				CplxProcessGroups.class);
		final QuestionStatistics questionStatistics = new QuestionStatistics();
		new KnowledgeAreaFactory().process(cplxProcessGroups);
		final QuestionComposite questionComposite = new QuestionComposite();
		final OverallQuestionDrawer questionDrawer = new OverallQuestionDrawer(
				cplxProcessGroups);
		final QuestionController controller = new QuestionController(
				questionDrawer, questionStatistics, questionComposite);
		final SelectQuestionContainerController controller2 = new SelectQuestionContainerController(
				new SelectQuestionContainerComposite(), questionDrawer);

		final QuestionStatisticsController controller3 = new QuestionStatisticsController(
				new QuestionStatisticsComposite(), questionStatistics);

		final MainWindow mainWindow = new MainWindow(controller, controller2,
				controller3);
		mainWindow.setSize(400, 300);
		mainWindow.setVisible(true);
	}
}
