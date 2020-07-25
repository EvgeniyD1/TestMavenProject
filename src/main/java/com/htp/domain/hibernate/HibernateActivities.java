package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
        "requests", "building"
})
@ToString(exclude = {
        "requests", "building"
})
@Builder
@Entity
@Table(name = "m_real_estate_activities")
public class HibernateActivities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private HibernateBuilding building;

    @Column
    private Long price;

    @Column
    private String currency;

    @Column(name = "user_link")
    private String userLink;

    @Column(name = "building_link")
    private String buildingLink;

    @Column
    private String type;

    @JsonManagedReference
    @OneToMany(mappedBy = "activities", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateActivitiesRequest> requests;
}
