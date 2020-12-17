package com.spring.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

/*인터페이스*/
public interface IHumanDAO
{
	public int add(HumanDTO human) throws SQLException;
	
	public int count() throws SQLException;
	
	public ArrayList<HumanDTO> list() throws SQLException;
	
	
}
