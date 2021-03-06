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
import java.awt.Font;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.xml.bind.JAXB;

import de.neuenberger.pmp.processes.generator.OverallQuestionDrawer;
import de.neuenberger.pmp.processes.generator.QuestionDrawer;
import de.neuenberger.pmp.processes.generator.QuestionDrawerFactory;
import de.neuenberger.pmp.processes.model.KnowledgeAreaFactory;
import de.neuenberger.pmp.processes.model.QuestionStatistics;
import de.neuenberger.pmp.processes.ui.Controller;
import de.neuenberger.pmp.processes.ui.QuestionComposite;
import de.neuenberger.pmp.processes.ui.QuestionController;
import de.neuenberger.pmp.processes.ui.QuestionStatisticsComposite;
import de.neuenberger.pmp.processes.ui.QuestionStatisticsController;
import de.neuenberger.pmp.processes.ui.SelectQuestionContainerComposite;
import de.neuenberger.pmp.processes.ui.SelectQuestionContainerController;
import de.neuenberger.pmp.processes.ui.listener.QuestionSelectedWithKeyListener;

/**
 * @author Michael Kirchmann
 * 
 */
public class MainWindow extends JFrame {

    private final JTabbedPane tabPane;

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    MainWindow(final Controller<?, ?>... controllers) {
        setTitle("PMP Quiz");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tabPane = new JTabbedPane();
        for (final Controller<?, ?> controller : controllers) {
            tabPane.addTab(controller.toString(), controller.getComponent());
        }
        this.add(tabPane, BorderLayout.CENTER);
    }

    public static void main(final String[] argv) throws IOException {
        String filename;
        final QuestionDrawer questionDrawer;
        QuestionDrawerFactory questionDrawerFactory;

        if (argv.length == 1 && "hsk".equalsIgnoreCase(argv[0])) {
            filename = "hsk_questions.xml";
            UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Arial", Font.PLAIN, 25));
            questionDrawerFactory = new QuestionDrawerFactory.HskQuestionDrawer();
        } else if (argv.length == 1 && "hskl".equalsIgnoreCase(argv[0])) {
            filename = "hsk_questions_levelled.xml";
            UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Arial", Font.PLAIN, 25));
            questionDrawerFactory = new QuestionDrawerFactory.HskQuestionDrawerLevelled();

        } else {
            filename = "pmp_processes.xml";
            questionDrawerFactory = new QuestionDrawerFactory.PmpQuestionDrawer();
        }
        final InputStream stream = MainWindow.class.getResource(filename).openStream();
        final CplxProcessGroups cplxProcessGroups = JAXB.unmarshal(stream, CplxProcessGroups.class);
        final QuestionStatistics questionStatistics = new QuestionStatistics();
        new KnowledgeAreaFactory().process(cplxProcessGroups);

        final QuestionComposite questionComposite = new QuestionComposite();
        questionDrawer = questionDrawerFactory.createQuestionDrawer(cplxProcessGroups);
        final QuestionController controller = new QuestionController(questionDrawer, questionStatistics,
                questionComposite);
        final SelectQuestionContainerController controller2 = new SelectQuestionContainerController(
                new SelectQuestionContainerComposite(), questionDrawer);

        final QuestionStatisticsController controller3 = new QuestionStatisticsController(
                new QuestionStatisticsComposite(), questionStatistics);

        final MainWindow mainWindow = new MainWindow(controller, controller2, controller3);
        mainWindow.addKeyListener(new QuestionSelectedWithKeyListener(controller));
        mainWindow.setSize(400, 300);
        mainWindow.setVisible(true);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        tabPane.addKeyListener(l);
        super.addKeyListener(l);
    }

    public static void enrichWithHSK() throws IOException {
        CplxProcessGroups cplxProcessGroups = new CplxProcessGroups();
        Charset forName = Charset.forName("UTF-8");
        for (int i = 1; i <= 6; i++) {
            String hsk = "HSK" + i;
            String filename = "HSK Official With Definitions 2012 L" + i + " freqorder.csv";
            try (InputStream resourceAsStream = MainWindow.class.getResourceAsStream(filename);
                    Reader isr = new InputStreamReader(resourceAsStream, forName);
                    BufferedReader bufferedReader = new BufferedReader(isr);) {
                CplxKnowledgeArea knowledgeArea = createKnowledgeArea(hsk);
                CplxKnowledgeArea knowledgeAreaKangxi = createKnowledgeArea(hsk+" Kangxi");
                cplxProcessGroups.getKnowledgeArea().add(knowledgeArea);
                cplxProcessGroups.getKnowledgeArea().add(knowledgeAreaKangxi);
                CplxGroup cplxGroup4 = createGroup(hsk, knowledgeArea, "4");
                CplxGroup cplxGroup5 = createGroup(hsk, knowledgeAreaKangxi, "");

                String readLine = null;
                do {
                    readLine = bufferedReader.readLine();
                    if (readLine != null && !readLine.isEmpty()) {
                        HskLine hskLine = new HskLine(readLine);

                        cplxGroup4.getLevelledDefinition().add(hskLine.createLevelledDefinition());
                        
                        if (hskLine.isKangxi()) {
                            cplxGroup5.getLevelledDefinition().add(hskLine.createLevelledDefinition());
                        }
                    }
                } while (readLine != null);

            } finally {
            }
        }
        JAXB.marshal(cplxProcessGroups, new File("hsk_questions-new.xml"));
    }

    private static CplxGroup createGroup(String hsk, CplxKnowledgeArea knowledgeArea, String id) {
        CplxGroup cplxGroup1 = new CplxGroup();
        cplxGroup1.setName(hsk + " Questions Group " + id);
        CplxAddition cplxAddition = knowledgeArea.getAddition();
        if (cplxAddition == null) {
            cplxAddition = new CplxAddition();
            knowledgeArea.setAddition(cplxAddition);
        }
        cplxAddition.getGroup().add(cplxGroup1);
        return cplxGroup1;
    }

    private static CplxKnowledgeArea createKnowledgeArea(String hsk) {
        CplxKnowledgeArea knowledgeArea1 = new CplxKnowledgeArea();
        knowledgeArea1.setId(hsk);
        knowledgeArea1.setName(hsk);
        return knowledgeArea1;
    }
}
