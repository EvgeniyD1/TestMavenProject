package com.htp.domain;

import java.util.Objects;

public class Building {
    private Long id;
    private String type;
    private int land_area;
    private int rooms_count;
    private int total_rooms_area;
    private int living_area;
    private int kitchen_area;
    private int building_floors;
    private int floor;
    private int building_year;
    private boolean repairs;
    private boolean garage;
    private boolean barn;
    private boolean bath;
    private String description;
    private String country_location;
    private String region_location;
    private String town_location;
    private String street_location;
    private String building_location;
    private String room_location;

    public Building() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLand_area() {
        return land_area;
    }

    public void setLand_area(int land_area) {
        this.land_area = land_area;
    }

    public int getRooms_count() {
        return rooms_count;
    }

    public void setRooms_count(int rooms_count) {
        this.rooms_count = rooms_count;
    }

    public int getTotal_rooms_area() {
        return total_rooms_area;
    }

    public void setTotal_rooms_area(int total_rooms_area) {
        this.total_rooms_area = total_rooms_area;
    }

    public int getLiving_area() {
        return living_area;
    }

    public void setLiving_area(int living_area) {
        this.living_area = living_area;
    }

    public int getKitchen_area() {
        return kitchen_area;
    }

    public void setKitchen_area(int kitchen_area) {
        this.kitchen_area = kitchen_area;
    }

    public int getBuilding_floors() {
        return building_floors;
    }

    public void setBuilding_floors(int building_floors) {
        this.building_floors = building_floors;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBuilding_year() {
        return building_year;
    }

    public void setBuilding_year(int building_year) {
        this.building_year = building_year;
    }

    public boolean isRepairs() {
        return repairs;
    }

    public void setRepairs(boolean repairs) {
        this.repairs = repairs;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public boolean isBarn() {
        return barn;
    }

    public void setBarn(boolean barn) {
        this.barn = barn;
    }

    public boolean isBath() {
        return bath;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry_location() {
        return country_location;
    }

    public void setCountry_location(String country_location) {
        this.country_location = country_location;
    }

    public String getRegion_location() {
        return region_location;
    }

    public void setRegion_location(String region_location) {
        this.region_location = region_location;
    }

    public String getTown_location() {
        return town_location;
    }

    public void setTown_location(String town_location) {
        this.town_location = town_location;
    }

    public String getStreet_location() {
        return street_location;
    }

    public void setStreet_location(String street_location) {
        this.street_location = street_location;
    }

    public String getBuilding_location() {
        return building_location;
    }

    public void setBuilding_location(String building_location) {
        this.building_location = building_location;
    }

    public String getRoom_location() {
        return room_location;
    }

    public void setRoom_location(String room_location) {
        this.room_location = room_location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return land_area == building.land_area &&
                rooms_count == building.rooms_count &&
                total_rooms_area == building.total_rooms_area &&
                living_area == building.living_area &&
                kitchen_area == building.kitchen_area &&
                building_floors == building.building_floors &&
                floor == building.floor &&
                building_year == building.building_year &&
                repairs == building.repairs &&
                garage == building.garage &&
                barn == building.barn &&
                bath == building.bath &&
                Objects.equals(id, building.id) &&
                Objects.equals(type, building.type) &&
                Objects.equals(description, building.description) &&
                Objects.equals(country_location, building.country_location) &&
                Objects.equals(region_location, building.region_location) &&
                Objects.equals(town_location, building.town_location) &&
                Objects.equals(street_location, building.street_location) &&
                Objects.equals(building_location, building.building_location) &&
                Objects.equals(room_location, building.room_location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, land_area, rooms_count, total_rooms_area, living_area, kitchen_area, building_floors, floor, building_year, repairs, garage, barn, bath, description, country_location, region_location, town_location, street_location, building_location, room_location);
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", land_area=" + land_area +
                ", rooms_count=" + rooms_count +
                ", total_rooms_area=" + total_rooms_area +
                ", living_area=" + living_area +
                ", kitchen_area=" + kitchen_area +
                ", building_floors=" + building_floors +
                ", floor=" + floor +
                ", building_year=" + building_year +
                ", repairs=" + repairs +
                ", garage=" + garage +
                ", barn=" + barn +
                ", bath=" + bath +
                ", description='" + description + '\'' +
                ", country_location='" + country_location + '\'' +
                ", region_location='" + region_location + '\'' +
                ", town_location='" + town_location + '\'' +
                ", street_location='" + street_location + '\'' +
                ", building_location='" + building_location + '\'' +
                ", room_location='" + room_location + '\'' +
                '}';
    }
}
