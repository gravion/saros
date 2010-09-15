package de.fu_berlin.inf.dpp.vcs;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

/**
 * SubclipseConsoleListener which doesn't do anything yet. Could be used to
 * parse SVN operations on drivers in order to send VCSActivities as soon as the
 * operation is started.
 */
public class SubclipseConsoleListener implements IPatternMatchListenerDelegate {
    private static final Logger log = Logger
        .getLogger(SubclipseConsoleListener.class);

    TextConsole console;

    public void connect(TextConsole console) {
        log.trace("SVN console found");
        this.console = console;
    }

    public void disconnect() {
        log.trace("SVN console lost");
        this.console = null;
    }

    List<String> operations;
    {
        String[] array = { "switch", "update", "checkout", };
        operations = Arrays.asList(array);
    }

    public void matchFound(PatternMatchEvent event) {
        if (!log.isTraceEnabled())
            return;
        log.trace("SVN console match");
        IDocument document = console.getDocument();
        int offset = event.getOffset();
        int length = event.getLength();
        try {
            if (document.getChar(offset) == ' ')
                return;
            String match = document.get(offset, length);
            String firstword = match.split(" ", 2)[0].trim();
            if (operations.contains(firstword)) {
                log.trace(match);
            }
        } catch (BadLocationException e) {
            log.debug("", e);
        }
    }
}
