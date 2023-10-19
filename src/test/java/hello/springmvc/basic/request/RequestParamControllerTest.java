package hello.springmvc.basic.request;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class RequestParamControllerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("key", "value1");
        multiValueMap.add("key", "value2");

        System.out.println(multiValueMap);
        
        List<String> list = multiValueMap.get("key");
        
        list.forEach(val -> System.out.println(val));
        
        list.forEach(System.out::println);
	}

}
