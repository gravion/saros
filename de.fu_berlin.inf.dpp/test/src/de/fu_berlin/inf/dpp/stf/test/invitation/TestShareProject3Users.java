package de.fu_berlin.inf.dpp.stf.test.invitation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fu_berlin.inf.dpp.net.JID;
import de.fu_berlin.inf.dpp.stf.sarosswtbot.BotConfiguration;
import de.fu_berlin.inf.dpp.stf.sarosswtbot.Musician;

public class TestShareProject3Users {
    // bots
    protected static Musician alice;
    protected static Musician carl;
    protected static Musician bob;

    @BeforeClass
    public static void configurePeer1() throws RemoteException,
        NotBoundException {
        carl = new Musician(new JID(BotConfiguration.JID_CARL),
            BotConfiguration.PASSWORD_CARL, BotConfiguration.HOST_CARL,
            BotConfiguration.PORT_CARL);
        carl.initBot();
    }

    @BeforeClass
    public static void configurePeer2() throws RemoteException,
        NotBoundException {
        bob = new Musician(new JID(BotConfiguration.JID_BOB),
            BotConfiguration.PASSWORD_BOB, BotConfiguration.HOST_BOB,
            BotConfiguration.PORT_BOB);
        bob.initBot();
    }

    @BeforeClass
    public static void configureHost() throws RemoteException,
        NotBoundException {
        alice = new Musician(new JID(BotConfiguration.JID_ALICE),
            BotConfiguration.PASSWORD_ALICE, BotConfiguration.HOST_ALICE,
            BotConfiguration.PORT_ALICE);
        alice.initBot();
        String projectName = BotConfiguration.PROJECTNAME;
        alice.bot.newJavaProjectWithClass(projectName,
            BotConfiguration.PACKAGENAME, BotConfiguration.CLASSNAME);
    }

    @AfterClass
    public static void cleanupInvitee1() throws RemoteException {
        carl.xmppDisconnect();
        carl.bot.deleteProject(BotConfiguration.PROJECTNAME);
    }

    @AfterClass
    public static void cleanupInvitee2() throws RemoteException {
        bob.xmppDisconnect();
        bob.bot.deleteProject(BotConfiguration.PROJECTNAME);
    }

    @After
    public void cleanupInviter() throws RemoteException {
        alice.xmppDisconnect();
        alice.bot.deleteProject(BotConfiguration.PROJECTNAME);
    }

    @Test
    public void testShareProject() throws RemoteException {

        List<String> musicians = new LinkedList<String>();
        musicians.add(carl.getPlainJid());
        musicians.add(bob.getPlainJid());

        alice.bot.shareProjectParallel(BotConfiguration.PROJECTNAME, musicians);

        carl.bot.confirmSessionInvitationWizard(alice.getPlainJid(),
            BotConfiguration.PROJECTNAME);
        // FIXME if this times out, cancel the invitation!
        bob.bot.confirmSessionInvitationWizard(alice.getPlainJid(),
            BotConfiguration.PROJECTNAME);

        assertTrue(carl.state.isParticipant(carl.jid));
        assertTrue(carl.state.isObserver(carl.jid));
        assertTrue(carl.state.isParticipant(bob.jid));
        assertTrue(carl.state.isObserver(bob.jid));
        assertTrue(carl.state.isParticipant(alice.jid));
        assertTrue(carl.state.isDriver(alice.jid));

        assertTrue(bob.state.isParticipant(bob.jid));
        assertTrue(bob.state.isObserver(bob.jid));
        assertTrue(bob.state.isParticipant(carl.jid));
        assertTrue(bob.state.isObserver(carl.jid));
        assertTrue(bob.state.isParticipant(alice.jid));
        assertTrue(bob.state.isDriver(alice.jid));

        assertTrue(alice.state.isParticipant(alice.jid));
        assertTrue(alice.state.isDriver(alice.jid));
        assertTrue(alice.state.isParticipant(carl.jid));
        assertTrue(alice.state.isObserver(carl.jid));
        assertTrue(alice.state.isParticipant(bob.jid));
        assertTrue(alice.state.isObserver(bob.jid));

        carl.leaveSession();
        assertFalse(carl.state.isParticipant(carl.jid));

        bob.leaveSession();
        assertFalse(bob.state.isParticipant(bob.jid));

        alice.waitUntilOtherLeaveSession(carl);
        alice.waitUntilOtherLeaveSession(bob);
        alice.leaveSession();
        assertFalse(alice.state.isParticipant(alice.jid));

        // invitee1.sleep(1000);
        // invitee2.sleep(1000);

    }
}
