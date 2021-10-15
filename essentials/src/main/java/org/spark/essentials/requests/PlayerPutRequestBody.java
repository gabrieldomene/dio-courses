package org.spark.essentials.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerPutRequestBody {
    private Long id;
    private String name;
}
