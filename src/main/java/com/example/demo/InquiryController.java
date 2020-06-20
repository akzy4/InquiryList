package com.example.demo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InquiryController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String index(Model model) {
		String sql = "SELECT id, name, mail, age, gender, contents FROM inquiry";
		
		RowMapper<Inquiry> rowMapper = new RowMapper<Inquiry>() {

			@Override
			public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
				Inquiry inquiry = new Inquiry();
				inquiry.setName(rs.getString("name"));
				inquiry.setMail(rs.getString("age"));
				inquiry.setAge(rs.getInt("age"));
				inquiry.setGender(rs.getString("gender"));
				inquiry.setContents(rs.getString("contents"));
				return inquiry;
			}
			
		};
		
		List<Inquiry> inquiries = jdbcTemplate.query(sql, rowMapper);
		model.addAttribute("inquiries", inquiries);
		return "index";
			
	}

}
