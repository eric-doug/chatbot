package com.jiuzhou.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "响应信息主体")
public class RestResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
	private int code;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回信息")
	private String msg;


	@Getter
	@Setter
	@ApiModelProperty(value = "数据")
	private T data;

	public static <T> RestResult<T> ok() {
		return restResult(null, 0, null);
	}

	public static <T> RestResult<T> ok(T data) {
		return restResult(data,0, null);
	}

	public static <T> RestResult<T> ok(T data, String msg) {
		return restResult(data, 0, msg);
	}

	public static <T> RestResult<T> failed() {
		return restResult(null,1, null);
	}

	public static <T> RestResult<T> failed(String msg) {
		return restResult(null, 1, msg);
	}

	public static <T> RestResult<T> failed(T data) {
		return restResult(data,1, null);
	}

	public static <T> RestResult<T> failed(T data, String msg) {
		return restResult(data, 1, msg);
	}

	private static <T> RestResult<T> restResult(T data, int code, String msg) {
		RestResult<T> apiResult = new RestResult<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
}
