package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.ActivitiesRequestRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.ActivitiesRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ActivityRequestRequestConverter<S, T> extends EntityConverter<S, T> {

    protected ActivitiesRequest doConvert(ActivitiesRequest activitiesReq,
                                          ActivitiesRequestRequest request) {

        activitiesReq.setMessage(request.getMessage());

        return activitiesReq;
    }

}
