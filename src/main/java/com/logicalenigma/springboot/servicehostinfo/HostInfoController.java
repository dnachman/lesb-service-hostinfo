package com.logicalenigma.springboot.servicehostinfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostInfoController {

	@Autowired
	private EurekaClient eurekaClient;

	@RequestMapping(path="/")
	@ResponseBody
	public Map<String, Object> getRequestHeaderInfo(
		@RequestHeader(value="User-Agent") String userAgent,
		@RequestHeader(value="Accept-Language") String acceptLanguage,
		HttpServletRequest httpRequest
	) {
		Map<String, Object> responseMap = new HashMap<>();

		responseMap.put("requestIpAddress", httpRequest.getRemoteAddr());
		responseMap.put("X-Forwarded-For", httpRequest.getHeader("X-Forwarded-For"));
		responseMap.put("X-Forwarded-Server", httpRequest.getHeader("X-Forwarded-Server"));
		responseMap.put("X-Forwarded-Host", httpRequest.getHeader("X-Forwarded-Host"));
		responseMap.put("requestLanguage", acceptLanguage);
		responseMap.put("requestSoftware", userAgent);
		

		return responseMap;

	}

@RequestMapping(path = "/server-info")
	@ResponseBody
	public Map<Object, Object> getServerInfo() throws UnknownHostException {

		Map<Object, Object> responseMap = new HashMap<>();

		InetAddress ip = InetAddress.getLocalHost();

		responseMap.put("hostAddress", ip.getHostAddress());

		return responseMap;

	}

	@RequestMapping(path="/instance-info")
	@ResponseBody
	public Map<Object, Object> getEurekaInstanceInfo() {

		Map<Object,Object> responseMap = new HashMap<>();

		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("service-hostinfo", false);

		responseMap.put("hostName", instanceInfo.getHostName());
		responseMap.put("ipAddr", instanceInfo.getIPAddr());
		responseMap.put("instanceId", instanceInfo.getInstanceId());

		return responseMap;

	}



}
