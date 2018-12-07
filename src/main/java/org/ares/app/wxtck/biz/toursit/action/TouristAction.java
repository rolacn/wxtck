package org.ares.app.wxtck.biz.toursit.action;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.ares.app.wxtck.common.util.GeneralUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class TouristAction {

	@RequestMapping("/getQrcImg")
	public void valicode(String qrcontent,Integer size,HttpServletResponse response) throws Exception{
		if(size==null)
			size=Integer.valueOf(2);
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		byte[] b_qrc=gutil.getScalaQrcode(qrcontent, size.intValue());
		os.write(b_qrc);
		os.flush();
		//ImageIO.write(image, "png", os);
	}
	
	@RequestMapping("/dev")
	public String dev() throws Exception{
		return "dev/dev";		
	}

	@Resource GeneralUtil gutil;
}
