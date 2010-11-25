package de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.noGUI;

import java.rmi.RemoteException;
import java.util.List;

import org.limewire.collection.Tuple;

import de.fu_berlin.inf.dpp.Saros;
import de.fu_berlin.inf.dpp.accountManagement.XMPPAccount;
import de.fu_berlin.inf.dpp.accountManagement.XMPPAccountStore;
import de.fu_berlin.inf.dpp.editor.EditorManager;
import de.fu_berlin.inf.dpp.net.ConnectionState;
import de.fu_berlin.inf.dpp.net.JID;
import de.fu_berlin.inf.dpp.net.internal.DataTransferManager.NetTransferMode;
import de.fu_berlin.inf.dpp.project.ISarosSession;
import de.fu_berlin.inf.dpp.project.SarosSessionManager;
import de.fu_berlin.inf.dpp.project.SharedProject;
import de.fu_berlin.inf.dpp.stf.client.Musician;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.noGUI.State;
import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.saros.workbench.SessionViewComponent;

/**
 * This is used to check the state of {@link Saros} from the inside. Use this
 * from {@link Musician}.
 */
public interface SarosState extends State {

    /**
     * Test if you are now in a session. <br>
     * You can also use another function
     * {@link SessionViewComponent#isInSession()} , which test the session state
     * with GUI.
     * 
     * <p>
     * <b>Attention:</b> <br>
     * Try to use the {@link SessionViewComponent#isInSession()} and
     * {@link SarosState#isInSession()} together in your junittests.
     * 
     * 
     * @return <tt>true</tt> if {@link SarosSessionManager#getSarosSession()} is
     *         not null.
     * 
     * @throws RemoteException
     * @see SarosSessionManager#getSarosSession()
     */
    public boolean isInSession() throws RemoteException;

    /**
     * Returns true if all given jids are drivers of the project.
     */
    public boolean areDrivers(List<JID> jids) throws RemoteException;

    /**
     * Returns true if all given jids are observers of the project.
     */
    public boolean areObservers(List<JID> jids) throws RemoteException;

    /**
     * Returns true if all given jids are participants of the project.
     */
    public boolean areParticipants(List<JID> jids) throws RemoteException;

    /**
     * Returns true if this Saros is connected to a XMPP server.
     */
    public boolean isConnected() throws RemoteException;

    /**
     * Returns true if the incoming connection from destJid was Jingle TCP.
     */
    public boolean isIncomingConnectionJingleTCP(JID destJid)
        throws RemoteException;

    /**
     * Returns true if the incoming connection from destJid was Jingle UDP.
     */
    public boolean isIncomingConnectionJingleUDP(JID destJid)
        throws RemoteException;

    /**
     * Returns true if the incoming connection from destJid was Socks5
     * Bytestream (XEP-0065).
     */
    public boolean isIncomingConnectionSocks5ByteStream(JID destJid)
        throws RemoteException;

    /**
     * Returns true if the incoming connection from destJid was In-Band
     * Bytestream (XEP-0047).
     */
    public boolean isIncomingConnectionIBB(JID destJid) throws RemoteException;

    /**
     * Returns true if the outgoing connection to destJid was Jingle TCP.
     */
    public boolean isOutgoingConnectionJingleTCP(JID destJid)
        throws RemoteException;

    /**
     * Returns true if the outgoing connection to destJid was Jingle UDP.
     */
    public boolean isOutgoingConnectionJingleUDP(JID destJid)
        throws RemoteException;

    /**
     * Returns true if the outgoing connection to destJid was Socks5 Bytestream
     * (XEP-0065).
     */
    public boolean isOutgoingConnectionSocks5ByteStream(JID destJid)
        throws RemoteException;

    /**
     * Returns true if the outgoing connection to destJid was In-Band Bytestream
     * (XEP-0047).
     */
    public boolean isOutgoingConnectionIBB(JID destJid) throws RemoteException;

    /**
     * Returns true if the given {@link JID} is a participant of our
     * {@link SharedProject}.
     */
    public boolean isParticipant(JID jid) throws RemoteException;

    /**
     * Returns true if the given {@link JID} is a driver in this
     * {@link SharedProject}.
     */
    public boolean isDriver(JID jid) throws RemoteException;

    /**
     * Returns true if the given {@link JID} is a observer in this
     * {@link SharedProject}.
     */
    public boolean isObserver(JID jid) throws RemoteException;

    /**
     * Returns a {@link Tuple} of incoming and outgoing {@link NetTransferMode}
     * to destJid. These can be:
     * 
     * UNKNOWN, IBB, JINGLETCP, JINGLEUDP or HANDMADE
     */
    public Tuple<NetTransferMode, NetTransferMode> getConnection(JID destJid)
        throws RemoteException;

    /**
     * Returns the {@link ConnectionState}. It can be:
     * 
     * NOT_CONNECTED, CONNECTING, CONNECTED, DISCONNECTING or ERROR
     */
    public ConnectionState getXmppConnectionState() throws RemoteException;

    public boolean hasContactWith(JID jid) throws RemoteException;

    public ISarosSession getProject() throws RemoteException;

    public String getContents(String path) throws RemoteException;

    public String getPathToScreenShot() throws RemoteException;

    /**
     * @return <tt>true</tt>, if you are currently following another user.
     * @throws RemoteException
     * @see EditorManager#isFollowing
     */
    public boolean isInFollowMode() throws RemoteException;

    public boolean isHost(JID jid) throws RemoteException;

    // public boolean isClassDirty(String projectName, String pkg, String
    // className)
    // throws RemoteException, FileNotFoundException;

    public JID getFollowedUserJID() throws RemoteException;

    public boolean isFollowingUser(String plainJID) throws RemoteException;

    public boolean isExclusiveDriver() throws RemoteException;

    public boolean isDriver() throws RemoteException;

    public void setJID(JID jid) throws RemoteException;

    public JID getJID() throws RemoteException;

    public boolean isSameUser(JID otherJID) throws RemoteException;

    /**
     * @param jid
     *            a Jabber ID which is used to identify the users of the Jabber
     *            network, more about it please see {@link JID}.
     * @return <tt>true</tt> if the account specified by the given jid exists in
     *         preference store
     * @throws RemoteException
     * @see XMPPAccountStore#getAllAccounts()
     */
    public boolean isAccountExist(JID jid) throws RemoteException;

    /**
     * 
     * @param jid
     *            a Jabber ID which is used to identify the users of the Jabber
     *            network, more about it please see {@link JID}.
     * @return <tt>true</tt> if the acount specified by the given jid is active
     * @throws RemoteException
     * @see XMPPAccount#isActive()
     */
    public boolean isAccountActive(JID jid) throws RemoteException;

    /**
     * activate the account specified by the given jid
     * 
     * @param jid
     *            a Jabber ID which is used to identify the users of the Jabber
     *            network, more about it please see {@link JID}.
     * @throws RemoteException
     * @see XMPPAccountStore#setAccountActive(XMPPAccount)
     */
    public void activateAccount(JID jid) throws RemoteException;

    /**
     * Creates an account.
     * 
     * @param username
     *            the username of the new account.
     * @param password
     *            the password of the new account.
     * @param server
     *            the server of the new account.
     * 
     * @throws RemoteException
     */
    public void createAccount(String username, String password, String server)
        throws RemoteException;

    /**
     * 
     * change the account specified by the given jid
     * 
     * @param jid
     *            a Jabber ID which is used to identify the users of the Jabber
     *            network, more about it please see {@link JID}.
     * @param newUserName
     *            the new username
     * @param newPassword
     *            the new password
     * @param newServer
     *            the new server
     * @throws RemoteException
     */
    public void changeAccount(JID jid, String newUserName, String newPassword,
        String newServer) throws RemoteException;

    /**
     * delete the account specified by the given jid
     * 
     * @param jid
     *            a Jabber ID which is used to identify the users of the Jabber
     *            network, more about it please see {@link JID}.
     * @throws RemoteException
     */
    public void deleteAccount(JID jid) throws RemoteException;
}
