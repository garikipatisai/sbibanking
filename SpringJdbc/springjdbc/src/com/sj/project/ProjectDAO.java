package com.sj.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.sj.bo.ProjectBo;

public class ProjectDAO {
	private JdbcTemplate jdbcTemplate;
	private final String sql_query = "select project_no,title,description,duration,status from project";

	public ProjectDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ProjectBo> getProjectBo() {

		return jdbcTemplate.execute(new getProjectBoPreapareStatementCreator(),
				new getPreparedStatementCallback());

	}
      // it is creating preparedStatement for dynamically passed data to super class
	// if you pass static data, no need to create preparedStatement. you can use statement. 
	private final class getProjectBoPreapareStatementCreator implements
			PreparedStatementCreator {

		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(sql_query);

			return pstmt;
		}
	}
    
	private final class getPreparedStatementCallback implements
			PreparedStatementCallback<List<ProjectBo>> {

		public List<ProjectBo> doInPreparedStatement(PreparedStatement pstmt)
				throws SQLException, DataAccessException {
			List<ProjectBo> projects = null;
			ProjectBo bo = null;
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			projects = new ArrayList<ProjectBo>();
			while (rs.next()) {
				bo = new ProjectBo();
				bo.setProjectNo(rs.getInt("PROJECT_NO"));
				bo.setTitle(rs.getString("TITLE"));
				bo.setDescription(rs.getString("DESCRIPTION"));
				bo.setDuration(rs.getInt("DURATION"));
				bo.setStatus(rs.getString("STATUS"));
				projects.add(bo);
			
		}
return projects;
		
		}}
	}

