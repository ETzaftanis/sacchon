package resource.patient;

import exception.AuthorizationException;
import org.restlet.engine.Engine;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import representation.PatientRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientSettingsResourceService;

import java.util.logging.Logger;

/**
 * Restful interface of {@link PatientSettingsResourceService}
 */
public class PatientSettingsResource extends ServerResource {

    public static final Logger LOGGER = Engine.getLogger(PatientSettingsResource.class);

    private long patientId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("id"));
    }

    @Get("json")
    public PatientRepresentation getPatient() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientSettingsResourceService.getPatient(patientId);
    }

    @Put("json")
    public PatientRepresentation updatePatient(PatientRepresentation patientRepresentation) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientSettingsResourceService.updatePatient(patientRepresentation, patientId);
    }

    @Delete("json")
    public void deletePatient() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        PatientSettingsResourceService.deletePatient(patientId);
    }

}
