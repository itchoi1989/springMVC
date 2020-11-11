package com.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 이 제공하는 『Controller』인터페이스를 구현함으로써
//    사용자 정의 컨트롤러 클래스를 구성한다.

// 『implements Controller』 또는 『extends AbstractController』
//-- 서블릿에서 『HttpServlet』을 상속받은 서블릿 객체 역할

/**

  * @FileName : HumanInsertController.java
  * @Project : springmvc03
  * @Date : 2020. 11. 11. 오후 10:30:27
  * @작성자 : 최호석
  * @변경이력 :
  * @프로그램 설명 : DI(의존성 주입)에 대해서 공부할 것
  *                  HttpServlet Request, Response에 대해 공부할 것
  *                  redirect에 대해 공부할 것.

  */
public class HumanInsertController implements Controller
{
	// 여기도 HumanDAO와 마찬가지로 DI(의존성 주입)을 한다.
	
	// 인터페이스 자료형
	private IHumanDAO dao;
	
	// setter 메소드 구성
	public void setDao(IHumanDAO dao)
	{
		this.dao = dao;
	}

	// ※ Controller 인터페이스의 메소드 재정의(Overrring)
	
	// ModelAndView
    // : View 객체 정보와 결과값이 들어있는 객체.
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		req.setCharacterEncoding("UTF-8");
		
		try
		{
			HumanDTO human = new HumanDTO();
			
			// Human.jsp 에서 날아올 이름과 전화번호를 get하여 설정해줌
			String name = req.getParameter("name");
			String telephone = req.getParameter("telephone");
			
			human.setName(name);
			human.setTelephone(telephone);
			
			// 여기서 DI를 했던 dao를 가져다가 add 메소드를 사용한다.
			dao.add(human);
			
		} catch (Exception e)
		{
			// 예외처리에 대한 내역
			System.out.println(e.toString());
		}
		
		// 모델앤뷰 객체안에 setviewname을 통해 다시 url을 지정해준다.
		mav.setViewName("redirect:humanlist.action");
		
		return mav;
	}

}
