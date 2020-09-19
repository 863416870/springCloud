package cc.yound.common.core.util;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "返回类")
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "code")
	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	@ApiModelProperty(value = "描述")
	private String msg;

	@Getter
	@Setter
	@ApiModelProperty(value = "对象")
	private T data;

	@Getter
	@Setter
	@ApiModelProperty(value = "请求路径")
	private String  requestUri;

	public static <T> R<T> ok() {
		return restResult(null, Code.SUCCESS.getCode(), null);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, Code.SUCCESS.getCode(), null);
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

	private static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

}
