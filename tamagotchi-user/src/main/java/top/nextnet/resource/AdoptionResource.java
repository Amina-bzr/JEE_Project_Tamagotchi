package top.nextnet.resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;




@Path("/adoption")
@RegisterRestClient(configKey = "user-api")
public interface AdoptionResource {

    @POST
    @Path("/{idOwner}/create/{name}")
    public void createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name);

}
