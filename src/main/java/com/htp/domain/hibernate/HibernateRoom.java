package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "building"
})
@ToString(exclude = {
        "building"
})
@Builder
@Entity
@Table(name = "m_rooms")
public class HibernateRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "buildings_id", nullable = false)
    private HibernateBuilding building;

    @Column(name = "room_area")
    private Integer roomArea;

    @Column
    private String description;
}
