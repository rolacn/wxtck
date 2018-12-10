package org.ares.app.wxtck.biz.toursit.action;

import javax.annotation.Resource;

import org.ares.app.wxtck.common.util.GeneralUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tourist")
public class TouristAction {

	@RequestMapping("/toBuyTicket")
	public String buyTicket() throws Exception{
		return "tourist/buy";
	}
	
	@RequestMapping("/placeOrder")
	public String placeOrder(){
		return "tourist/order";
	}

	@RequestMapping("/payOrder")
	public String payOrder(){
		return "tourist/pay";
	}
	
	@Resource GeneralUtil gutil;
}
