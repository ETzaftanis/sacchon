package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbAverageResourceService;


/**
 * Restful interface of {@link PatientCarbAverageResourceService}
 */
public class PatientCarbAverageResource extends ServerResource {
    private long patientId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get
    public Double getAverageCarb() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);

        String start = getQueryValue("start");
        String end = getQueryValue("end");

        return PatientCarbAverageResourceService.getAverageCarb(patientId, start, end);
    }
}
