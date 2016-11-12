package org.palladiosimulator.editors.commons.tabs.parameters;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.util.InternalEList;

public class ParameterUtil extends Copier {

    /**
     *
     */
    private static final long serialVersionUID = 3504940555318719080L;

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyReference(org.eclipse.emf.ecore.EReference,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void copyReference(final EReference eReference, final EObject eObject,
            final EObject copyEObject) {
        if (eObject.eIsSet(eReference)) {
            if (eReference.isMany()) {
                final List<?> source = (List<?>) eObject.eGet(eReference);
                final InternalEList<Object> target = (InternalEList<Object>) copyEObject
                        .eGet(getTarget(eReference));
                if (source.isEmpty()) {
                    target.clear();
                } else {
                    final boolean isBidirectional = eReference.getEOpposite() != null;
                    int index = 0;
                    for (final Iterator<?> k = source.iterator(); k.hasNext();) {
                        final Object referencedEObject = k.next();
                        final Object copyReferencedEObject = get(referencedEObject);
                        if (copyReferencedEObject == null) {
                            if (!isBidirectional) {
                                target.addUnique(index, referencedEObject);
                                ++index;
                            }
                        } else {
                            if (isBidirectional) {
                                final int position = target
                                        .indexOf(copyReferencedEObject);
                                if (position == -1) {
                                    target.addUnique(index,
                                            copyReferencedEObject);
                                } else if (index != position) {
                                    target.move(index, copyReferencedEObject);
                                }
                            } else {
                                target.addUnique(index, copyReferencedEObject);
                            }
                            ++index;
                        }
                    }
                }
            } else {
                final Object referencedEObject = eObject.eGet(eReference);
                if (referencedEObject == null) {
                    copyEObject.eSet(getTarget(eReference), null);
                } else {
                    final Object copyReferencedEObject = get(referencedEObject);
                    if (copyReferencedEObject == null) {
                        if (eReference.getEOpposite() == null && !eReference.getName().equals("expression")) {
                            copyEObject.eSet(getTarget(eReference),
                                    referencedEObject);
                        }
                    } else {
                        copyEObject.eSet(getTarget(eReference),
                                copyReferencedEObject);
                    }
                }
            }
        }
    }

}
