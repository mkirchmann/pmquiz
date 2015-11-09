/**
 * 
 */
package de.neuenberger.pmp.processes;

import generated.CplxAddition;
import generated.CplxDefinition;
import generated.CplxGroup;
import generated.CplxKnowledgeArea;
import generated.CplxProcessGroups;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

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
		String filename;
		boolean process;
		if (argv.length==1 && "hsk".equalsIgnoreCase(argv[0])) {
			filename = "hsk_questions.xml";
			process=false;
		} else {
			filename = "pmp_processes.xml";
			process=true;
		}
		final InputStream stream = MainWindow.class.getResource(
				filename).openStream();
		final CplxProcessGroups cplxProcessGroups = JAXB.unmarshal(stream,
				CplxProcessGroups.class);
		final QuestionStatistics questionStatistics = new QuestionStatistics();
		if (process) {
			new KnowledgeAreaFactory().process(cplxProcessGroups);
		}
		// enrichWithHSK(cplxProcessGroups);
		
		
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

	private static void enrichWithHSK(CplxProcessGroups cplxProcessGroups) throws IOException {
		Charset forName = Charset.forName("UTF-8");
		for (int i=1; i<=6; i++) {
			String hsk="HSK"+i;
			String filename = "HSK Official With Definitions 2012 L"+i+" freqorder.csv";
			try (
			InputStream resourceAsStream = MainWindow.class.getResourceAsStream(filename);
			Reader isr = new InputStreamReader(resourceAsStream, forName);
			BufferedReader bufferedReader = new BufferedReader(isr);)
			{
				CplxKnowledgeArea knowledgeArea = createKnowledgeArea(hsk);
				cplxProcessGroups.getKnowledgeArea().add(knowledgeArea);
				CplxGroup cplxGroup1 = createGroup(hsk, knowledgeArea, "1");
				CplxGroup cplxGroup2 = createGroup(hsk, knowledgeArea, "2");
				CplxGroup cplxGroup3 = createGroup(hsk, knowledgeArea, "3");
				
				String readLine=null;
				do {
					readLine = bufferedReader.readLine();
					if (readLine!=null && !readLine.isEmpty()) {
						HskLine hskLine = new HskLine(readLine);
						
						CplxDefinition definition1 = hskLine.createDefinition1();
						cplxGroup1.getDefinition().add(definition1);
						CplxDefinition definition2 = hskLine.createDefinition2();
						cplxGroup2.getDefinition().add(definition2);
						CplxDefinition definition3 = hskLine.createDefinition3();
						cplxGroup3.getDefinition().add(definition3);
					}
				} while (readLine!=null);
				
			} finally {
			}
		}
		JAXB.marshal(cplxProcessGroups, new File("hsk_questions.xml"));
	}

	private static CplxGroup createGroup(String hsk, CplxKnowledgeArea knowledgeArea, String id) {
		CplxGroup cplxGroup1 = new CplxGroup();
		cplxGroup1.setName(hsk+" Questions Group "+id);
		CplxAddition cplxAddition = knowledgeArea.getAddition();
		if (cplxAddition==null) {
			cplxAddition = new CplxAddition();
			knowledgeArea.setAddition(cplxAddition);
		}
		cplxAddition.getGroup().add(cplxGroup1);
		return cplxGroup1;
	}

	private static CplxKnowledgeArea createKnowledgeArea(String hsk) {
		CplxKnowledgeArea knowledgeArea1=new CplxKnowledgeArea();
		knowledgeArea1.setId(hsk);
		knowledgeArea1.setName(hsk);
		return knowledgeArea1;
	}
}
