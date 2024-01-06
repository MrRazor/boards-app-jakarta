package cz.uhk.boardsappjakarta.security;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@BasicAuthenticationMechanismDefinition
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:jboss/datasources/BoardsDS",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select authority from authorities where username = ?",
        hashAlgorithm = BCryptPasswordHash.class)
@DeclareRoles({"ROLE_ADMIN", "ROLE_USER"})
@ApplicationScoped
public class SecurityConfiguration {

}