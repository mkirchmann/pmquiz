/**
 * 
 */
package de.neuenberger.pmp.processes.ui;

import java.awt.Component;

/**
 * @author Michael Kirchmann
 * 
 */
public interface Controller<C extends Component, M> {
	C getComponent();

	M getModel();
}
