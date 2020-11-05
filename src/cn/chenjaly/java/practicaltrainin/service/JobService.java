package cn.chenjaly.java.practicaltrainin.service;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.Job;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public interface JobService {
	List<Job> findJob(Job job,PageModel model);
	int getTotalCountByJob(Job job);
	Job findJob(Integer id);
	public boolean addJob(Job job);
	public void updateJob(Job job);
	public void deleteJob(String[] id);
}
