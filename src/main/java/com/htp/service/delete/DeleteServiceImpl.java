package com.htp.service.delete;

import com.htp.dao.delete.DeleteDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteServiceImpl implements DeleteService{

    private final DeleteDao deleteDao;

    public DeleteServiceImpl(DeleteDao deleteDao) {
        this.deleteDao = deleteDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteUser(Long id) {
        deleteDao.deleteUser(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteRole(Long id) {
        deleteDao.deleteRole(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteBuilding(Long id) {
        deleteDao.deleteBuilding(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteActivities(Long id) {
        deleteDao.deleteActivities(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void deleteActivitiesRequest(Long id) {
        deleteDao.deleteActivitiesRequest(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void restoreUser(Long id) {
        deleteDao.restoreUser(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void restoreRole(Long id) {
        deleteDao.restoreRole(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void restoreBuilding(Long id) {
        deleteDao.restoreBuilding(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void restoreActivities(Long id) {
        deleteDao.restoreActivities(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void restoreActivitiesRequest(Long id) {
        deleteDao.restoreActivitiesRequest(id);
    }
}
