package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import resource.ResourceUtils;
import security.Shield;
import service.PatientGlucoseAverageResourceService;

/**
 * Restful interface of {@link PatientGlucoseAverageResourceService}
 */
public class PatientGlucoseAverageResource extends ServerResource {
    private long patientId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get
    public Double getAverageGlucose() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        String start = getQueryValue("start");
        String end = getQueryValue("end");
        return PatientGlucoseAverageResourceService.getAverageGlucose(patientId, start, end);
    }
}
