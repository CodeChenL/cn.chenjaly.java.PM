package org.gec.dao;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.Job;
import org.gec.util.PageModel;

public interface JobDao {
    //查询
    public List<Job> findJob(Job job, PageModel model);

    //添加
    public boolean addJob(Job job);

    //修改
    public void updateJob(Job job);

    //删除
    public void deleteJob(String[] id);

    public int getTotalCountByJob(Job job);

    public Job findJob(Integer id);
}
