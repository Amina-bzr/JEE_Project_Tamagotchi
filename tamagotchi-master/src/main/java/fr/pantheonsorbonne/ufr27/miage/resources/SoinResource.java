package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Notification;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.model.Treatment;
import fr.pantheonsorbonne.ufr27.miage.service.SoinService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/soin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SoinResource {

    @Inject
    SoinService soinService;

    //REVOIR QUE LES ATTRIBUTS MAJ BIEN (EX: HYDRATER) MAIS HUNGRY MARCHE !

    @POST
    @Path("/feed/{id}")
    public Response feedTamagotchi(@PathParam("id") Integer tamagotchiId, @QueryParam("points") @DefaultValue("10") Integer points) {
        try {
            System.out.println("ID RESSOURCE EST : "+tamagotchiId);
            soinService.feedTamagotchi(tamagotchiId, points);
            return Response.ok("Tamagotchi nourri avec succès").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/hydrate/{id}")
    public Response hydrateTamagotchi(@PathParam("id") Integer tamagotchiId, @QueryParam("points") @DefaultValue("10") Integer points) {
        try {
            soinService.hydrateTamagotchi(tamagotchiId, points);
            return Response.ok("Tamagotchi hydraté avec succès").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/energize/{id}")
    public Response energizeTamagotchi(@PathParam("id") Integer tamagotchiId, @QueryParam("points") @DefaultValue("10") Integer points) {
        System.out.println(tamagotchiId);
        try {
            soinService.energizeTamagotchi(tamagotchiId, points);
            return Response.ok("Tamagotchi énergisé avec succès").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /*@GET
    @Path("/{id}")
    public Response getTamagotchiInfo(@PathParam("id") Integer tamagotchiId) {
        Tamagotchi tamagotchi = soinService.getTamagotchiById(tamagotchiId);
        if (tamagotchi == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Tamagotchi non trouvé").build();
        }
        return Response.ok(tamagotchi).build();
    }*/

    @GET
    @Path("/notifications/{ownerId}")
    public Response getNotificationsByOwner(@PathParam("ownerId") Integer ownerId) {
        List<Notification> notifications = soinService.getNotificationsAndMarkAsRead(ownerId);
        if(notifications.isEmpty()) {
            return Response.status(Response.Status.OK).entity("Vous n'avez pas de notifications pour le moment").build();
        }
        return Response.ok(notifications).build();
    }

    @POST
    @Path("/treatments/treat")
    public Response getTreatmentsForTamagotchi(@QueryParam("tamagotchiId") Integer tamagotchiId) {
        /*Tamagotchi tamagotchi = soinService.getTamagotchiById(tamagotchiId);
        if (tamagotchi == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Tamagotchi non trouvé").build();
        }

        List<Treatment> treatments = soinService.getTreatmentsForDisease(tamagotchi.getDisease());
        if (treatments.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Aucun traitement disponible pour la maladie : " + tamagotchi.getDisease()).build();
        }

        return Response.ok(treatments).build();*/
        try{
            Tamagotchi tamagotchi = soinService.getTamagotchiById(tamagotchiId);
            List<Treatment> treatments = soinService.getTreatmentsForDisease(tamagotchi.getDisease());
            return Response.ok(treatments).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }


    @POST
    @Path("/treatments/apply")
    public Response applyTreatment(@QueryParam("tamagotchiId") Integer tamagotchiId,
                                   @QueryParam("treatmentId") Integer treatmentId) {
        try {
            soinService.applyTreatmentToTamagotchi(tamagotchiId, treatmentId);
            return Response.ok("Traitement appliqué avec succès").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

