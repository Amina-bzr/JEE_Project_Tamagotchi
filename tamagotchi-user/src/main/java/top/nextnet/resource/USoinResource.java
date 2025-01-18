package top.nextnet.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/soin")
@RegisterRestClient(configKey = "user-api")
public interface USoinResource {

    @POST
    @Path("/feed/{id}")
    public Response feedTamagotchi(@PathParam("id") Integer tamagotchiId, @QueryParam("points") @DefaultValue("10") Integer points);

    @POST
    @Path("/hydrate/{id}")
    public Response hydrateTamagotchi(@PathParam("id") Integer tamagotchiId, @QueryParam("points") @DefaultValue("10") Integer points);

    @POST
    @Path("/energize/{id}")
    public Response energizeTamagotchi(@PathParam("id") Integer tamagotchiId, @QueryParam("points") @DefaultValue("10") Integer points);

    @GET
    @Path("/{id}")
    public Response getTamagotchiInfo(@PathParam("id") Integer tamagotchiId);

    @GET
    @Path("/notifications/{ownerId}")
    public Response getNotificationsByOwner(@PathParam("ownerId") Integer ownerId);

    @POST
    @Path("/treatments/treat")
    public Response getTreatmentsForTamagotchi(@QueryParam("tamagotchiId") Integer tamagotchiId);

    @POST
    @Path("/treatments/apply")
    public Response applyTreatment(@QueryParam("tamagotchiId") Integer tamagotchiId, @QueryParam("treatmentId") Integer treatmentId);

}









