/**
 * 
 */
package de.neuenberger.pmp.processes.model;

import generated.CplxKnowledgeArea;
import generated.CplxProcessGroup;
import generated.CplxProcessGroup.Process;
import generated.CplxProcessGroups;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Kirchmann
 * 
 */
public class KnowledgeAreaFactory {
	public void process(final CplxProcessGroups groups) {
		groups.getKnowledgeArea();
		final List<CplxProcessGroup> list = groups.getProcessGroup();
		final List<CplxKnowledgeArea> knowledgeArea = groups.getKnowledgeArea();
		final Map<String, CplxKnowledgeArea> mapIdToKnowledgeArea = new HashMap<>();
		for (final CplxKnowledgeArea cplxKnowledgeArea : knowledgeArea) {
			mapIdToKnowledgeArea.put(cplxKnowledgeArea.getId(),
					cplxKnowledgeArea);
		}
		for (final CplxProcessGroup cplxProcessGroup : list) {
			final List<Process> processList = cplxProcessGroup.getProcess();
			for (final Process process : processList) {
				final String knowledgeAreaId = process.getKnowledgeAreaId();
				final String[] split = knowledgeAreaId.split(",");
				for (final String kaId : split) {
					final CplxKnowledgeArea area = mapIdToKnowledgeArea
							.get(kaId);
					if (area == null) {
						throw new IllegalStateException(
								"Unknown Knowledge Area: '" + kaId + "'");
					}
					area.getProcess().add(process);
				}
			}
		}

	}

}
