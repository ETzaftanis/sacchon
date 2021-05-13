package resource.patient;

import exception.AuthorizationException;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.*;
import representation.CarbRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientCarbListResourceService;
import service.PatientCarbResourceService;
import service.PatientConsultationListResourceService;

import java.util.logging.Logger;


/**
 * Restful interface of {@link PatientCarbResourceService}
 */
public class PatientCarbResource extends ServerResource {
    private long patientId;
    private long carbId;

    public static final Logger LOGGER = Engine.getLogger(PatientCarbResource.class);

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        carbId = Long.parseLong(getAttribute("carbId"));
    }

    @Get("json")
    public CarbRepresentation getCarb(){
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientCarbResourceService.getCarb(patientId, carbId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Put("json")
    public CarbRepresentation updateCarb(CarbRepresentation carbRepresentation){
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientCarbResourceService.updateCarb(carbRepresentation, patientId, carbId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Delete("json")
    public void deleteCarb() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        PatientCarbResourceService.deleteCarb(carbId);
    }
}