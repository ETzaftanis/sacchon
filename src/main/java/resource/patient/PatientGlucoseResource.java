package resource.patient;

import exception.AuthorizationException;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.*;
import representation.GlucoseRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientGlucoseResourceService;

import java.util.logging.Logger;

/**
 * Restful interface of {@link PatientGlucoseResourceService}
 */
public class PatientGlucoseResource extends ServerResource {
    public static final Logger LOGGER = Engine.getLogger(PatientGlucoseResource.class);

    private long patientId;
    private long glucoseId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        glucoseId = Long.parseLong(getAttribute("glucoseId"));
    }

    @Get("json")
    public GlucoseRepresentation getGlucose() {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientGlucoseResourceService.getGlucose(patientId, glucoseId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Put("json")
    public GlucoseRepresentation updateGlucose(GlucoseRepresentation glucoseRepresentationIn) {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            PatientGlucoseResourceService.update(glucoseRepresentationIn, patientId);
            return glucoseRepresentationIn;
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Delete("json")
    public void deleteGlucose() {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            PatientGlucoseResourceService.delete(glucoseId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }


}
