package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import representation.ConsultationRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbAverageResourceService;
import service.PatientConsultationListResourceService;

import java.util.List;
/**
 * Restful interface of {@link PatientConsultationListResourceService}
 */
public class PatientConsultationListResource extends ServerResource {
    private long id;

    protected void doInit() {
        id = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<ConsultationRepresentation> getConsultationList() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientConsultationListResourceService.getConsultationLIst(id);
    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentationIn) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientConsultationListResourceService.add(consultationRepresentationIn, id);
    }
}
