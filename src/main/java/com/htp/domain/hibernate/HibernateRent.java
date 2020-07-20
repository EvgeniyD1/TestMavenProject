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
        "user", "building"
})
@ToString(exclude = {
        "user", "building"
})
@Builder
@Entity
@Table(name = "m_rent")
public class HibernateRent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
//    @JsonIgnoreProperties("rent")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private HibernateUser user;

    @JsonBackReference
//    @JsonIgnoreProperties("rent")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private HibernateBuilding building;

    private Long price;

    private String currency;
}
