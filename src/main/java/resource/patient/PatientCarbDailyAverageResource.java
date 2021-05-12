package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbDailyAverageResourceService;

import java.util.List;


/**
 * Restful interface of {@link PatientCarbDailyAverageResourceService}
 */
public class PatientCarbDailyAverageResource extends ServerResource {
    private long id;

    protected void doInit() {

        id = Long.parseLong(getAttribute("patientId"));
    }

    @Get
    public List<Double> getAverageCarb() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        String start = getQueryValue("start");
        String end = getQueryValue("end");
        return PatientCarbDailyAverageResourceService.getAverageCarb(id, start, end);
    }

}
