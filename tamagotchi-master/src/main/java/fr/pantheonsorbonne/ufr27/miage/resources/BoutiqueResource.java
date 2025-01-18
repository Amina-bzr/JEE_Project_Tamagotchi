package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.gateways.BoutiqueGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import fr.pantheonsorbonne.ufr27.miage.service.InventoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/boutique")
public class BoutiqueResource {
    @Inject
    BoutiqueGateway boutiqueGateway;

    @Inject
    InventoryService inventoryService;

    //create tamagotchi endpoint
    @Path("/{idTamagotchi}/achat/{productId}")
    @POST
    public Response purchaseProduct(@PathParam("idTamagotchi") Integer idTamagotchi, @PathParam("productId") Integer productId) {
        ProductDTO product = new ProductDTO(productId, idTamagotchi);
        String responseMessage;
        System.out.println("ATTEMPTING to buy product : " + productId);

        if (inventoryService.productInInventory(productId, idTamagotchi)) {
            responseMessage = "Product already purchased.";
        } else {
            responseMessage = this.boutiqueGateway.purchaseProduct(product);
        }

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
