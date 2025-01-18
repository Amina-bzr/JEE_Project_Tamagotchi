
package top.nextnet.resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Collection;


@Path("/banking")
@RegisterRestClient(configKey = "user-api")
public interface BankingResource {

    //transfer money endpoint
    @POST
    @Path("/{tamagotchiId}/transfer/{tamagotchiIdDest}/{amount}")
    public Response transfer(
            @PathParam("tamagotchiId") Integer tamagotchiId,
            @PathParam("tamagotchiIdDest") Integer tamagotchiIdDest,
            @PathParam("amount") double amount);

    //get balance
    @Path("/{tamagotchiId}/balance")
    @GET
    public Response getTamagotchiAmount(@PathParam("tamagotchiId") Integer tamagotchiId);


}




