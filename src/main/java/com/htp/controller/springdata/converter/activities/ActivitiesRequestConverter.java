package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.ActivitiesSDRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.hibernate.HibernateActivities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ActivitiesRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateActivities doConvert(HibernateActivities activities,
                                            ActivitiesSDRequest request) {

        activities.setPrice(request.getPrice());
        activities.setCurrency(request.getCurrency());
        activities.setType(request.getType());

        return activities;
    }
}
