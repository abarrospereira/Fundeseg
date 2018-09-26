package br.com.app.model.session;

import br.com.app.model.response.OperationResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SessionResponse extends OperationResponse {
	@ApiModelProperty(required = true, value = "")
	private SessionItem item;

	public SessionItem getItem() {
		return item;
	}

	public void setItem(SessionItem item) {
		this.item = item;
	}

}
