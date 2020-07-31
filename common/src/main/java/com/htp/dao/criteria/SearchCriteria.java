package com.htp.dao.criteria;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SearchCriteria implements Serializable {

    private String orPredicate;
    private String key;
    private String operation;
    private String value;

}
