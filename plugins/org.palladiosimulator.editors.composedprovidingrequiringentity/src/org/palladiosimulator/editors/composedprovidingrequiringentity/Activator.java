package org.palladiosimulator.editors.composedprovidingrequiringentity;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "org.palladiosimulator.editors.composedprovidingrequiringentity";

	// The shared instance
	private static Activator plugin;

	private static Set<Viewpoint> viewpoints;

	public Viewpoint SYSTEM_DESIGN;
	public RepresentationDescription COMPOSED_PROVIDING_REQUIRING_ENTITY_DIAGRAM;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
      super.start(context);
	  plugin = this;
	  viewpoints = new HashSet<Viewpoint>();
	  viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/composedprovidingrequiringentity.odesign")); 
	  
	  // Set viewpoint constants
	  for (Viewpoint viewpoint : viewpoints) {
		  if (viewpoint.getName().equals("System Design")) {
			  SYSTEM_DESIGN = viewpoint;
              break;
          }
	  }

	  // Set diagram description constants
	  for (RepresentationDescription representationDescription : SYSTEM_DESIGN.getOwnedRepresentations()) {
		  if (representationDescription.getName().equals("ComposedProvidingRequiringEntity Diagram")) {
			  COMPOSED_PROVIDING_REQUIRING_ENTITY_DIAGRAM = representationDescription;
			  break;
		  }
		  
	  }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (viewpoints != null) {
			for (final Viewpoint viewpoint : viewpoints) {
				ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
			}
			viewpoints.clear();
			viewpoints = null;
		}
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
}