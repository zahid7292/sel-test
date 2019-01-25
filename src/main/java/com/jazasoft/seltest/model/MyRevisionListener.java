package com.jazasoft.seltest.model;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by mdzahidraza on 01/07/17.
 */
public class MyRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        MyRevisionEntity entity = (MyRevisionEntity)revisionEntity;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated())) {
            //model.setUsername(((User) authentication.getPrincipal()).getName());
            entity.setUsername("test_user");
        }
    }
}
