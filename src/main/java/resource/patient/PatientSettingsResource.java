package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import representation.PatientRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientSettingsResourceService;

import java.util.Objects;

public class PatientSettingsResource extends ServerResource {
    private long id;

    protected void doInit() {
        id = Long.parseLong(getAttribute("id"));
    }

    private PatientSettingsResourceService patientSettingsResourceService;

    public PatientSettingsResource(PatientSettingsResourceService patientSettingsResourceService) {
        this.setService(patientSettingsResourceService);
    }

    private void setService(PatientSettingsResourceService patientSettingsResourceService) {
        Objects.requireNonNull(patientSettingsResourceService, "patient setting service can't be null");
        this.patientSettingsResourceService = patientSettingsResourceService;
    }

    @Get("json")
    public PatientRepresentation getPatient() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);

        return patientSettingsResourceService.getPatient(id);
    }

    @Put("json")
    public PatientRepresentation updatePatient(PatientRepresentation patientRepresentation) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);

        patientSettingsResourceService.updatePatient(patientRepresentation, id);

        return patientRepresentation;
    }

    @Delete("json")
    public void deletePatient() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);

        patientSettingsResourceService.deletePatient(id);
    }

}
