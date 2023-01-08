package com.uniyaz.staff.service;

import com.uniyaz.staff.dao.StaffDao;
import com.uniyaz.staff.queryfilterdto.StaffQueryFilterDto;
import com.uniyaz.staff.domain.Staff;

import java.util.List;

public class StaffService {

    public List<Staff> findAll() {
        StaffDao staffDao = new StaffDao();
        return staffDao.findAll();
    }

    public Staff save(Staff staff) {
        StaffDao staffDao = new StaffDao();
        return staffDao.save(staff);
    }

    public void delete(Staff staff) {
        StaffDao staffDao = new StaffDao();
        staffDao.delete(staff);
    }

    public List<Staff> findAllByQueryFilterDto(StaffQueryFilterDto staffQueryFilterDto) {
        StaffDao staffDao = new StaffDao();
        return staffDao.findAllByQueryFilterDto(staffQueryFilterDto);
    }

    public List<Staff> findAllByQueryFilterDtoCriteria(StaffQueryFilterDto staffQueryFilterDto) {
        StaffDao staffDao = new StaffDao();
        return staffDao.findAllByQueryFilterDtoCriteria(staffQueryFilterDto);
    }
}
