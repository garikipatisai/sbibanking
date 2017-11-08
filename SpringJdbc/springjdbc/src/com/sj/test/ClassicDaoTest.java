package com.sj.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sj.bo.ProjectBo;
import com.sj.project.ProjectDAO;

public class ClassicDaoTest {
public static void main(String[] args) {
	ApplicationContext context=new ClassPathXmlApplicationContext("com/sj/common/application-context.xml");
	ProjectDAO pdao= context.getBean("projectDao",ProjectDAO.class);
	List<ProjectBo> projects=pdao.getProjectBo();
	System.out.println(projects);
	
}
}
