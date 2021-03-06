package de.fu_berlin.inf.dpp.intellij.ui;

import de.fu_berlin.inf.dpp.intellij.util.MessageUtils;

/**
 * UI message bundle.
 */
public class Messages {

    private static final String BUNDLE_NAME = Messages.class.getName()
        .toLowerCase();

    static {
        MessageUtils.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String AddProjectToSessionWizard_title;

    public static String CollaborationUtils_confirm_closing;
    public static String CollaborationUtils_confirm_closing_text;
    public static String CollaborationUtils_confirm_leaving;
    public static String CollaborationUtils_confirm_leaving_text;
    public static String CollaborationUtils_insufficient_privileges;
    public static String CollaborationUtils_insufficient_privileges_text;
    public static String CollaborationUtils_partial;

    public static String ConsistencyAction_confirm_dialog_title;
    public static String ConsistencyAction_message_inconsistency_detected;
    public static String ConsistencyAction_progress_perform_recovery;
    public static String ConsistencyAction_title_inconsistency_detected;
    public static String ConsistencyAction_tooltip_inconsistency_detected;
    public static String ConsistencyAction_tooltip_no_inconsistency;

    public static String NegotiationHandler_canceled_invitation;
    public static String NegotiationHandler_canceled_invitation_text;
    public static String NegotiationHandler_error_during_invitation;
    public static String NegotiationHandler_error_during_invitation_text;
    public static String NegotiationHandler_inviting_user;
    public static String NegotiationHandler_project_sharing_canceled_text;
    public static String NegotiationHandler_sharing_project;
    public static String NegotiationHandler_sharing_project_canceled_remotely;
    public static String NegotiationHandler_sharing_project_canceled_remotely_text;

    public static String EnterProjectNamePage_create_new_project;
    public static String EnterProjectNamePage_project_name;
    public static String EnterProjectNamePage_title2;
    public static String EnterProjectNamePage_use_existing_project;

    public static String ShowDescriptionPage_description;
    public static String ShowDescriptionPage_title2;

    public static String JoinSessionWizard_8;
    public static String JoinSessionWizard_accept;
    public static String JoinSessionWizard_info;
    public static String JoinSessionWizard_inv_canceled;
    public static String JoinSessionWizard_inv_canceled_text;
    public static String JoinSessionWizard_inv_canceled_text2;
    public static String JoinSessionWizard_inv_canceled_text3;
    public static String JoinSessionWizard_title;

    public static String UserStatusChangeHandler_user_joined;
    public static String UserStatusChangeHandler_user_joined_text;
    public static String UserStatusChangeHandler_user_left;
    public static String UserStatusChangeHandler_user_left_text;
    public static String UserStatusChangeHandler_he_has_now_access;
    public static String UserStatusChangeHandler_permission_changed;
    public static String UserStatusChangeHandler_read_only;
    public static String UserStatusChangeHandler_write;
    public static String UserStatusChangeHandler_you_have_now_access;

    public static String SubscriptionManager_incoming_subscription_request_title;
    public static String SubscriptionManager_incoming_subscription_request_message;

    private Messages() {
    }
}
