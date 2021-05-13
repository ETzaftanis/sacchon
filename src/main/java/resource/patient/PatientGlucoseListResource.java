package resource.patient;

import exception.AuthorizationException;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import representation.GlucoseRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientGlucoseListResourceService;
import service.PatientGlucoseResourceService;

import java.util.List;
import java.util.logging.Logger;

public class PatientGlucoseListResource extends ServerResource {
    private long patientId;

    public static final Logger LOGGER = Engine.getLogger(PatientGlucoseListResource.class);

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<GlucoseRepresentation> getGlucoseList() throws AuthorizationException {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientGlucoseListResourceService.getGlucoseRepresentations(patientId);

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Post("json")
    public GlucoseRepresentation add(GlucoseRepresentation glucoseRepresentationIn) throws AuthorizationException {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientGlucoseListResourceService.getGlucoseRepresentation(glucoseRepresentationIn, patientId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

}
