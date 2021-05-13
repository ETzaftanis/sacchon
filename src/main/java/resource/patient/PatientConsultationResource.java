package resource.patient;

import exception.AuthorizationException;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import representation.ConsultationRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbResourceService;
import service.PatientConsultationResourceService;
import service.PatientGlucoseListResourceService;

import java.util.logging.Logger;


/**
 * Restful interface of {@link PatientConsultationResourceService}
 */
public class PatientConsultationResource extends ServerResource {
    private long patientId;
    private long consultationId;

    public static final Logger LOGGER = Engine.getLogger(PatientConsultationResource.class);

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        consultationId = Long.parseLong(getAttribute("consultationId"));
    }

    @Get("json")
    public ConsultationRepresentation getConsultation() throws AuthorizationException {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientConsultationResourceService.getConsultation(patientId, consultationId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }
}
