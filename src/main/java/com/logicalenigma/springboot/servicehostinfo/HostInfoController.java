package com.logicalenigma.springboot.servicehostinfo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostInfoController {

	@RequestMapping(path="/")
	@ResponseBody
	public Map<String, Object> getRequestHeaderInfo(
		@RequestHeader(value="User-Agent") String userAgent,
		@RequestHeader(value="Accept-Language") String acceptLanguage,
		HttpServletRequest httpRequest
	) {
		Map<String, Object> responseMap = new HashMap<>();

		responseMap.put("ipaddress", httpRequest.getRemoteAddr());
		responseMap.put("X-Forwarded-For", httpRequest.getHeader("X-Forwarded-For"));
		responseMap.put("X-Forwarded-Server", httpRequest.getHeader("X-Forwarded-Server"));
		responseMap.put("X-Forwarded-Host", httpRequest.getHeader("X-Forwarded-Host"));
		responseMap.put("language", acceptLanguage);
		responseMap.put("software", userAgent);

		return responseMap;

	}

}
