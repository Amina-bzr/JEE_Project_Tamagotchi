package top.nextnet.resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Collection;


@Path("/adoption")
@RegisterRestClient(configKey = "user-api")
public interface AdoptionResource {

    @POST
    @Path("/{idOwner}/create/{name}")
    public void createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name);

    @Path("/{idOwner}/adopt/{idTamagotchi}")
    @PUT
    public Response createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("idTamagotchi") Integer idTamagotchi);

    @Path("/tamagotchis")
    @GET
    public Response getTamagotchis(@QueryParam("hasOwner") Boolean hasOwner);
}
