package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import representation.ConsultationRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbResourceService;
import service.PatientConsultationResourceService;


/**
 * Restful interface of {@link PatientConsultationResourceService}
 */
public class PatientConsultationResource extends ServerResource {
    private long patientId;
    private long consultationId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        consultationId = Long.parseLong(getAttribute("consultationId"));
    }

    @Get("json")
    public ConsultationRepresentation getConsultation() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientConsultationResourceService.getConsultation(patientId, consultationId);
    }
}
