package hello.springmvc.basic.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {

	/**
	 * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
	 */
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		log.info("username={}, age={}", username, age);
		response.getWriter().write("ok");
	}

	@ResponseBody
	@RequestMapping("/request-param-v2")
//	public String requestParamV2(@RequestParam(name = "username", required = false) String memberName, @RequestParam("age") int memberAge, String samName) {
	public String requestParamV2(@RequestParam(name = "username") String memberName, @RequestParam("age") int memberAge, String samName) {
		log.info("username={}, age={}, samName={}", memberName, memberAge, samName, samName);
		return "OK";
	}

	/**
	 * @RequestParam 사용 HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
	 */
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(@RequestParam String username, @RequestParam int age) {
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam 사용 String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
	 */
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age) {
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam.required /request-param-required -> username이 없으므로 예외
	 *
	 *                        주의! /request-param-required?username= -> 빈문자로 통과
	 *
	 *                        주의! /request-param-required int age -> null을 int에 입력하는
	 *                        것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
	 */
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(@RequestParam(required = true) String username, @RequestParam(required = false) Integer age) {
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam - defaultValue 사용
	 *
	 *               참고: defaultValue는 빈 문자의 경우에도 적용
	 *               /request-param-default?username=
	 */
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, @RequestParam(required = false, defaultValue = "-1") int age) {
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-params")
	 public String test(@RequestParam(name="username", defaultValue = "") List<String> usernames, @RequestParam("age") List<Integer> ages) {

		log.info("usernames: {}", usernames);
		log.info("ages: {}", ages);
		
		return "ok";
		
	 }	
	
	/**
	 * @RequestParam Map, MultiValueMap Map(key=value) MultiValueMap(key=[value1,
	 *               value2, ...]) ex) (key=userIds, value=[id1, id2]) 파라미터의 값이 1개가
	 *               확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
		return "ok";
	}

	
	@ResponseBody
	@RequestMapping("/request-param-map2")
	public String requestParamMap2(@RequestParam MultiValueMap<String, String> multiMap) {
		
		log.info("multiMap username={}, age={}", multiMap.get("username"), multiMap.get("age"));
		log.info("MultiValueMap:" + multiMap);
		
		
		List<String> list = multiMap.get("username");
		list.forEach(val -> { log.info("val = {}" , val); });
		
		
		return "ok";
	}
	
	
	
	/**
	 * @ModelAttribute 사용 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨, 뒤에 model을
	 *                 설명할 때 자세히 설명
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@ModelAttribute HelloData helloData) {
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		
//		HelloData helloTest = HelloData.builder().age(10).username("test").build();
//		log.info("helloTest {}", helloTest);
		
		return "ok";
	}

	/**
	 * @ModelAttribute 생략 가능 String, int 같은 단순 타입 = @RequestParam argument resolver
	 *                 로 지정해둔 타입 외 = @ModelAttribute
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData) {
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/model-attribute-v3")
	public HelloData modelAttributeV3(HelloData helloData) {
		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		return helloData;
	}
	
	@ResponseBody
	@RequestMapping("/model-attribute-v4")
	public List<HelloData> modelAttributeV4(@RequestBody List<HelloData> params) {
	    log.info("params={}", params);
	    return params;
	}
	
	
	
}

