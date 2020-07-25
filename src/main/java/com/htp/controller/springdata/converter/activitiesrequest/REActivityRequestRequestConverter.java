package com.htp.controller.springdata.converter.activitiesrequest;

import com.htp.controller.springdata.activitiestequest.REActivitiesRequestSDSaveRequest;
import com.htp.controller.springdata.converter.EntityConverter;
import com.htp.domain.hibernate.HibernateRealEstateActivitiesRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class REActivityRequestRequestConverter <S, T> extends EntityConverter<S, T> {

    protected HibernateRealEstateActivitiesRequest doConvert(HibernateRealEstateActivitiesRequest activitiesReq,
                                                             REActivitiesRequestSDSaveRequest request) {

        activitiesReq.setMessage(request.getMessage());

        return activitiesReq;
    }

}
