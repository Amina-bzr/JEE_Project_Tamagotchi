package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.AdoptionGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.BoutiqueGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.AlertTypes;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Tamagotchi;
import fr.pantheonsorbonne.ufr27.miage.service.AdoptionService;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import fr.pantheonsorbonne.ufr27.miage.service.InventoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.List;

@Path("/boutique")
public class BoutiqueResource {
    @Inject
    BoutiqueGateway boutiqueGateway;

    //create tamagotchi endpoint
    @Path("/{idTamagotchi}/achat/{productId}")
    @POST
    public Response purchaseProduct(@PathParam("idTamagotchi") Integer idTamagotchi, @PathParam("productId") Integer productId) {
        ProductDTO product = new ProductDTO(idTamagotchi, productId);
        System.out.println("ATTEMPTING to buy product : " + productId);
        //ProductDTO purchasedProduct = this.boutiqueGateway.purchaseProduct(product);
        String responseMessage = "RESOURCE: Purchase request sent to boutique for product " + productId;
        //response
        return Response.status(Response.Status.OK)
                .entity(responseMessage)
                .build();
    }

    @Path("/products")
    @GET
    public Response getProducts(@QueryParam("category") String category) {
        List<ProductDTO> responseProducts = boutiqueGateway.getProducts(category);
        String responseMessage = "Getting products request sent to boutique.";
        return Response.status(Response.Status.OK)
                .entity(responseProducts)
                .build();
    }
}
