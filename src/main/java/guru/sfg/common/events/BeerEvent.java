package guru.sfg.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class BeerEvent implements Serializable {
    static final long serialVersionUID = 2500814025775227769L;
    private BeerDto beerDto;
}
