package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Building {
    private Long id;
    private String type;
    private int landArea;
    private int roomsCount;
    private int totalRoomsArea;
    private int livingArea;
    private int kitchenArea;
    private int buildingFloors;
    private int floor;
    private int buildingYear;
    private boolean isRepairs;
    private boolean isGarage;
    private boolean isBarn;
    private boolean isBath;
    private String description;
    private String countryLocation;
    private String regionLocation;
    private String townLocation;
    private String streetLocation;
    private String buildingLocation;
    private String roomLocation;

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

    public int getLandArea() {
        return landArea;
    }

    public void setLandArea(int landArea) {
        this.landArea = landArea;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getTotalRoomsArea() {
        return totalRoomsArea;
    }

    public void setTotalRoomsArea(int totalRoomsArea) {
        this.totalRoomsArea = totalRoomsArea;
    }

    public int getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(int livingArea) {
        this.livingArea = livingArea;
    }

    public int getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(int kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public int getBuildingFloors() {
        return buildingFloors;
    }

    public void setBuildingFloors(int buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBuildingYear() {
        return buildingYear;
    }

    public void setBuildingYear(int buildingYear) {
        this.buildingYear = buildingYear;
    }

    public boolean isRepairs() {
        return isRepairs;
    }

    public void setRepairs(boolean repairs) {
        this.isRepairs = repairs;
    }

    public boolean isGarage() {
        return isGarage;
    }

    public void setGarage(boolean garage) {
        this.isGarage = garage;
    }

    public boolean isBarn() {
        return isBarn;
    }

    public void setBarn(boolean barn) {
        this.isBarn = barn;
    }

    public boolean isBath() {
        return isBath;
    }

    public void setBath(boolean bath) {
        this.isBath = bath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }

    public String getRegionLocation() {
        return regionLocation;
    }

    public void setRegionLocation(String regionLocation) {
        this.regionLocation = regionLocation;
    }

    public String getTownLocation() {
        return townLocation;
    }

    public void setTownLocation(String townLocation) {
        this.townLocation = townLocation;
    }

    public String getStreetLocation() {
        return streetLocation;
    }

    public void setStreetLocation(String streetLocation) {
        this.streetLocation = streetLocation;
    }

    public String getBuildingLocation() {
        return buildingLocation;
    }

    public void setBuildingLocation(String buildingLocation) {
        this.buildingLocation = buildingLocation;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return landArea == building.landArea &&
                roomsCount == building.roomsCount &&
                totalRoomsArea == building.totalRoomsArea &&
                livingArea == building.livingArea &&
                kitchenArea == building.kitchenArea &&
                buildingFloors == building.buildingFloors &&
                floor == building.floor &&
                buildingYear == building.buildingYear &&
                isRepairs == building.isRepairs &&
                isGarage == building.isGarage &&
                isBarn == building.isBarn &&
                isBath == building.isBath &&
                Objects.equals(id, building.id) &&
                Objects.equals(type, building.type) &&
                Objects.equals(description, building.description) &&
                Objects.equals(countryLocation, building.countryLocation) &&
                Objects.equals(regionLocation, building.regionLocation) &&
                Objects.equals(townLocation, building.townLocation) &&
                Objects.equals(streetLocation, building.streetLocation) &&
                Objects.equals(buildingLocation, building.buildingLocation) &&
                Objects.equals(roomLocation, building.roomLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, landArea, roomsCount, totalRoomsArea, livingArea, kitchenArea, buildingFloors, floor, buildingYear, isRepairs, isGarage, isBarn, isBath, description, countryLocation, regionLocation, townLocation, streetLocation, buildingLocation, roomLocation);
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", land_area=" + landArea +
                ", rooms_count=" + roomsCount +
                ", total_rooms_area=" + totalRoomsArea +
                ", living_area=" + livingArea +
                ", kitchen_area=" + kitchenArea +
                ", building_floors=" + buildingFloors +
                ", floor=" + floor +
                ", building_year=" + buildingYear +
                ", repairs=" + isRepairs +
                ", garage=" + isGarage +
                ", barn=" + isBarn +
                ", bath=" + isBath +
                ", description='" + description + '\'' +
                ", country_location='" + countryLocation + '\'' +
                ", region_location='" + regionLocation + '\'' +
                ", town_location='" + townLocation + '\'' +
                ", street_location='" + streetLocation + '\'' +
                ", building_location='" + buildingLocation + '\'' +
                ", room_location='" + roomLocation + '\'' +
                '}';
    }
}
