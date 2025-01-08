package fr.pantheonsorbonne.ufr27.miage.resources;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;

@Path("/adoption")
public class AdoptionResource {

    @Inject
    AdoptionService adoptionService;

    //create tamagotchi endpoint
    @Path("/{idOwner}/create/{name}")
    @POST
    public Response createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name) {
        this.adoptionService.addTamagotchiService(name, idOwner);
        return Response.status(Response.Status.CREATED).build(); //201 : CREATED SUCCES
    }

}

