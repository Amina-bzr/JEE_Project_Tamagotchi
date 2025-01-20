package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Account;
import fr.pantheonsorbonne.ufr27.miage.model.Transaction;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("/bancaire")
public class BankingResources {
    @Inject
    BankingService bankingService;

    //transfer money endpoint
    @Path("/{tamagotchiId}/transfer/{tamagotchiIdDest}/{amount}")
    @POST
    public Response transfer(
            @PathParam("tamagotchiId") Integer tamagotchiId,
            @PathParam("tamagotchiIdDest") Integer tamagotchiIdDest,
            @PathParam("amount") double amount) {

        try {

            Account fromAccount = bankingService.getAccountByTamagotchi(tamagotchiId);
            Account toAccount = bankingService.getAccountByTamagotchi(tamagotchiIdDest);

            bankingService.transfer(fromAccount.getId(), toAccount.getId(), amount);

            String responseMessage = "Transfer of " + amount + " from Tamagotchi " + tamagotchiId +
                    " to Tamagotchi " + tamagotchiIdDest + " was successful.";
            return Response.status(Response.Status.OK)
                    .entity(responseMessage)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error during transfer: " + e.getMessage())
                    .build();
        }
    }

    //solde du compte
    @Path("/{tamagotchiId}/balance")
    @GET
    public Response getTamagotchiAmount(@PathParam("tamagotchiId") Integer tamagotchiId) {

        try {
            Account account = bankingService.getAccountByTamagotchi(tamagotchiId);
            double balance = account.getBalance();

            return Response.ok("The balance for Tamagotchi " + tamagotchiId + " is " + balance).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error fetching balance: " + e.getMessage())
                    .build();
        }
    }


}


