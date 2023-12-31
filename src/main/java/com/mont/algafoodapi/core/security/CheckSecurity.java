package com.mont.algafoodapi.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {
    
    public @interface Cuisine {


        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_CUISINE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsEdit {}
    }

    public @interface Restaurants {
        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_RESTAURANTS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsEdit {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_RESTAURANTS') or @appSecurity.manageRestaurant(#id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsManageOperation {}

    }

    public @interface Orders {

        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @PostAuthorize("hasAuthority('QUERY_ORDERS') or "
                + "@appSecurity.userAuthenticatedEqual(returnObject.body.client.id) or "
				+ "@appSecurity.manageRestaurant(returnObject.body.restaurant.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}


        @PreAuthorize("@appSecurity.allowsSearchOrder(#filter.clientId, #filter.restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsSearch {}

        @PreAuthorize("@appSecurity.allowsManageOrder(#orderCode)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsManage {}

        @PreAuthorize("hasAuthority('SCOPE_write') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsCreate {}
    }

    public @interface PaymentMethods {

        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_PAYMENT_METHODS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsEdit {}
    }

    public @interface Cities {
        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_CITIES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsEdit {}
    }

    public @interface States {
        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_STATES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsEdit {}
    }

    public @interface UserGroupsPermissions {

        @PreAuthorize("hasAuthority('SCOPE_write') and @appSecurity.getUserId() == #userId")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsChangeOwnPassword {}

        @PreAuthorize("hasAuthority('SCOPE_write') and "
                        + "(@appSecurity.getUserId() == #userId or"
                        + "hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS'))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsChangeUser {}


        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsEdit {}

        @PreAuthorize("hasAuthority('SCOPE_read') and hasAuthority('QUERY_USERS_GROUPS_PERMISSIONS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}

    }

    public @interface Statistics {
        @PreAuthorize("hasAuthority('SCOPE_read') and hasAuthority('CREATE_REPORTS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowsQuery {}
    }
}
