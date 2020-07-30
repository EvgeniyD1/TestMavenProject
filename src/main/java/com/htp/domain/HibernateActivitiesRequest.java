package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "activities"
})
@ToString(exclude = {
        "activities"
})
@Builder
@Entity
@Table(name = "m_real_estate_activities_request")
public class HibernateActivitiesRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_activities_id")
    private HibernateActivities activities;

    @Column(name = "user_link")
    private String userLink;

    @Column
    private String message;

    @Column
    private boolean delete;
}
