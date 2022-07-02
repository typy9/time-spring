package com.parpiiev.time.utils.dto;

import com.parpiiev.time.model.Status;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ActivityRequestDTO {

    private int request_id;

    private int user_id;

    private int activity_id;

    private Status status;
}
