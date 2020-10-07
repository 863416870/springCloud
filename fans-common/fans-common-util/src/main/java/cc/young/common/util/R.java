package cc.young.common.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private T data;

	@Getter
	@Setter
	private String  requestUri;

	public static <T> R<T> ok() {
		return restResult(null, Code.SUCCESS.getCode(), Code.SUCCESS.getZhDescription());
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, Code.SUCCESS.getCode(), Code.SUCCESS.getZhDescription());
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, Code.SUCCESS.getCode(), msg);
	}

	public static <T> R<T> failed() {
		return restResult(null,Code.FAIL.getCode(), null);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null, Code.FAIL.getCode(), msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data, Code.FAIL.getCode(), null);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data, Code.FAIL.getCode(), msg);
	}

	/**
	 * 未登录返回结果
	 */
	public static <T> R<T> unauthorized(T data) {
		return restResult(data,Code.UN_AUTHORIZATION.getCode(), Code.UN_AUTHORIZATION.getZhDescription());
	}

	/**
	 * 未授权返回结果
	 */
	public static <T> R<T> forbidden(T data) {
		return restResult(data,Code.FORBIDDEN.getCode(), Code.FORBIDDEN.getZhDescription());
	}

	private static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

}
