package org.gec.service.impl;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.Job;
import org.gec.dao.JobDao;
import org.gec.dao.impl.JobDaoImpl;
import org.gec.service.JobService;
import org.gec.util.PageModel;

public class JobServiceImpl implements JobService {
    JobDao dao = new JobDaoImpl();

    @Override
    public List<Job> findJob(Job job, PageModel model) {
        // TODO Auto-generated method stub
        return dao.findJob(job, model);
    }

    @Override
    public boolean addJob(Job job) {
        // TODO Auto-generated method stub
        return dao.addJob(job);
    }

    @Override
    public void updateJob(Job job) {
        // TODO Auto-generated method stub
        dao.updateJob(job);
    }

    @Override
    public void deleteJob(String[] id) {
        // TODO Auto-generated method stub
        dao.deleteJob(id);
    }

    @Override
    public int getTotalCountByJob(Job job) {
        // TODO Auto-generated method stub
        return dao.getTotalCountByJob(job);
    }

    @Override
    public Job findJob(Integer id) {
        // TODO Auto-generated method stub
        return dao.findJob(id);
    }

}
