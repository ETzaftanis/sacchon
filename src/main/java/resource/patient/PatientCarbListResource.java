package resource.patient;

import exception.AuthorizationException;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import representation.CarbRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbDailyAverageResourceService;
import service.PatientCarbListResourceService;
import service.PatientCarbResourceService;

import java.util.List;
import java.util.logging.Logger;


/**
 * Restful interface of {@link PatientCarbListResourceService}
 */
public class PatientCarbListResource extends ServerResource {
    private long patientId;

    public static final Logger LOGGER = Engine.getLogger(PatientCarbListResource.class);

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<CarbRepresentation> getCarbList() throws AuthorizationException {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientCarbListResourceService.getCarbList(patientId);

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Post("json")
    public CarbRepresentation addCarb(CarbRepresentation carbRepresentationIn){
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientCarbListResourceService.getCarbRepresentation(carbRepresentationIn, patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

}

