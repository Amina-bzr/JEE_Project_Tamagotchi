package fr.pantheonsorbonne.ufr27.miage.camel;



import fr.pantheonsorbonne.ufr27.miage.model.Account;
import fr.pantheonsorbonne.ufr27.miage.service.BankingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultComponent;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    CamelContext camelContext;

    @Inject
    BankingService bankingService;

    @Override
    public void configure() throws Exception {

        // Définir la configuration de base, ici on peut activer le traçage pour voir les échanges
        camelContext.setTracing(true);

        // Route pour créer un compte
        from("direct:createAccount") // Point d'entrée pour créer un compte (par exemple, depuis un service HTTP ou un message)
                .bean(bankingService, "createAccount(${header.userId}, ${header.tamagotchiId})")
                .log("Account created with ID: ${body.id}")
                .to("mock:createdAccount"); // Simulation de la sortie ou une autre destination

        // Route pour consulter un compte
        from("direct:getAccount") // Point d'entrée pour consulter un compte
                .bean(bankingService, "getAccount(${header.accountNumber})")
                .log("Account details: ${body}")
                .to("mock:accountDetails"); // Simulation de la sortie ou une autre destination

        // Route pour effectuer un dépôt sur un compte
        from("direct:deposit") // Point d'entrée pour déposer sur un compte
                .bean(bankingService, "deposit(${header.accountId}, ${header.amount})")
                .log("Deposit made to account ID: ${header.accountId}, amount: ${header.amount}")
                .to("mock:depositDone"); // Simulation de la sortie ou une autre destination

        // Route pour effectuer un retrait
        from("direct:withdraw") // Point d'entrée pour effectuer un retrait
                .bean(bankingService, "withdraw(${header.accountId}, ${header.amount})")
                .log("Withdrawal made from account ID: ${header.accountId}, amount: ${header.amount}")
                .to("mock:withdrawDone"); // Simulation de la sortie ou une autre destination

        // Route pour effectuer un virement
        from("direct:transfer") // Point d'entrée pour effectuer un virement
                .bean(bankingService, "transfer(${header.fromAccountId}, ${header.toAccountId}, ${header.amount})")
                .log("Transferred ${header.amount} from account ID: ${header.fromAccountId} to account ID: ${header.toAccountId}")
                .to("mock:transferDone"); // Simulation de la sortie ou une autre destination

        // Route pour supprimer un compte
        from("direct:deleteAccount") // Point d'entrée pour supprimer un compte
                .bean(bankingService, "deleteAccount(${header.accountNumber})")
                .log("Account with number ${header.accountNumber} deleted.")
                .to("mock:deleteDone"); // Simulation de la sortie ou une autre destination
    }
}

