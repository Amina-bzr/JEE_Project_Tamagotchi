package top.nextnet.resource;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;



@Path("/adoption")
@RegisterRestClient(configKey = "user-api")
public interface AdoptionResource {

    @POST
    @Path("/{idOwner}/create/{name}")
    public void createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name);

    /*
    @Path(("/tamagotchis/abandoned"))
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Donation> getDonations() {
        return this.donationService.getDonationService();
    }
    */

/*
    @Path("/{idOwner}/tamagotchis/create/{name}")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createTamagotchi( @PathParam("idOwner") Integer idOwner, @PathParam("name") String name) {
        this.adoptionService.createTamagotchi(idOwner, name);
    }
*/
}
