package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ResponseViewController {

	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1() {
		ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
		return mav;
	}
	
	
	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model) {
		model.addAttribute("data", "hello!! response-view-v2");
		return "response/hello";
	}
	
	@RequestMapping("/response-view-v3")
	public ModelAndView responseViewV1(ModelAndView mav) {
		mav.setViewName("response/hello");
		mav.addObject("data", "hello!");
		return mav;
	}		

	@RequestMapping("/response-view-v4")
	public String responseViewV4(Model model) {
		model.addAttribute("data", "hello!!");
//		return "response/hello2";
		return "forward:/basic/hello-form.html";
	}	
	
	@RequestMapping("/response-view-v5")
	public ModelAndView responseViewV5() {
		ModelAndView mav = new ModelAndView( "forward:/basic/hello-form.html");
		mav.addObject("data", "hello!!");
		return mav;
	}		
	
	@RequestMapping("/response/hello")
	public void responseViewV3(Model model) {
		model.addAttribute("data", "hello!!");
	}


}
