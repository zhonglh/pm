package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IStaffPerformanceDao;
import com.pm.domain.business.StaffPerformance;
import com.pm.service.IStaffPerformanceService;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class StaffPerformanceServiceImpl implements IStaffPerformanceService {

    @Autowired
    private IStaffPerformanceDao staffPerformanceDao;

    @Override
    public int addStaffPerformance(StaffPerformance staffPerformance) {
        return staffPerformanceDao.addStaffPerformance(staffPerformance);
    }

    @Override
    public int updateStaffPerformance(StaffPerformance staffPerformance) {
        return staffPerformanceDao.updateStaffPerformance(staffPerformance);
    }

    @Override
    public void deleteStaffPerformance(StaffPerformance[] staffPerformances) {
        if(staffPerformances != null && staffPerformances.length >0) {
            for(StaffPerformance staffPerformance :staffPerformances) {
                staffPerformanceDao.deleteStaffPerformance(staffPerformance);
            }
        }
    }

    @Override
    public void verifyStaffPerformance(StaffPerformance staffPerformance) {
        staffPerformanceDao.verifyStaffPerformance(staffPerformance);
    }

    @Override
    public StaffPerformance getStaffPerformance(String id) {
        return staffPerformanceDao.getStaffPerformance(id);
    }




    @Override
    public List<StaffPerformance> getStaffPerformanceList(StaffPerformance staffPerformance){
        return staffPerformanceDao.getStaffPerformanceList(staffPerformance);
    }

    @Override
    public Pager<StaffPerformance> queryStaffPerformance(StaffPerformance staffPerformance, UserPermit userPermit, Pager<StaffPerformance> pager) {
        return staffPerformanceDao.queryStaffPerformance(staffPerformance,userPermit,pager);
    }

    @Override
    public void unVerify(String id) {

        StaffPerformance sp = this.getStaffPerformance(id);
        int size = staffPerformanceDao.unVerifyStaffPerformance(sp);
        if(size == 0) {
            throw new PMException(CommonErrorConstants.e029902);
        }
    }

    @Override
    public <T> T get(String id) {
        return (T)this.getStaffPerformance(id);
    }
}
