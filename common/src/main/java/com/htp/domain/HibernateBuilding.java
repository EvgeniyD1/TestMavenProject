package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "user", "activities"
})
@ToString(exclude = {
        "user", "activities"
})
@Builder
@Entity
@Table(name = "m_buildings")
public class HibernateBuilding implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column(name = "land_area")
    private Integer landArea;

    @Column(name = "rooms_count")
    private Integer roomsCount;

    @Column(name = "total_rooms_area")
    private Integer totalRoomsArea;

    @Column(name = "living_area")
    private Integer livingArea;

    @Column(name = "kitchen_area")
    private Integer kitchenArea;

    @Column(name = "building_floors")
    private Integer buildingFloors;

    @Column
    private Integer floor;

    @Column(name = "building_year")
    private Integer buildingYear;

    @Column
    private boolean repairs;

    @Column
    private boolean garage;

    @Column
    private boolean barn;

    @Column
    private boolean bath;

    @Column
    private String description;

    @Column(name = "country_location")
    private String countryLocation;

    @Column(name = "region_location")
    private String regionLocation;

    @Column(name = "town_location")
    private String townLocation;

    @Column(name = "street_location")
    private String streetLocation;

    @Column(name = "building_location")
    private String buildingLocation;

    @Column(name = "room_location")
    private String roomLocation;

    @Column
    private boolean delete;

    //+
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private HibernateUser user;


    @JsonManagedReference
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateActivities> activities;
}
