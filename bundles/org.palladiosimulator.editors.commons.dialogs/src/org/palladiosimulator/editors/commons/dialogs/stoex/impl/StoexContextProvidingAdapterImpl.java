package org.palladiosimulator.editors.commons.dialogs.stoex.impl;

import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.commons.stoex.services.StoexContext;
import org.palladiosimulator.commons.stoex.services.StoexContextProvider;
import org.palladiosimulator.editors.commons.dialogs.stoex.StoExContextProvidingAdapter;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class StoexContextProvidingAdapterImpl implements StoExContextProvidingAdapter {

    private RandomVariable stoexContainer;
    private TypeEnum expectedType;
    private Notifier target;

    public Notifier getTarget() {
        return target;
    }

    public void notifyChanged(Notification notification) {
    }

    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    @Override
    public boolean isAdapterForType(Object type) {
        return type == StoexContextProvider.class;
    }

    @Override
    public void setStoexContainer(RandomVariable stoexContainer) {
        this.stoexContainer = stoexContainer;
    }

    @Override
    public StoexContext getContext(Resource resource) {
        return createStoexContext(stoexContainer, expectedType);
    }

    protected static StoexContext createStoexContext(RandomVariable stoexContainer, TypeEnum expectedType) {
        return new StoexContext() {

            @Override
            public Optional<RandomVariable> getContainer() {
                return Optional.ofNullable(stoexContainer);
            }

            @Override
            public Optional<TypeEnum> getExpectedType() {
                return Optional.ofNullable(expectedType);
            }
        };
    }

    @Override
    public void setExpectedType(TypeEnum expectedType) {
        this.expectedType = expectedType;
    }

}
