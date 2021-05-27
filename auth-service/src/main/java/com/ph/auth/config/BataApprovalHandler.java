package com.ph.auth.config;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

/**
 * Created by Administrator on 2017/4/4 0004.
 */
public class BataApprovalHandler extends ApprovalStoreUserApprovalHandler {

    private int approvalExpirySeconds = -1;

    @Autowired
    private ApprovalStore approvalStore;

    public BataApprovalHandler(
        JdbcClientDetailsService clientDetailsService, ApprovalStore approvalStore, OAuth2RequestFactory oAuth2RequestFactory) {
        this.setApprovalStore(approvalStore);
        this.setClientDetailsService(clientDetailsService);
        this.setRequestFactory(oAuth2RequestFactory);
    }


    @Override
    public AuthorizationRequest updateAfterApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        // Get the approved scopes
        Set<String> requestedScopes = authorizationRequest.getScope();
        Set<String> approvedScopes = new HashSet<String>();
        Set<Approval> approvals = new HashSet<Approval>();

        Date expiry = computeExpiry();

        // Store the scopes that have been approved / denied
        Map<String, String> approvalParameters = authorizationRequest.getApprovalParameters();
        for (String requestedScope : requestedScopes) {
            String approvalParameter = OAuth2Utils.SCOPE_PREFIX + requestedScope;
            String value = approvalParameters.get(approvalParameter);
            value = value == null ? "" : value.toLowerCase();
            if ("true".equals(value) || value.startsWith("approve")||value.equals("on")) {
                approvedScopes.add(requestedScope);
                approvals.add(new Approval(userAuthentication.getName(), authorizationRequest.getClientId(),
                        requestedScope, expiry, Approval.ApprovalStatus.APPROVED));
            }
            else {
                approvals.add(new Approval(userAuthentication.getName(), authorizationRequest.getClientId(),
                        requestedScope, expiry, Approval.ApprovalStatus.DENIED));
            }
        }
        approvalStore.addApprovals(approvals);

        boolean approved;
        authorizationRequest.setScope(approvedScopes);
        if (approvedScopes.isEmpty() && !requestedScopes.isEmpty()) {
            approved = false;
        }
        else {
            approved = true;
        }
        authorizationRequest.setApproved(approved);
        return authorizationRequest;
    }

    private Date computeExpiry() {
        Calendar expiresAt = Calendar.getInstance();
        // use default of 1 month
        if (approvalExpirySeconds == -1) {
            expiresAt.add(Calendar.MONTH, 1);
        }
        else {
            expiresAt.add(Calendar.SECOND, approvalExpirySeconds);
        }
        return expiresAt.getTime();
    }

}
