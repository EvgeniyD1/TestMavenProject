package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "user"
})
@ToString(exclude = {
        "user"
})
@Builder
@Entity
@Table(name = "m_chat")
public class HibernateChat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private HibernateUser user;

    @Column(name = "room_id")
    private Long roomId;

    @Column
    private Timestamp time;

    @Column
    private String message;

}
