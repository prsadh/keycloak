import React, { useState, useEffect } from "react";
import {
  ActionGroup,
  AlertVariant,
  Button,
  Form,
  PageSection,
} from "@patternfly/react-core";
import { convertToFormValues } from "../../../util";
import ComponentRepresentation from "keycloak-admin/lib/defs/componentRepresentation";
import { useAdminClient } from "../../../context/auth/AdminClient";
import { ViewHeader } from "../../../components/view-header/ViewHeader";
import { useHistory, useParams } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useAlerts } from "../../../components/alert/Alerts";
import { useTranslation } from "react-i18next";
import { LdapMapperUsername } from "./LdapMapperUsername";
import { useRealm } from "../../../context/realm-context/RealmContext";

export const LdapMappingDetails = () => {
  const form = useForm<ComponentRepresentation>();
  const [mapper, setMapper] = useState<ComponentRepresentation>();
  const adminClient = useAdminClient();
  const { mapperId } = useParams<{ mapperId: string }>();
  const history = useHistory();

  const { realm } = useRealm();
  const id = mapperId;
  const { t } = useTranslation("user-federation");
  const { addAlert } = useAlerts();

  useEffect(() => {
    (async () => {
      if (mapperId) {
        const fetchedMapper = await adminClient.components.findOne({ id });
        if (fetchedMapper) {
          // TODO: remove after adding all mapper types
          console.log("LdapMappingDetails: id used in findOne(id) call::");
          console.log(id);
          console.log("LdapMappingDetails: data returned from findOne(id):");
          console.log(fetchedMapper);
          setMapper(fetchedMapper);
          setupForm(fetchedMapper);
        }
      }
    })();
  }, []);

  const setupForm = (mapper: ComponentRepresentation) => {
    Object.entries(mapper).map((entry) => {
      if (entry[0] === "config") {
        convertToFormValues(entry[1], "config", form.setValue);
      } else {
        form.setValue(entry[0], entry[1]);
      }
    });
  };

  const save = () => {
    addAlert(
      t(
        id === "new"
          ? "Create functionality not implemented yet!"
          : "Save functionality not implemented yet!"
      ),
      AlertVariant.success
    );
    history.push(`/${realm}/user-federation`);
  };

  return (
    <>
      <ViewHeader titleKey={mapper ? mapper.name! : ""} subKey="" />
      <PageSection variant="light" isFilled>
        <LdapMapperUsername form={form} />
        <Form onSubmit={form.handleSubmit(save)}>
          <ActionGroup className="keycloak__form_actions">
            <Button
              isDisabled={!form.formState.isDirty}
              variant="primary"
              type="submit"
              data-testid="ldap-save"
            >
              {t("common:save")}
            </Button>
            <Button
              variant="link"
              onClick={() => history.push(`/${realm}/user-federation`)}
              data-testid="ldap-cancel"
            >
              {t("common:cancel")}
            </Button>
          </ActionGroup>
        </Form>
      </PageSection>
    </>
  );
};
