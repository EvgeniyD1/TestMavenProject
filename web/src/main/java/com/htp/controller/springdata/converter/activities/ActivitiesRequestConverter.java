package com.htp.controller.springdata.converter.activities;

import com.htp.controller.springdata.activities.ActivitiesRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.Activities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ActivitiesRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Activities doConvert(Activities activities,
                                   ActivitiesRequest request) {

        activities.setPrice(request.getPrice());
        activities.setCurrency(request.getCurrency());
        activities.setType(request.getType());

        return activities;
    }
}
