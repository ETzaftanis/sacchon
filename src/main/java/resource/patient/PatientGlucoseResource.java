package resource.patient;

import exception.AuthorizationException;
import jpaUtil.JpaUtil;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import repository.GlucoseRepository;
import representation.GlucoseRepresentation;
import resource.ResourceUtils;
import security.Shield;
import service.PatientConsultationListResourceService;
import service.PatientGlucoseResourceService;

import javax.persistence.EntityManager;

/**
 * Restful interface of {@link PatientGlucoseResourceService}
 */
public class PatientGlucoseResource extends ServerResource {
    private long patientId;
    private long glucoseId;

    protected void doInit() {
        patientId = Long.parseLong(getAttribute("patientId"));
        glucoseId = Long.parseLong(getAttribute("glucoseId"));
    }

    @Get("json")
    public GlucoseRepresentation getGlucose() throws AuthorizationException {
        ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        return PatientGlucoseResourceService.getGlucose(patientId, glucoseId);
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
        EntityManager em = JpaUtil.getEntityManager();
        GlucoseRepository glucoseRepository = new GlucoseRepository(em);
        glucoseRepository.delete(glucoseRepository.read(glucoseId).getId());
    }
}
