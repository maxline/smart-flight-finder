package com.seleon.flightscanner.wizzair;

import com.seleon.flightscanner.ryanair.enums.ArrivalCategory;
import com.seleon.flightscanner.nearbyairports.IataCode;
import com.seleon.flightscanner.ryanair.enums.SearchMode;
import com.seleon.flightscanner.ryanair.enums.SortBy;
import com.seleon.flightscanner.utils.JsonUtil;
import com.seleon.flightscanner.wizzair.dto.WizzAirResponse;
import com.seleon.flightscanner.wizzair.dto.WizzAirFlightItem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class WizzAirFareSearch {

    private static final SearchMode SEARCH_MODE = SearchMode.ROUND; // SearchMode.ROUND; SearchMode.ONE_WAY;
    private static final ArrivalCategory TYPE_OF_TRIP = ArrivalCategory.BEACH;  //BEACH: CITY_BREAK; OUTDOOR
    private static final SortBy SORT_BY = SortBy.PRICE; // SortBy.DATE_FROM, SortBy.PRICE, SortBy.DAY_OF_WEEK
    private static final Integer PRICE_VALUE_TO = 600;
    private static final String CURRENCY = "PLN";

    private static final String DATE_FROM = "2026-04-01";
    private static final String DATE_TO = "2026-04-30";
    private static final String DEPARTURE_TIME_FROM = "05:45";
    private static final String DEPARTURE_TIME_TO = "13:00";
    private static final String DAYS_OF_WEEK = "FRIDAY,SATURDAY,SUNDAY";

    private static final Integer DURATION_FROM = 1;
    private static final Integer DURATION_TO = 4;
    private static final String INBOUND_DEPARTURE_TIME_FROM = "16:00";
    private static final String INBOUND_DEPARTURE_TIME_TO = "23:59";

    private static String urlCreator(WizzSearchParameters searchParameters) {

//        String payloadMarchFlexible = "{\"arrivalStations\":null," +
//                "\"departureStations\":[\"WRO\"]," +
//                "\"tripDuration\":\"anytime\"," +
//                "\"isReturnFlight\":false," +
//                "\"stdPlan\":[\"2026-03\"]," +
//                "\"pax\":1," +
//                "\"dateFilterType\":\"Flexible\"," +
//                "\"departureDate\":null," +
//                "\"returnDate\":null}";
//
//
//        String payloadWeekend = "{\"arrivalStations\":null," +
//                "\"departureStations\":[\"WRO\"]," +
//                "\"tripDuration\":\"weekend\"," +
//                "\"isReturnFlight\":true," +
//                "\"stdPlan\":null," +
//                "\"pax\":1," +
//                "\"dateFilterType\":\"Flexible\"," +
//                "\"departureDate\":null," +
//                "\"returnDate\":null}";
//
//
//        String payload1Week = "{\"arrivalStations\":null," +
//                "\"departureStations\":[\"WRO\"]," +
//                "\"tripDuration\":\"1 week\"," +
//                "\"isReturnFlight\":true," +
//                "\"stdPlan\":[\"2026-04\",\"2026-05\"]," +
//                "\"pax\":1," +
//                "\"dateFilterType\":\"Flexible\"," +
//                "\"departureDate\":null," +
//                "\"returnDate\":null}";
//
//        String payloadS1_3Days = "{\"arrivalStations\":null," +
//                "\"departureStations\":[\"WRO\"]," +
//                "\"tripDuration\":\"1-3 days\"," +
//                "\"isReturnFlight\":true," +
//                "\"stdPlan\":null," +
//                "\"pax\":1," +
//                "\"dateFilterType\":\"Flexible\"," +
//                "\"departureDate\":null," +
//                "\"returnDate\":null}";
//
//        String payloadReturn = "{\"arrivalStations\":null,\"departureStations\":[\"WRO\"]" +
//                ",\"tripDuration\":\"anytime\"" +
//                ",\"isReturnFlight\":true" +
//                ",\"stdPlan\":null,\"pax\":1" +
//                ",\"dateFilterType\":\"Exact\"" +
//                ",\"departureDate\":\"2026-03-05\"" +
//                ",\"returnDate\":\"2026-03-19\"}";


        String url = "https://be.wizzair.com/28.0.0/Api/search/SmartSearchCheapFlightsV2";


        String curl_example_is_return_false = "curl 'https://be.wizzair.com/28.0.0/Api/search/SmartSearchCheapFlightsV2' \\\n" +
                "  -H 'accept: application/json, text/plain, */*' \\\n" +
                "  -H 'accept-language: en-US,en;q=0.9' \\\n" +
                "  -H 'content-type: application/json' \\\n" +
                "  -b 'ASP.NET_SessionId=r4lnc5b4xofyynr11vmbvrw2; RequestVerificationToken=e4316d82212d490cb488fa967ed089c9; configCatBeId=16-4kXuWKBMqr2cj0xD_x' \\\n" +
                "  -H 'origin: https://www.wizzair.com' \\\n" +
                "  -H 'priority: u=1, i' \\\n" +
                "  -H 'referer: https://www.wizzair.com/en-gb/flights/fare-finder/wroclaw/anywhere/0/0/0/1/0/0/2026-03-02/2026-03-31?flexible=anytime&duration=1_week' \\\n" +
                "  -H 'sec-ch-ua: \"Not:A-Brand\";v=\"99\", \"Google Chrome\";v=\"145\", \"Chromium\";v=\"145\"' \\\n" +
                "  -H 'sec-ch-ua-mobile: ?0' \\\n" +
                "  -H 'sec-ch-ua-platform: \"macOS\"' \\\n" +
                "  -H 'sec-fetch-dest: empty' \\\n" +
                "  -H 'sec-fetch-mode: cors' \\\n" +
                "  -H 'sec-fetch-site: same-site' \\\n" +
                "  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36' \\\n" +
                "  -H 'x-kpsdk-cd: {\"workTime\":1772355181764,\"id\":\"e9bc0edbac6ee7d1ea2b8942bbee0459\",\"answers\":[6,14],\"duration\":39,\"d\":5,\"st\":1772355111334,\"rst\":1772355111339}' \\\n" +
                "  -H 'x-kpsdk-ct: 0GVoov7HwhRIr0wb6zYGO4bcVfmc8vA2htvy7k3lFqTDSC1DFPOH44pCgG2Fd1srO4pV5dZjCI9lPXnltoee9WvLp5XfTFDKcWZXVhnuFu7msdGOlHnd4MUQyDfCV1C9ReS94yUOBNfDpu27XmOdTUYENvfOtHEzmjzwbOenW0S6' \\\n" +
                "  -H 'x-kpsdk-h: 01dw2KwktB0jbT2xQUeHAW77BjdP0=' \\\n" +
                "  -H 'x-kpsdk-v: j-1.2.205' \\\n" +
                "  -H 'x-requestverificationtoken: e4316d82212d490cb488fa967ed089c9' \\\n" +
                "  --data-raw '{\"arrivalStations\":null,\"departureStations\":[\"WRO\"],\"tripDuration\":\"anytime\",\"isReturnFlight\":false,\"stdPlan\":[\"2026-03\"],\"pax\":1,\"dateFilterType\":\"Flexible\",\"departureDate\":null,\"returnDate\":null}'";


        String curl_example_is_return_true = "curl 'https://be.wizzair.com/28.0.0/Api/search/SmartSearchCheapFlightsV2' \\\n" +
                "  -H 'accept: application/json, text/plain, */*' \\\n" +
                "  -H 'accept-language: en-US,en;q=0.9' \\\n" +
                "  -H 'content-type: application/json' \\\n" +
                "  -b 'configCatBeId=16-4kXuWKBMqr2cj0xD_x; RequestVerificationToken=08ebf4194cec409c86f96fac86208040' \\\n" +
                "  -H 'origin: https://www.wizzair.com' \\\n" +
                "  -H 'priority: u=1, i' \\\n" +
                "  -H 'referer: https://www.wizzair.com/en-gb/flights/fare-finder/wroclaw/anywhere/0/0/0/1/0/0/2026-03-02/2026-03-31?flexible=anytime&duration=1_week' \\\n" +
                "  -H 'sec-ch-ua: \"Not:A-Brand\";v=\"99\", \"Google Chrome\";v=\"145\", \"Chromium\";v=\"145\"' \\\n" +
                "  -H 'sec-ch-ua-mobile: ?0' \\\n" +
                "  -H 'sec-ch-ua-platform: \"macOS\"' \\\n" +
                "  -H 'sec-fetch-dest: empty' \\\n" +
                "  -H 'sec-fetch-mode: cors' \\\n" +
                "  -H 'sec-fetch-site: same-site' \\\n" +
                "  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36' \\\n" +
                "  -H 'x-kpsdk-cd: {\"workTime\":1772357699505,\"id\":\"88fad8ab8f98845079167c49329aeda3\",\"answers\":[25,2],\"duration\":53.2,\"d\":5,\"st\":1772355111334,\"rst\":1772355111339}' \\\n" +
                "  -H 'x-kpsdk-ct: 0GVoov7HwhRIr0wb6zYGO4bcVfmc8vA2htvy7k3lFqTDSC1DFPOH44pCgG2Fd1srO4pV5dZjCI9lPXnltoee9WvLp5XfTFDKcWZXVhnuFu7msdGOlHnd4MUQyDfCV1C9ReS94yUOBNfDpu27XmOdTUYENvfOtHEzmjzwbOenW0S6' \\\n" +
                "  -H 'x-kpsdk-h: 01dw2KwktB0jbT2xQUeHAW77BjdP0=' \\\n" +
                "  -H 'x-kpsdk-v: j-1.2.205' \\\n" +
                "  -H 'x-requestverificationtoken: 08ebf4194cec409c86f96fac86208040' \\\n" +
                "  --data-raw '{\"arrivalStations\":null,\"departureStations\":[\"WRO\"],\"tripDuration\":\"anytime\",\"isReturnFlight\":true,\"stdPlan\":null,\"pax\":1,\"dateFilterType\":\"Exact\",\"departureDate\":\"2026-03-05\",\"returnDate\":\"2026-03-19\"}'";


        return url;
    }

    public static void main(String[] args) {
        try {
            //fareFind(IataCode.WAW);
            //fareFind(IataCode.KRK);
            //fareFind(IataCode.VIE);
            fareFind(IataCode.WRO);
            //fareFind(IataCode.KTW);
            //fareFind(IataCode.POZ);
            //fareFind(IataCode.RZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String makeWizzAirApiCall(String jsonPayload) throws URISyntaxException, IOException, InterruptedException {
        String url = "https://be.wizzair.com/28.0.0/Api/search/SmartSearchCheapFlightsV2";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("accept", "application/json, text/plain, */*")
                .header("accept-language", "en-US,en;q=0.9")
                .header("content-type", "application/json")
                .header("origin", "https://www.wizzair.com")
                .header("referer", "https://www.wizzair.com/en-gb/flights/fare-finder")
                .header("sec-ch-ua", "\"Not:A-Brand\";v=\"99\", \"Google Chrome\";v=\"145\", \"Chromium\";v=\"145\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"macOS\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-site")
                .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static void fareFind(IataCode airportFrom) throws URISyntaxException, IOException, InterruptedException {

        WizzSearchParameters searchParameters = WizzSearchParameters.builder()
                .stdPlan(List.of("2026-04", "2026-05"))
                .dateFilterType(DateFilterType.FLEXIBLE)
                .departureStations(List.of(airportFrom.name()))
                .build();

        String faresUrl = urlCreator(searchParameters);

        // Example payloads from curl examples:
        // isReturnFlight = false (one-way):
        String examplePayloadOneWay = "{\"arrivalStations\":null,\"departureStations\":[\"WRO\"],\"tripDuration\":\"anytime\",\"isReturnFlight\":false,\"stdPlan\":[\"2026-03\"],\"pax\":1,\"dateFilterType\":\"Flexible\",\"departureDate\":null,\"returnDate\":null}";

        // isReturnFlight = true (round-trip):
        String examplePayloadRoundTrip = "{\"arrivalStations\":null,\"departureStations\":[\"WRO\"],\"tripDuration\":\"anytime\",\"isReturnFlight\":true,\"stdPlan\":null,\"pax\":1,\"dateFilterType\":\"Exact\",\"departureDate\":\"2026-03-05\",\"returnDate\":\"2026-03-19\"}";

        // Make the API call with the payload
        String responseBody = makeWizzAirApiCall(examplePayloadOneWay);

        //System.out.println("Response: " + responseBody);

        // Convert the response body to DTO classes
        WizzAirResponse wizzAirResponse = JsonUtil.jsonToObject(responseBody, WizzAirResponse.class);

        // Process the response
        if (wizzAirResponse != null && wizzAirResponse.getItems() != null) {
            System.out.println("\nFound " + wizzAirResponse.getItems().size() + " flights");

            // Example: Print first few flights
            for (int i = 0; i < wizzAirResponse.getItems().size(); i++) {
                WizzAirFlightItem item = wizzAirResponse.getItems().get(i);
                System.out.println("\nFlight " + (i + 1) + ":");
                String arrivalCityName = "";
                try {
                    arrivalCityName = IataCode.valueOf(item.getArrivalStation()).getCity();
                } catch (IllegalArgumentException e) {
                    System.out.println("!!!!! " + item.getArrivalStation());
                }
                System.out.println("  Route: " + item.getDepartureStation() + " -> " + item.getArrivalStation() + " (" + arrivalCityName + ")");
                System.out.println("  Departure: " + item.getStd());
                System.out.println("  Duration: " + item.getFlightDurationMinutes() + " minutes");
                System.out.println("  Regular Price: " + item.getRegularPrice().getAmount() + " " + item.getRegularPrice().getCurrencyCode());
                System.out.println("  WDC Price: " + item.getWdcPrice().getAmount() + " " + item.getWdcPrice().getCurrencyCode());
            }
        }

//        if (response != null) {
//            System.out.println("Date from: " + DATE_FROM + ", Date To: " + DATE_TO);
//
//            //System.out.println(searchParameters);
//            //System.out.println("RyanAir fares api from " + AIRPORT_FROM + " " + SEARCH_MODE.getValue() + " call was executed. Response details:");
//            //System.out.println(response.body());
//
//            final String fileToSave = "wizz_air_" + airportFrom.name() + "_" + SEARCH_MODE + "_003.json";
//            FilesUtil.save(response.body(),FilesUtil.PATH_RYAN, fileToSave);
//            printFoundFlights(response.body(), airportFrom);
//
//        }
    }

//    private static void printFoundFlights(String flightDataJson, IataCode airportFrom) {
//        System.out.println("\nDate from: " + DATE_FROM + ", Date To: " + DATE_TO);
//        System.out.println(airportFrom + " " + SEARCH_MODE.getValue() + " Response details:");
//        System.out.println("Sorted by: " + SORT_BY);
//
//        FlightData flightData = JsonUtil.jsonToObject(flightDataJson, FlightData.class);
//        List<String> fareLites = new FaresProcessorImpl().extractFareLites(flightData, SORT_BY);
//
//        System.out.println(JsonUtil.listToNewLineString(fareLites));
//        System.out.println(fareLites.size());
//    }
}
