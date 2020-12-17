package com.spring.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**

  * @FileName : HumanListController.java
  * @Project : springmvc03
  * @Date : 2020. 11. 11. 오후 10:42:27
  * @작성자 : 최호석
  * @변경이력 :
  * @프로그램 설명 :

  */

public class HumanListController implements Controller
{

	// ※ DAO 객체에 대한 의존성 주입
	
	// 인터페이스 자료형 객체
	private IHumanDAO dao;
	
    // setter 메소드 구성
	
	public void setDao(IHumanDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 리스트에 사람 또는 번호의 갯수
		int count = 0;
		
		// 뿌려줄 리스트 객체 생성
		ArrayList<HumanDTO> humanlist = new ArrayList<HumanDTO>();
		
		try
		{
			count  = dao.count();
			humanlist = dao.list();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		mav.setViewName("WEB-INF/views/HumanList.jsp");
		
		// DispatcherServlet → 특정 Controller 객체 호출 및 결과 수신.
		// 결과는 ModelAndView 객체.
		// DispatcherServlet → 특정 View 객체에 Model 객체를 넘겨주고 결과 출력.
		mav.addObject("count", count);
		mav.addObject("humanlist", humanlist);
		
		return mav;
	}

}
