package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "rooms", "rent", "sale"
})
@ToString(exclude = {
        "rooms", "rent", "sale"
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

    @JsonManagedReference
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateRoom> rooms;

    @JsonManagedReference
//    @JsonIgnoreProperties("building")
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateRent> rent;

    @JsonManagedReference
//    @JsonIgnoreProperties("building")
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateSale> sale;
}
