package fr.pantheonsorbonne.ufr27.miage.resources;
import fr.pantheonsorbonne.ufr27.miage.camel.gateways.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertTypes;
import fr.pantheonsorbonne.ufr27.miage.exception.OwnerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiHasOwner;
import fr.pantheonsorbonne.ufr27.miage.exception.TamagotchiNotFoundException;
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

    @Inject
    AdoptionGateway adoptionGateway;


    @Path("/{idOwner}/create/{name}")
    @POST
    public Response createTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("name") String name) {
        String responseMessage;
        Response.Status status;
        System.out.println("name is " + name);
        try  {
            Tamagotchi newTamagotchi = this.adoptionService.addTamagotchiService(name, idOwner);
            System.out.println("ATTEMPTING to create account for tamagotchi with id : " + newTamagotchi.getIdTamagotchi());
            this.bankingService.createAccount(newTamagotchi.getIdTamagotchi());
            responseMessage = "Tamagotchi created successfully! " + newTamagotchi.toString();
            //request gift from fairy
            adoptionGateway.sendAdoptionAlert(new AlertDTO(newTamagotchi.getIdTamagotchi(), AlertTypes.CREATED));
            status = Response.Status.CREATED;
        } catch (OwnerNotFoundException e) {
            responseMessage = "Owner not found! please check the ownerId provided in the path";
            status = Response.Status.NOT_FOUND;
        }
        return Response.status(status)
                .entity(responseMessage)
                .build();
    }

    @Path("/{idOwner}/adopt/{idTamagotchi}")
    @PUT
    public Response adoptTamagotchi(@PathParam("idOwner") Integer idOwner, @PathParam("idTamagotchi") Integer idTamagotchi) {
        Response.Status status;
        System.out.println("owner is " + idOwner + " Tamagotchi " + idTamagotchi);
        String responseMessage;
        try {
            Tamagotchi updatedTamagotchi = this.adoptionService.updateTamagotchiOwner(idTamagotchi, idOwner);
            adoptionGateway.sendAdoptionAlert(new AlertDTO(updatedTamagotchi.getIdTamagotchi(), AlertTypes.ADOPTED));
            responseMessage = "Tamagotchi adopted successfully! " + updatedTamagotchi.toString();
            status = Response.Status.OK;
        } catch (TamagotchiNotFoundException t) {
            responseMessage = "Tamagotchi with id "+idTamagotchi+" doesn't exist";
            status = Response.Status.NOT_FOUND;
        } catch (TamagotchiHasOwner o) {
            responseMessage = "Tamagotchi with id "+idTamagotchi+" already has an owner";
            status = Response.Status.CONFLICT;
        }
        return Response.status(status)
                .entity(responseMessage)
                .build();
    }


    @Path("/tamagotchis")
    @GET
    public Response getTamagotchis(@QueryParam("hasOwner") Boolean hasOwner) {
        Collection<Tamagotchi> tamagotchis = this.adoptionService.getTamagotchis(hasOwner);
        return Response.ok(tamagotchis).build();
    }




}

