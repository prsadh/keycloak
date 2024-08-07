/*
 * Copyright 2024 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.protocol.oid4vc.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pojo to represent a VerifiableCredential for internal handling
 *
 * @author <a href="https://github.com/wistefan">Stefan Wiedemann</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifiableCredential {

    public static final String VC_CONTEXT_V1 = "https://www.w3.org/ns/credentials/v1";
    public static final String VC_CONTEXT_V2 = "https://www.w3.org/ns/credentials/v2";

    /**
     * @context: The value of the @context property MUST be an ordered set where the first item is a URL with the
     * value https://www.w3.org/ns/credentials/v2. Subsequent items in the ordered set MUST be composed of any
     * combination of URLs and/or objects, where each is processable as a JSON-LD Context.
     */
    @JsonProperty("@context")
    private List<String> context = new ArrayList<>(List.of(VC_CONTEXT_V1));
    private List<String> type = new ArrayList<>();
    private URI issuer;
    private Instant issuanceDate;
    private URI id;
    private Instant expirationDate;
    private CredentialSubject credentialSubject = new CredentialSubject();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public VerifiableCredential setAdditionalProperties(String name, Object property) {
        additionalProperties.put(name, property);
        return this;
    }

    public List<String> getContext() {
        return context;
    }

    public VerifiableCredential setContext(List<String> context) {
        this.context = context;
        return this;
    }

    public List<String> getType() {
        return type;
    }

    public VerifiableCredential setType(List<String> type) {
        this.type = type;
        return this;
    }

    public URI getIssuer() {
        return issuer;
    }

    public VerifiableCredential setIssuer(URI issuer) {
        this.issuer = issuer;
        return this;
    }

    public Instant getIssuanceDate() {
        return issuanceDate;
    }

    public VerifiableCredential setIssuanceDate(Instant issuanceDate) {
        this.issuanceDate = issuanceDate;
        return this;
    }

    public URI getId() {
        return id;
    }

    public VerifiableCredential setId(URI id) {
        this.id = id;
        return this;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public VerifiableCredential setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public CredentialSubject getCredentialSubject() {
        return credentialSubject;
    }

    public VerifiableCredential setCredentialSubject(CredentialSubject credentialSubject) {
        this.credentialSubject = credentialSubject;
        return this;
    }

    public VerifiableCredential setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }
}
