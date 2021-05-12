package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import representation.GlucoseRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientGlucoseListResourceService;

import java.util.List;

public class PatientGlucoseListResource extends ServerResource {
    private long patientId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
    }

    @Get("json")
    public List<GlucoseRepresentation> getGlucoseList() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientGlucoseListResourceService.getGlucoseRepresentations(patientId);
    }

    @Post("json")
    public GlucoseRepresentation add(GlucoseRepresentation glucoseRepresentationIn) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientGlucoseListResourceService.getGlucoseRepresentation(glucoseRepresentationIn, patientId);
    }

}
