package de.fu_berlin.inf.dpp.ui.widgetGallery.demoSuits.wizard.pages;

import org.eclipse.jface.wizard.IWizardPage;

import de.fu_berlin.inf.dpp.ui.widgetGallery.annotations.Demo;
import de.fu_berlin.inf.dpp.ui.widgetGallery.demoExplorer.WizardPageDemo;
import de.fu_berlin.inf.dpp.ui.wizards.pages.BuddySelectionWizardPage;

@Demo("This demo show a BuddySelectionWizardPage that reflects the buddies that were selected on creation.")
public class BuddySelectionDemo extends WizardPageDemo {
    @Override
    public IWizardPage getWizardPage() {
	return new BuddySelectionWizardPage();
    }
}
