package de.fu_berlin.inf.dpp.project;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import de.fu_berlin.inf.dpp.activities.IActivity;
import de.fu_berlin.inf.dpp.editor.EditorManager;
import de.fu_berlin.inf.dpp.project.internal.RoleManager;

/**
 * The activity registry holds references to all active activity providers and
 * their activities.
 * 
 * @author rdjemili
 */
public class ActivityRegistry {
    private final List<IActivityProvider> activityProviders = new ArrayList<IActivityProvider>();

    private static ActivityRegistry instance;

    /**
     * @return the singleton instance of this registry..
     */
    public static ActivityRegistry getDefault() {
        if (ActivityRegistry.instance == null) {
            ActivityRegistry.instance = new ActivityRegistry();
        }

        return ActivityRegistry.instance;
    }

    public void addProvider(IActivityProvider provider) {
        if (!this.activityProviders.contains(provider)) {
            this.activityProviders.add(provider);
        }
    }

    /**
     * Converts given XML data into an activity.
     */
    public IActivity parseActivity(XmlPullParser parser) {
        /*
         * TODO When all activities are (de)serialized with XStream the
         * providers won't be necessary here anymore.
         */
        IActivity activity = null;
        for (IActivityProvider provider : this.activityProviders) {
            activity = provider.fromXML(parser);

            if (activity != null) {
                return activity;
            }
        }

        return null;
    }

    /**
     * Singleton constructor
     */
    private ActivityRegistry() {
        loadDefaultActivityProviders();
        loadExtensionPoints();
    }

    private void loadDefaultActivityProviders() {
        addProvider(EditorManager.getDefault());
        addProvider(new SharedResourcesManager());
        addProvider(new RoleManager());
    }

    private void loadExtensionPoints() {
        /*
         * TODO 1 [NEW FEATURE] load activity providers from the extension-point
         * for Saros
         */
    }
}
