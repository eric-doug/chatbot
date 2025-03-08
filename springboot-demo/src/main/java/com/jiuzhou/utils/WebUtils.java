package com.jiuzhou.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Miscellaneous utilities for web applications.
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {
	private  final String BASIC_ = "Basic ";
	private  final String UNKNOWN = "unknown";

	/**
	 * 读取cookie
	 *
	 * @param name cookie name
	 * @return cookie value
	 */
	public String getCookieVal(String name) {
		HttpServletRequest request = WebUtils.getRequest();
		Assert.notNull(request, "request from RequestContextHolder is null");
		return getCookieVal(request, name);
	}

	/**
	 * 读取cookie
	 *
	 * @param request HttpServletRequest
	 * @param name    cookie name
	 * @return cookie value
	 */
	public String getCookieVal(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 设置cookie
	 *
	 * @param response        HttpServletResponse
	 * @param name            cookie name
	 * @param value           cookie value
	 * @param maxAgeInSeconds maxage
	 */
	public void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	/**
	 * 获取 HttpServletRequest
	 *
	 * @return {HttpServletRequest}
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}


	/**
	 * 获取ip
	 *
	 * @return {String}
	 */
	public String getIP() {
		return getIP(WebUtils.getRequest());
	}

	/**
	 * 获取ip
	 *
	 * @param request HttpServletRequest
	 * @return {String}
	 */
	public String getIP(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest is null");
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return StringUtils.isBlank(ip) ? null : ip.split(",")[0];
	}


}
