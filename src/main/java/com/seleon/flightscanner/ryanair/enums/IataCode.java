package com.seleon.flightscanner.ryanair.enums;

import lombok.Getter;

@Getter
public enum IataCode {
    // 🇬🇷 Greece (Mainland + Islands)
    AOK("Karpathos", "35.42-27.15"),
    ATH("Athens", "37.94-23.94"),
    CFU("Corfu", "39.61-19.91"),
    CHQ("Chania Crete", "35.53-24.15"),
    HER("Heraklion Crete", "35.34-25.18"),
    JIK("Ikaria", "37.66-25.37"),
    JMK("Mykonos", "37.43-25.33"),
    JNX("Naxos", "37.1-25.37"),
    JTR("Santorini", "36.4-25.48"),
    KGS("Kos", "36.79-27.09"),
    KVA("Kavala", "40.93-24.41"),
    LXS("Lemnos", "39.91-25.24"),
    MJT("Lesbos (Mytilene)", "39.06-26.6"),
    RHO("Rhodes", "36.4-28.09"),
    SMI("Samos", "37.68-26.91"),
    SKU("Skiathos", "39.15-23.5"),
    SKG("Thessaloniki", "40.52-22.99"),
    ZTH("Zakynthos", "37.75-20.89"),



    // 🇪🇸 Spain
    ALC("Alicante", "38.29--0.56"),
    AGP("Malaga", "36.67--4.5"),
    BCN("Barcelona", "41.3-2.08"),
    BIO("Bilbao", "43.3--2.91"),
    IBZ("Ibiza", "38.88-1.41"),
    LPA("Las Palmas", "27.93--15.39"),
    PMI("Palma de Mallorca", "39.55-2.73"),
    SCQ("Santiago de Compostela", "42.9--8.41"),
    TFS("Tenerife South", "28.04--16.59"),
    VLC("Valencia", "39.49--0.48"),

    // 🇮🇹 Italy
    CAG("Cagliari", "39.25-9.06"),
    CTA("Catania", "37.47-15.06"),
    CIA("Rome Ciampino", "41.80-12.59"),
    FLR("Florence", "43.81-11.20"),
    FCO("Rome Fiumicino", "41.80-12.24"),
    MXP("Milan Malpensa", "45.63-8.72"),
    NAP("Naples", "40.88-14.29"),
    OLB("Olbia", "40.90-9.52"),
    PMO("Palermo", "38.18-13.09"),
    PSA("Pisa", "43.69-10.40"),
    VCE("Venice", "45.50-12.35"),

    // 🇮🇹 Italy (additional)
    PSR("Pescara", "42.43-14.19"),
    BRI("Bari", "41.14-16.76"),
    AOI("Ancona", "43.62-13.36"),
    RMI("Rimini", "44.02-12.61"),


    // 🇭🇷 Croatia
    DBV("Dubrovnik", "42.56-18.26"),
    SPU("Split", "43.54-16.30"),
    ZAD("Zadar", "44.10-15.35"),
    PUY("Pula", "44.89-13.92"),
    RJK("Rijeka", "45.22-14.57"),

    // 🇵🇹 Portugal
    FAO("Faro", "37.02-7.97"),
    FNC("Funchal (Madeira)", "32.69-16.77"),
    LIS("Lisbon", "38.77-9.13"),
    OPO("Porto", "41.24-8.68"),
    PDL("Ponta Delgada (Azores)", "37.74-25.70"),

    // 🇨🇾 Cyprus
    ECN("Ercan *", "35.15-33.50"),
    LCA("Larnaca", "34.88-33.63"),
    PFO("Paphos", "34.72-32.49"),

    //🇦🇱 Albania
    TIA("Tirana", "41.41-19.72"),

    // 🇲🇪 Montenegro
    TGD("Podgorica", "42.36-19.25"),
    TIV("Tivat", "42.41-18.72"),

    // 🇧🇬 Bulgaria
    BOJ("Burgas", "42.57-27.52"),
    PDV("Plovdiv", "42.07-24.85"),
    SOF("Sofia", "42.69-23.41"),
    VAR("Varna", "43.23-27.83"),

    // 🇩🇪 Germany
    BER("Berlin Brandenburg", "52.37-13.51"),
    CGN("Cologne", "50.87-7.14"),
    DUS("Düsseldorf", "51.29-6.77"),
    FRA("Frankfurt", "50.04-8.57"),
    HAM("Hamburg", "53.63-9.99"),
    MUC("Munich", "48.35-11.78"),
    NUE("Nuremberg", "49.50-11.08"),
    STR("Stuttgart", "48.69-9.22"),

    // 🇵🇱 Poland
    GDN("Gdansk", "54.38-18.47"),
    KTW("Katowice", "50.47-19.08"),
    KRK("Krakow", "50.08-19.78"),
    LUZ("Lublin", "51.24-22.70"),
    POZ("Poznan", "52.42-16.83"),
    RZE("Rzeszow", "50.11-22.01"),
    SZZ("Szczecin", "53.58-14.90"),
    WAW("Warsaw Chopin", "52.17-20.97"),
    WMI("Warsaw Modlin", "52.45-20.65"),
    WRO("Wroclaw", "51.10-16.88"),

    // 🇦🇹 Austria
    GRZ("Graz", "47.00-15.44"),
    INN("Innsbruck", "47.26-11.35"),
    KLU("Klagenfurt", "46.64-14.34"),
    LNZ("Linz", "48.23-14.19"),
    SZG("Salzburg", "47.80-13.00"),
    VIE("Vienna", "48.11-16.57");

    private final String city;
    private final String coords;


    IataCode(String city, String coords) {
        this.city = city;
        this.coords = coords;
    }

    public String getCity() {
        return city;
    }

    public String getCoords() {
        return coords;
    }


    @Override
    public String toString() {
        return "{" + name() +
                ", " + city  +
                ", " + coords +
                '}';
    }
}