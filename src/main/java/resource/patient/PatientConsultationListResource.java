package resource.patient;

import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import representation.ConsultationRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientConsultationListResourceService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Restful interface of {@link PatientConsultationListResourceService}
 */
public class PatientConsultationListResource extends ServerResource {
    private long patientId;

    public static final Logger LOGGER = Engine.getLogger(PatientConsultationListResource.class);

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<ConsultationRepresentation> getConsultationList() {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientConsultationListResourceService.getConsultationLIst(patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentationIn){
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientConsultationListResourceService.add(consultationRepresentationIn, patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }
}
