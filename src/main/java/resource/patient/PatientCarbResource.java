package resource.patient;

import exception.AuthorizationException;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import representation.CarbRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbResourceService;

public class PatientCarbResource extends ServerResource {
    private long patientId;
    private long carbId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        carbId = Long.parseLong(getAttribute("carbId"));
    }

    @Get("json")
    public CarbRepresentation getCarb() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientCarbResourceService.getCarb(patientId, carbId);
    }

    @Put("json")
    public CarbRepresentation updateCarb(CarbRepresentation carbRepresentation) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientCarbResourceService.updateCarb(carbRepresentation, patientId, carbId);
    }

    @Delete("json")
    public void deleteCarb() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        PatientCarbResourceService.deleteCarb(carbId);
    }
}