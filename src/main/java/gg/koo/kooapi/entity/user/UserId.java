package gg.koo.kooapi.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class UserId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userid;
}
