package perform;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import domain.Wedstrijd;

public class PerformRestExample {

    private final String SERVER_URI = "http://localhost:8080/rest";
    private WebClient webClient = WebClient.create();

    public PerformRestExample() throws Exception {
        String sport = "Tennis";

        System.out.println("\n------- GET WEDSTRIJDEN BY: " + sport + "-------");
        getAllWedstrijd(sport);

        int idWedstrijd = 2;
        System.out.println("\n------- GET BESCHIKBAAREPLAATSEN BY IDWEDSTRIJD: " + idWedstrijd + "-------");
        getPlaatsenByIdWedstrijd(idWedstrijd);
    }

    private void getAllWedstrijd(String sport) {
		webClient.get().uri(SERVER_URI + "/wedstrijdenBySport/" + sport).retrieve()
        .bodyToFlux(Wedstrijd.class)
        .flatMap(wed -> {
            printWedData(wed);
            return Mono.empty();
        })
        .blockLast();
	}

    private void getPlaatsenByIdWedstrijd(int idWedstrijd) {
        webClient.get().uri(SERVER_URI + "/AantalPlaatsenByWedstrijdId/" + idWedstrijd).retrieve()
            .bodyToMono(Integer.class)
            .doOnNext(this::printAantalPlaatsen)
            .block();
    }

    private void printWedData(Wedstrijd wed) {
        System.out.printf(wed.toString() + "\n");
    }

    private void printAantalPlaatsen(int aantal) {
        System.out.printf("Aantal beschikbare plaatsen: " + aantal + "\n");
    }

}
