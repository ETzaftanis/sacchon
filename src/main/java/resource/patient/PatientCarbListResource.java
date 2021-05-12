package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import representation.CarbRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbDailyAverageResourceService;
import service.PatientCarbListResourceService;

import java.util.List;


/**
 * Restful interface of {@link PatientCarbListResourceService}
 */
public class PatientCarbListResource extends ServerResource {
    private long patientId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<CarbRepresentation> getCarbList() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientCarbListResourceService.getCarbList(patientId);
    }

    @Post("json")
    public CarbRepresentation addCarb(CarbRepresentation carbRepresentationIn) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientCarbListResourceService.getCarbRepresentation(carbRepresentationIn, patientId);
    }

}

