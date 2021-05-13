package resource.patient;

import exception.AuthorizationException;
import jpaUtil.JpaUtil;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.*;
import repository.GlucoseRepository;
import representation.GlucoseRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientGlucoseResourceService;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Restful interface of {@link PatientGlucoseResourceService}
 */
public class PatientGlucoseResource extends ServerResource {
    public static final Logger LOGGER = Engine.getLogger(PatientGlucoseResource.class);

    private long patientId;
    private long glucoseId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        glucoseId = Long.parseLong(getAttribute("glucoseId"));
    }

    @Get("json")
    public GlucoseRepresentation getGlucose() {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            return PatientGlucoseResourceService.getGlucose(patientId, glucoseId);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Error While Checking Role");
        }
    }

    @Put("json")
    public GlucoseRepresentation updateGlucose(GlucoseRepresentation glucoseRepresentationIn) throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        PatientGlucoseResourceService.update(glucoseRepresentationIn, patientId);
        return glucoseRepresentationIn;
    }

    @Delete("json")
    public void deleteGlucose() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        EntityManager em = JpaUtil.getEntityManager();//TODO
        GlucoseRepository glucoseRepository = new GlucoseRepository(em);
        glucoseRepository.delete(glucoseRepository.read(glucoseId).getId());
    }
}
