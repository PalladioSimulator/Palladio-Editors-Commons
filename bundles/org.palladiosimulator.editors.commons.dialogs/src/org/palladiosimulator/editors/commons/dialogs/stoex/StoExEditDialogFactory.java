package org.palladiosimulator.editors.commons.dialogs.stoex;

import org.eclipse.swt.widgets.Shell;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public interface StoExEditDialogFactory {

    StoExEditDialog create(Shell parentShell, TypeEnum expectedType);

}
