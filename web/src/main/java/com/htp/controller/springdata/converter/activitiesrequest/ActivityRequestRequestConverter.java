package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.ActivitiesRequestSDRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.HibernateActivitiesRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ActivityRequestRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateActivitiesRequest doConvert(HibernateActivitiesRequest activitiesReq,
                                                   ActivitiesRequestSDRequest request) {

        activitiesReq.setMessage(request.getMessage());

        return activitiesReq;
    }

}
