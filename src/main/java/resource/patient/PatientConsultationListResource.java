package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import representation.ConsultationRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientConsultationListResourceService;

import java.util.List;
/**
 * Restful interface of {@link PatientConsultationListResourceService}
 */
public class PatientConsultationListResource extends ServerResource {
    private long patientId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<ConsultationRepresentation> getConsultationList() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientConsultationListResourceService.getConsultationLIst(patientId);
    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentationIn) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientConsultationListResourceService.add(consultationRepresentationIn, patientId);
    }
}
