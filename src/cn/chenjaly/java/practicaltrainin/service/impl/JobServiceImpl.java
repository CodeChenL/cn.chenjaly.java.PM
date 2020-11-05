package cn.chenjaly.java.practicaltrainin.service.impl;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.Job;
import cn.chenjaly.java.practicaltrainin.dao.JobDao;
import cn.chenjaly.java.practicaltrainin.dao.impl.JobDaoImpl;
import cn.chenjaly.java.practicaltrainin.service.JobService;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public class JobServiceImpl	implements JobService {
	private JobDao dao = new JobDaoImpl();
	@Override
	public List<Job> findJob(Job job, PageModel model) {
		try {
			List<Job> jobs = dao.findJob(job, model);
			return jobs;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public int getTotalCountByJob(Job job) {
		try {
			int count = dao.getTotalCountByJob(job);
			return count;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		return 0;
	}

	@Override
	public Job findJob(Integer id) {
		return dao.findJob(id);
	}

	@Override
	public boolean addJob(Job job) {
		return dao.addJob(job);
	}

	@Override
	public void updateJob(Job job) {
		dao.updateJob(job);
		
	}

	@Override
	public void deleteJob(String[] id) {
		dao.deleteJob(id);
		
	}

}
