package com.mont.algafoodapi.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {
    
    public @interface Cuisine {


        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_CUISINE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowEdit {}
    }

    public @interface Restaurants {
        @PreAuthorize("hasAuthority('SCOPE_read') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowQuery {}

        @PreAuthorize("hasAuthority('SCOPE_write') and hasAuthority('EDIT_RESTAURANTS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface allowEdit {}
    }
}
