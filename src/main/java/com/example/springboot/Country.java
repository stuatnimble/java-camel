package com.example.springboot;

public class Country {
    private CountryName name;

    private Integer area;

    private String region;


    private String[] capital;

    public CountryName getName() {
        return name;
    }

    public void setName(CountryName name) {
        this.name = name;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

}
