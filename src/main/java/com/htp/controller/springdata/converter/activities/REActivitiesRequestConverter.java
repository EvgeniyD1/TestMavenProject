package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.REActivitiesSDSaveRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.hibernate.HibernateRealEstateActivities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class REActivitiesRequestConverter <S, T> extends EntityConverter<S, T> {

    protected HibernateRealEstateActivities doConvert(HibernateRealEstateActivities activities,
                                                      REActivitiesSDSaveRequest request) {

        activities.setPrice(request.getPrice());
        activities.setCurrency(request.getCurrency());
        activities.setType(request.getType());

        return activities;
    }
}
