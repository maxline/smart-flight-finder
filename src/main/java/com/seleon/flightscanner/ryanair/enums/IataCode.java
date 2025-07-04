package com.seleon.flightscanner.ryanair.enums;

public enum IataCode {
    // 🇬🇷 Greece (Mainland + Islands)
    AOK("Karpathos"),
    ATH("Athens"),
    CFU("Corfu"),
    CHQ("Chania, Crete"),
    HER("Heraklion, Crete"),
    JIK("Ikaria"),
    JMK("Mykonos"),
    JNX("Naxos"),
    JTR("Santorini"),
    KGS("Kos"),
    KVA("Kavala"),
    LXS("Lemnos"),
    MJT("Lesbos (Mytilene)"),
    RHO("Rhodes"),
    SMI("Samos"),
    SKU("Skiathos"),
    SKG("Thessaloniki"),
    ZTH("Zakynthos"),

    // 🇪🇸 Spain
    ALC("Alicante"),
    AGP("Malaga"),
    BCN("Barcelona"),
    BIO("Bilbao"),
    IBZ("Ibiza"),
    LPA("Las Palmas"),
    PMI("Palma de Mallorca"),
    SCQ("Santiago de Compostela"),
    TFS("Tenerife South"),
    VLC("Valencia"),

    // 🇮🇹 Italy
    CAG("Cagliari"),
    CTA("Catania"),
    CIA("Rome Ciampino"),
    FLR("Florence"),
    FCO("Rome Fiumicino"),
    MXP("Milan Malpensa"),
    NAP("Naples"),
    OLB("Olbia"),
    PMO("Palermo"),
    PSA("Pisa"),
    VCE("Venice"),

    // 🇭🇷 Croatia
    DBV("Dubrovnik"),
    SPU("Split"),
    ZAD("Zadar"),
    PUY("Pula"),
    RJK("Rijeka"),

    // 🇵🇹 Portugal
    FAO("Faro"),
    FNC("Funchal (Madeira)"),
    LIS("Lisbon"),
    OPO("Porto"),
    PDL("Ponta Delgada (Azores)"),

    // 🇨🇾 Cyprus
    ECN("Ercan *"),
    LCA("Larnaca"),
    PFO("Paphos"),

    //🇦🇱 Albania
    TIA("Tirana"),

    //🇲🇪 Montenegro
    TGD("Podgorica"),
    TIV("Tivat"),

    // Bulgaria
    BOJ("Burgas"),
    PDV("Plovdiv"),
    SOF("Sofia"),
    VAR("Varna"),

    // 🇩🇪 Germany
    BER("Berlin Brandenburg"),
    CGN("Cologne"),
    DUS("Düsseldorf"),
    FRA("Frankfurt"),
    HAM("Hamburg"),
    MUC("Munich"),
    NUE("Nuremberg"),
    STR("Stuttgart"),

    // 🇵🇱 Poland
    GDN("Gdansk"),
    KTW("Katowice"),
    KRK("Krakow"),
    LUZ("Lublin"),
    POZ("Poznan"),
    RZE("Rzeszow"),
    SZZ("Szczecin"),
    WAW("Warsaw Chopin"),
    WMI("Warsaw Modlin"),
    WRO("Wroclaw"),

    // 🇦🇹 Austria
    GRZ("Graz"),
    INN("Innsbruck"),
    KLU("Klagenfurt"),
    LNZ("Linz"),
    SZG("Salzburg"),
    VIE("Vienna");

    private final String city;

    IataCode(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

}