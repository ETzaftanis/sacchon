package resource.patient;

import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.*;
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
    public PatientRepresentation getPatient() {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientSettingsResourceService.getPatient(patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Put("json")
    public PatientRepresentation updatePatient(PatientRepresentation patientRepresentation) {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientSettingsResourceService.updatePatient(patientRepresentation, patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Delete("json")
    public void deletePatient() {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            PatientSettingsResourceService.deletePatient(patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

}
