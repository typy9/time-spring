package com.parpiiev.time.utils.projections;

import lombok.*;

/**
 * JPA Projection Bean to retrieve data from a custom JPA query.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class ActivityProjection {

    private int id;
    private String name;
    private int category_id;

}
