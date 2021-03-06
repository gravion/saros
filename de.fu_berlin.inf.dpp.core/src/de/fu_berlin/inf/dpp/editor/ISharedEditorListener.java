package de.fu_berlin.inf.dpp.editor;

import de.fu_berlin.inf.dpp.activities.SPath;
import de.fu_berlin.inf.dpp.activities.TextSelectionActivity;
import de.fu_berlin.inf.dpp.session.User;

/**
 * Listener type for events related to local and remote {@link IEditorManager
 * editors} which belong to the current session.
 */
public interface ISharedEditorListener {

    /**
     * Fired when a user activates (moves focus to) an editor. Implies that the
     * editor has been opened if it wasn't open already.
     * 
     * @param filePath
     *            project-relative path of the file associated with the editor,
     *            or <code>null</code> if the user activated an editor that is
     *            not associated with a shared file (or has no open editor at
     *            all)
     * @param user
     *            the user who activated the editor (may be the local user)
     * 
     */
    public void editorActivated(User user, SPath filePath);

    /**
     * Fired when a user closes an editor.
     * 
     * @param user
     *            the user who closed the editor (may be the local user)
     * @param filePath
     *            project-relative path of the file associated with the closed
     *            editor
     */
    public void editorClosed(User user, SPath filePath);

    /**
     * Fired when the local user starts or stops following a remote user.
     * 
     * @param target
     *            the user who will be or was being followed
     * @param isFollowed
     *            <code>true</code> if following has started, <code>false</code>
     *            if it has ended
     * @deprecated Implement {@link IFollowModeListener} instead and register to
     *             the {@link FollowModeManager}.
     */
    @Deprecated
    public void followModeChanged(User target, boolean isFollowed);

    /**
     * Fired when a user changes an editor's text content. This should only be
     * treated as an event notification; it should not be assumed that the
     * change has already been applied locally when this method is called.
     * 
     * @param user
     *            the user who performed the text edit (may be the local user)
     * @param filePath
     *            project-relative path of the file whose associated editor was
     *            changed
     * @param offset
     *            character offset inside the file where the edit occurred
     * @param deletedText
     *            the text deleted at the offset
     * @param insertedText
     *            the text inserted at the offset after deletion
     */
    public void textEdited(User user, SPath filePath, int offset,
        String deletedText, String insertedText);

    /**
     * Fired when a user changes the text selection in an editor. The change has
     * already been applied locally when this method is called.
     * 
     * @param selection
     *            The text selection as a {@link TextSelectionActivity}
     */
    public void textSelectionChanged(TextSelectionActivity selection);

    /**
     * Fired when the local user uses the "jump to user" feature to jump to the
     * active editor and viewport of a remote user.
     * 
     * @param target
     *            the user being jumped to
     */
    public void jumpedToUser(User target);
}
