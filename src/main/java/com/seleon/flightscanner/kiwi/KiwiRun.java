package com.seleon.flightscanner.kiwi;

import com.seleon.flightscanner.kiwi.dto.response.calendar.CalendarEntryLite;
import com.seleon.flightscanner.kiwi.dto.response.calendar.ItineraryCalendarResponse;
import com.seleon.flightscanner.nearbyairports.IataCode;
import com.seleon.flightscanner.utils.FilesUtil;
import com.seleon.flightscanner.utils.JsonUtil;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import static com.seleon.flightscanner.nearbyairports.IataCode.*;

public class KiwiRun {
    public static final String SEARCH_ONE_WAY_URL =  "https://api.skypicker.com/umbrella/v2/graphql?featureName=SearchOneWayItinerariesQuery";
    public static final String CALENDAR_MONTH_URL = "https://api.skypicker.com/umbrella/v2/graphql?featureName=CalendarMonthPricesFetcherQuery";


    //todo create a separate class/file with aeroports cities and coordinates, that automatically get coordinates from the city
    //todo combine the request that search flights from with request for back home in one scenario
    //todo add several from and destinations to the query
    public static final String COORDINATES_ALC = "38.35--0.49-250km"; //alicante
    public static final String COORDINATES_ROM = "41.9-12.5-250km"; //rome
    public static final String COORDINATES_PSR = "38.35--0.49-250km"; //psr

    public static final String CALENDAR_START = "2026-03-01T00:00:00";
    public static final String CALENDAR_END = "2026-03-13T23:59:59";

    public static final String DESTINATION_IDS = "City:wroclaw_pl";
    //public static final String DESTINATION_IDS = "City:krakow_pl";

    public static final String KM_250 = "-250km";


    String mmm = """
    {
  "source": {
    "ids": [
      "City:varna_bg",
      "City:burgas_bg",
      "City:sofia_bg"
    ]
  },
  "destination": {
    "ids": [
      "City:krakow_pl",
      "City:wroclaw_pl",
      "City:katowice_pl",
      "City:rzeszow_pl"
    ]
  },
  "dates": {
    "start": "2025-08-01T00:00:00",
    "end": "2025-08-31T23:59:59"
  },
  "passengers": {
    "adults": 1,
    "children": 0,
    "infants": 0,
    "adultsHoldBags": [
      0
    ],
    "adultsHandBags": [
      0
    ],
    "childrenHoldBags": [],
    "childrenHandBags": []
  },
  "cabinClass": {
    "cabinClass": "ECONOMY",
    "applyMixedClasses": false
  }
}
    
    
""";


    private String requestBody = """
                {
                  "query": "query SearchOneWayItinerariesQuery(\\n  
                  $search: SearchOnewayInput\\n  
                  $filter: ItinerariesFilterInput\\n  
                  $options: ItinerariesOptionsInput\\n  
                  $conditions: Boolean!\\n) {}"
                }
                """;

    public static void main(String[] args) throws Exception {
        //String fileName = "kiwi_payload_graphql_samle.txt";
        //String variables = FilesUtil.load(FilesUtil.PATH_KIWI, "variables1.json");

        String fileName = "CalendarMonthPricesFetcherQuery.json";
        String queryTemplate = FilesUtil.load(FilesUtil.PATH_KIWI, fileName);

        //IataCode source = SOF;
        IataCode source = PMI;
        String sourceRadius =  KM_250;
        String destinationIds = DESTINATION_IDS;
        String start = CALENDAR_START;
        String end = CALENDAR_END;

        HttpResponse<String> response = searchKiwiMonthlyCalendar(queryTemplate,
                source.getCoords() + sourceRadius,
                destinationIds,
                start, end);

        FilesUtil.save(response.body(), FilesUtil.PATH_KIWI, "kiwi_response.json");


        printResults(response, source.toString(), destinationIds, start, end);

    }

    private static HttpResponse<String> searchKiwiMonthlyCalendar(String queryTemplate, String sourceCoordinates,
                                                                  String destinationIds,
                                                                  String start, String end) throws IOException, InterruptedException {
        String query = queryTemplate
                .replace("{source_coordinates}",sourceCoordinates)
                .replace("{destination_ids}", destinationIds)
                .replace("{start}", start)
                .replace("{end}", end);

        HttpResponse<String> response = new KiwiGraphQLClient().callApi(CALENDAR_MONTH_URL, query);
        return response;
    }

    // todo move to separate class and create tests
    public static List<String> extractCalendarEntryLites(ItineraryCalendarResponse calendarResponse) {
        return calendarResponse.getData().getItineraryPricesCalendar().getCalendar().stream()
                .map(calendarEntry -> new CalendarEntryLite(calendarEntry))
                //.sorted(Comparator.comparing(FareLite::getOutboundDepartureDate))
                .map(CalendarEntryLite::toBrief)
                .collect(Collectors.toList());
    }

    private static void printResults(HttpResponse<String> response, String sourceCoordinates,
                                     String destinationIds,
                                     String start, String end) {

//        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-dd");
//
//        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
//        String flyAt = date.format(dateFormatter).toUpperCase() + " " + dayOfWeek;


        System.out.println("Search parameters: from: " + sourceCoordinates + ", to: " + destinationIds +
                ", start: " + start + ", end: " + end);
        String responseBody = response.body();

        System.out.println("Response code: " + response.statusCode());
        //System.out.println("Response body:\n" + responseBody);

        ItineraryCalendarResponse calendarResponse = JsonUtil.jsonToObject(responseBody, ItineraryCalendarResponse.class);

        System.out.println("Search parameters: source: " + sourceCoordinates + ", destination: " + destinationIds +
                ", start: " + start + ", end: " + end);
        List<String> calendarLites = extractCalendarEntryLites(calendarResponse);
        System.out.println(JsonUtil.listToNewLineString(calendarLites));
    }

}
