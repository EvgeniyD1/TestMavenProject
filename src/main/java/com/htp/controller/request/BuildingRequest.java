package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BuildingRequest {

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
}
