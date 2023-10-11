package hello.springmvc.basic.request;

import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RequestHeaderController {

	@RequestMapping("/headers")
	public String headers(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod, Locale locale, @RequestHeader MultiValueMap<String, String> headerMap,
			@RequestHeader("host") String host, @CookieValue(value = "myCookie", required = false) String cookie) {

		log.info("request={}", request);
		log.info("response={}", response);
		log.info("httpMethod={}", httpMethod);
		log.info("locale={}", locale);
		log.info("headerMap={}", headerMap);
		log.info("header host={}", host);
		log.info("myCookie={}", cookie);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap();
		map.add("keyA", "value1");
		map.add("keyA", "value2");
		//[value1,value2]
		List<String> values = map.get("keyA");
		
		return "ok";
	}
}
