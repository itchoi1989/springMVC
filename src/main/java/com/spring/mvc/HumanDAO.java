package com.spring.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

/**

  * @FileName : HumanDAO.java
  * @Project : springmvc03
  * @Date : 2020. 11. 11. 오후 10:10:27
  * @작성자 : 최호석
  * @변경이력 :
  * @프로그램 설명 : 의존성 주입과 preparedstament를 정확히 공부할 것.
  * 				 DAO 작동 순서를 익힐것.
  *                  ResultSet은 무엇이고, executeUpdate와 executeQuery의 차이는 무엇인가?

  */

/* 데이터베이스 액션 처리 객체 */ 

public class HumanDAO implements IHumanDAO
{
	// ※ Connection 객체에 대한 의존성 주입을 위한 준비
	
	//    ① 인터페이스 형태의 데이터타입
	private DataSource dataSource;
	
	//    ② setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	// ※ IHumanDAO 인터페이스의 메소드 오버라이딩(Overring)
	@Override
	public int add(HumanDTO human) throws SQLException
	{
		int result = 0;
		
		//datasource를 통해 데이터베이스에 연결한 후 
		//연결 객체의 인스턴스인 conn 변수에 넣는다.
		Connection conn = dataSource.getConnection();
		String sql = "INSERT INTO HUMANLIST(MID, NAME, TELEPHONE) "
				+ " VALUES(HUMANLIST_SEQ.NEXTVAL, ?, ?)";
		
		// conn에 preparestament 를 연결하여 pstmt에 담는다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 첫번째 이름이 담길 것을 세팅해준다.
		pstmt.setString(1, human.getName());
		// 두번째 전화번호가 담길 것을 세팅해준다.
		pstmt.setString(2, human.getTelephone());
		
		// executeUpdate로 쿼리를 결과값에 담아준다.
		result = pstmt.executeUpdate();
		System.out.println(result);
		
		// 사용한 preparedstament를 닫아준다.
		pstmt.close();
		// 사용한 conn을 닫아준다.
		conn.close();
		 
		return result;
		
	}

	@Override
	public int count() throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT COUNT(*) AS COUNT FROM HUMANLIST";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
			result = rs.getInt("COUNT");
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	
	@Override
	public ArrayList<HumanDTO> list() throws SQLException
	{
		ArrayList<HumanDTO> result = new ArrayList<HumanDTO>();
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT MID,NAME,TELEPHONE FROM HUMANLIST ORDER BY MID";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			HumanDTO dto = new HumanDTO();
			
			dto.setMid(String.valueOf(rs.getInt("MID")));
			dto.setName(rs.getString("NAME"));
			dto.setName(rs.getString("TELEPHONE"));
			
			result.add(dto);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	
}
