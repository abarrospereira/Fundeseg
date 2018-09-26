//This is a common http response model for providing data series

package br.com.app.model.response;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import br.com.app.model.data.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleDataSeriseResponse extends OperationResponse {
    private List<SingleSerise> items;
}
