# Spring MVC 패턴 학습

spring의 전체적인 실행 순서

<img width="200%" src="https://user-images.githubusercontent.com/67315764/98838008-02bc1e80-2487-11eb-99b8-bb5906aec9a0.png"></img>

Request -> DispatcherServlet -> HandlerMapping -> (Controller -> Service -> DAO -> DB -> DAO -> Service -> Controller)
-> DispatcherServlet -> ViewResolver -> View -> DispatcherServlet -> Response

1. DispatcherServlet 은 모든 연결을 담당하며, 웹 브라우저에서 요청이 들어오면
   요청을 처리하기 위해 HandlerMapping 객체에게 컨트롤러 검색을 요청한다.
   
2. DispatcherServlet은 클라이언트의 요청 경로를 이용해서 이를 처리할 컨트롤러 객체를 찾아서 DispatcherServlet에 리턴한다.

3. DispatcherServlet은 @Controller 어노테이션을 이용해서 구현한 컨트롤러, 
   스프링 2.5까지 사용됐던 Controller 인터페이스를 구현한 컨트롤러, 
   특수 목적으로 사용되는 HttpRequestHandler 인터페이스를 구현한 클래스를 동일한 방식으로 실행하고 처리하기 위해
   HandlerAdapter 객체에게 요청 처리를 위임한다.
   
4. HandlerAdapter 객체는 컨트롤러의 알맞은 메소드를 호출해서 요청을 처리하고,
   반환 받은 결과를 ModelAndView 객체에 담아서 DispatcherServlet 에 리턴한다.
   
5. ModelAndView 객체를 반환 받은 DispatcherServlet 은 ViewResolver 객체를 이용해서 결과를 보여줄 뷰를 검색한다.
   ViewResolver 객체는 ModelAndView 객체에 담긴 뷰 이름을 이용해서 View 객체를 찾거나 생성해서 리턴한다.
   
6. DispatcherServlet 은 ViewResolver가 리턴한 View 객체에게 응답 결과 생성을 요청한다.

7. JSP를 사용하는 경우, View 객체는 JSP 를 실행함으로서 브라우저에게 전송할 응답 결과를 생성한다.
   ModelAndView 의 Model 객체에 담겨있는 데이터가 응답 결과에 필요하면 Model 에서 데이터를 꺼내 JSP 에서 사용할 수 있다.
   
   
   클라이언트의 요청을 실제로 처리하는 것은 컨트롤러이고, DispatcherServlet은 클라이언트의 요청을    전달받는 창구 역할을 한다.
   쉽게 말하자면, 서빙 직원이 음식 주문을 받으면 실제 음식은 주방장이 하는 경우와 마찬가지다.
   Controller : 주방장, DispatcherServlet : 서빙 직원
   
   DispatcherServlet 에게 어떤 컨트롤러가 요청을 처리하는가는 중요하지 않으며,
   처리 결과를 ModelAndView 타입의 객체로 전달 받을 수 있기만 하면 된다.
   이 때, 사용할 컨트롤러를 찾고, 처리 결과를 ModelAndView 객체로 변환해주는 객체가 HandlerAdapter 이다.
   
   핸들러 객체의 실제 타입마다 그에 알맞은 HandlerMapping 과 HandlerAdapter 가 존재하기 때문에,
   사용할 핸들러의 종류에 따라 해당 HandlerMapping 과 HandlerAdapter 를 스프링 빈으로 등록해 주어야 하는데,
   스프링 설정 기능(<mvc:annotation-driven>)을 사용하면 직접 등록하지 않아도 스프링이 알아서 등록해준다.
   
   요청을 처리할 컨트롤러를 찾기 때문에 ControllerMapping 이라는 이름이 어울리는데,
   스프링 MVC 는 웹 요청을 처리할 수 있는 범용적인 프레임워크를 제공하고 있기 때문에,
   클라이언트의 요청을 처리하는 객체가 컨트롤러가 아닐 수도 있다.(HttpRequestHandler 등).
   그래서 스프링 MVC 는 웹 요청을 실제로 처리하는 객체를 Handler 라고 표현하고 있다.
   따라서 컨트롤러는 DispatcherServlet 입장에서 보면 한 종류의 핸들러 객체이다.
   

2. DispatcherServlet 과 Spring Container

   스프링의 DispatcherServlet 은 설정 파일을 이용해서 스프링 컨테이너를 생성하는데,
   기본적으로 WEB-INF 폴더 안의 WEB-INF/[서블릿 이름]-servlet.xml 파일을 설정 파일로 이용한다.

   HandlerMapping, HandlerAdapter, 컨트롤러, ViewResolver 등의 객체는 

   DisptacherServlet 이 생성하는 스프링 컨테이너로 부터 구하기 때문에,

   DistacherServlet 이 사용하는 설정 파일에 이들 객체를 빈 으로 설정해야 한다.

   DispatcherServlet => 설정 파일 로딩 => 스프링 컨테이너 생성 

   => 스프링 컨테이너 안에 빈으로 설정된 HandlerMapping, HandlerAdapter, 컨트롤러, ViewResolver 객체 이용

   그리고 이렇게 생성되는 스프링 컨테이너는 WebApplicationContext 컨테이너이다.



