package de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import de.fu_berlin.inf.dpp.stf.sarosSWTBot.SarosSWTBot;
import de.fu_berlin.inf.dpp.stf.server.SarosConstant;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.conditions.SarosSWTBotPreferences;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.BasicPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.EditorPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.HelperPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.MenuPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.PerspectivePart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.TablePart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.ToolbarPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.TreePart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.ViewPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noExportedObjects.WindowPart;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.noGUI.SarosState;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.ChatViewComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.RSViewComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.RosterViewComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.SarosMainMenuComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.SarosPEViewComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.SarosWorkbenchComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.SessionViewComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.workbench.BasicComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.workbench.EditorComponent;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.workbench.ProgressViewComponent;

public abstract class EclipseComponent {
    protected static final transient Logger log = Logger
        .getLogger(EclipseComponent.class);

    // Title of Buttons
    protected final static String YES = SarosConstant.BUTTON_YES;
    protected final static String OK = SarosConstant.BUTTON_OK;
    protected final static String NO = SarosConstant.BUTTON_NO;
    protected final static String CANCEL = SarosConstant.BUTTON_CANCEL;
    protected final static String FINISH = SarosConstant.BUTTON_FINISH;
    protected final static String NEXT = SarosConstant.BUTTON_NEXT;

    public static final String SRC = "src";
    public final static String SUFIX_JAVA = ".java";
    protected final static String PROGRESSINFORMATION = SarosConstant.SHELL_TITLE_PROGRESS_INFORMATION;

    // Role:Driver
    protected final static String ROLENAME = SarosConstant.ROLENAME;

    // exported objects
    public static BasicComponent basicC;
    public static SarosMainMenuComponent mainMenuC;
    public static EditorComponent editorC;
    public static SarosWorkbenchComponent workbenchC;
    public static SarosState state;
    public static RosterViewComponent rosterVC;
    public static SessionViewComponent sessonVC;
    public static RSViewComponent rsVC;
    public static ChatViewComponent chatVC;
    public static SarosPEViewComponent peVC;
    public static ProgressViewComponent progressC;

    // No exported objects
    public static TablePart tablePart;
    public static MenuPart menuPart;
    public static TreePart treePart;
    public static WindowPart windowPart;
    public static BasicPart basicPart;
    public static ViewPart viewPart;
    public static HelperPart helperPart;
    public static PerspectivePart perspectivePart;
    public static EditorPart editorPart;
    public static ToolbarPart toolbarPart;

    public static SarosSWTBot bot;
    public static int sleepTime = 750;

    protected void waitUntil(ICondition condition) {
        bot.waitUntil(condition, SarosSWTBotPreferences.SAROS_TIMEOUT);
    }

    abstract protected void precondition() throws RemoteException;

    public String getClassPath(String projectName, String pkg, String className) {
        return projectName + "/src/" + pkg.replaceAll("\\.", "/") + "/"
            + className + ".java";
    }

    public String getPkgPath(String projectName, String pkg) {
        return projectName + "/src/" + pkg.replaceAll("\\.", "/");
    }

    public String[] getClassNodes(String projectName, String pkg,
        String className) {
        String[] nodes = { projectName, SRC, pkg, className + SUFIX_JAVA };
        return nodes;
    }

    public String[] getPkgNodes(String projectName, String pkg) {
        String[] nodes = { projectName, SRC, pkg };
        return nodes;
    }

    public String getPath(String... nodes) {
        String folderpath = "";
        for (int i = 0; i < nodes.length; i++) {
            if (i == nodes.length - 1) {
                folderpath += nodes[i];
            } else
                folderpath += nodes[i] + "/";
        }
        return folderpath;
    }

}
