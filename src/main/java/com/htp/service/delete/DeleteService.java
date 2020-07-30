package com.htp.service.delete;

public interface DeleteService {

    void deleteUser(Long id);

    void deleteRole(Long id);

    void deleteBuilding(Long id);

    void  deleteActivities(Long id);

    void  deleteActivitiesRequest(Long id);

    void restoreUser(Long id);

    void restoreRole(Long id);

    void restoreBuilding(Long id);

    void  restoreActivities(Long id);

    void  restoreActivitiesRequest(Long id);
}
