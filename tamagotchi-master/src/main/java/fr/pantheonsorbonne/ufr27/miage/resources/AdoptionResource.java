package fr.pantheonsorbonne.ufr27.miage.resources;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import fr.pantheonsorbonne.ufr27.miage.dto.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.dto.Owner;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;

import java.util.Collection;
import java.util.List;

@Path("/adoption")
public class AdoptionResource {

    @Inject
    AdoptionService adoptionService;

    /*
    @Path(("/tamagotchis/abandoned"))
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Donation> getDonations() {
        return this.donationService.getDonationService();
    }
    */

    //create tamagotchi endpoint
    @Path("/{idOwner}/create/{name}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name) {
        this.adoptionService.addTamagotchiService(name, idOwner);
        return Response.status(Response.Status.CREATED).build();
    }

}

