package cz.uhk.boardsappjakarta.controller.index;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("")
public class Controller {

    @GET
    @Produces("text/html")
    public String index() {
        return  """
                <html>
                    <header><title>Boards</title></header>
                    
                    <body>
                        Jakarta EE variant of Boards application.
                    </body>
                </html>
                """;
    }

}