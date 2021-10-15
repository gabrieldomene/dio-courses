package org.spark.essentials.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class PlayerPostRequestBody {

    @NotEmpty(message = "The player name cannot be empty")
    private String name;
}
