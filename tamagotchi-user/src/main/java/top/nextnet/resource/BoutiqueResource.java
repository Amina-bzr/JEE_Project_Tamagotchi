package top.nextnet.resource;

import fr.pantheonsorbonne.ufr27.miage.dto.ProductDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/boutique")
@RegisterRestClient(configKey = "user-api")
public interface BoutiqueResource {

        //create tamagotchi endpoint
        @POST
        @Path("/{idTamagotchi}/achat/{productId}")
        public Response purchaseProduct(@PathParam("idTamagotchi") Integer idTamagotchi, @PathParam("productId") Integer productId);

        @GET
        @Path("/products")
        public Response getProducts(@QueryParam("category") String category);

}
