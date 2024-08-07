package org.keycloak.test.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.test.framework.annotations.KeycloakIntegrationTest;
import org.keycloak.test.framework.annotations.TestClient;
import org.keycloak.test.framework.annotations.TestRealm;
import org.keycloak.test.framework.realm.RealmConfig;

@KeycloakIntegrationTest
public class MultipleInstancesTest {

    @TestRealm
    RealmResource realm1;

    @TestRealm
    RealmResource realm2;

    @TestRealm(ref = "another", config = CustomRealmConfig.class)
    RealmResource realm3;

    @TestClient(ref = "client1")
    ClientResource client;

    @TestClient
    ClientResource client2;

    @Test
    public void testMultipleInstances() {
        Assertions.assertEquals("default", realm1.toRepresentation().getRealm());
        Assertions.assertEquals("default", realm2.toRepresentation().getRealm());
        Assertions.assertEquals(realm1, realm2);

        Assertions.assertEquals("another", realm3.toRepresentation().getRealm());

        Assertions.assertEquals("client1", client.toRepresentation().getClientId());
        Assertions.assertEquals("default", client2.toRepresentation().getClientId());
    }


    public static class CustomRealmConfig implements RealmConfig {
        @Override
        public RealmRepresentation getRepresentation() {
            return new RealmRepresentation();
        }
    }

}
