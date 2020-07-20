package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "m_sale")
public class HibernateSale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
//    @JsonIgnoreProperties("sale")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private HibernateUser user;

    @JsonBackReference
//    @JsonIgnoreProperties("sale")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private HibernateBuilding building;

    private Long price;

    private String currency;
}
