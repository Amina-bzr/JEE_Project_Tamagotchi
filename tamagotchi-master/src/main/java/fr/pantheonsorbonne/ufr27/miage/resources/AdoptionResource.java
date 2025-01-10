package fr.pantheonsorbonne.ufr27.miage.resources;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;

import java.util.Collection;

@Path("/adoption")
public class AdoptionResource {

    @Inject
    AdoptionService adoptionService;

    @Inject
    BankingService bankingService;

    //create tamagotchi endpoint
    @Path("/{idOwner}/create/{name}")
    @POST
    public Response createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name) {
        System.out.println("name is " + name);
        Tamagotchi newTamagotchi = this.adoptionService.addTamagotchiService(name, idOwner);
        this.bankingService.createAccount(newTamagotchi.getIdTamagotchi());
        String responseMessage = "Tamagotchi created successfully! " + newTamagotchi.toString();
        return Response.status(Response.Status.CREATED)
                .entity(responseMessage)
                .build();
    }

    @Path("/{idOwner}/adopt/{idTamagotchi}")
    @PUT
    public Response createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("idTamagotchi") Integer idTamagotchi) {
        System.out.println("owner is " + idOwner + " Tamagotchi " + idTamagotchi);

        //update the owner of the tamagotchi
        Tamagotchi updatedTamagotchi = this.adoptionService.updateTamagotchiOwner(idTamagotchi, idOwner);

        //construct a response message
        String responseMessage = "Tamagotchi created successfully! " + updatedTamagotchi.toString();

        return Response.status(Response.Status.OK)
                .entity(responseMessage) // Returning plain text message
                .build();
    }


    @Path("/tamagotchis")
    @GET
    public Response getTamagotchis(@QueryParam("hasOwner") Boolean hasOwner) {
        Collection<Tamagotchi> tamagotchis = this.adoptionService.getTamagotchis(hasOwner);
        return Response.ok(tamagotchis).build();
    }




}

