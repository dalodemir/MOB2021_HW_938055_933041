package de.fh_kiel.iue.mob2021_hw_938055_933041;

public final class CryptoData {
    private String name;
    private String euro;

    public CryptoData(String name, String euro) {
        this.name = name;
        this.euro = euro;
    }

    public String getEuro() {
        return euro;
    }

    public String getName() {
        return name;
    }
}
