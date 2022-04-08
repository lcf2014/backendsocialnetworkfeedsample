package com.backendsocialnetworkapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoubleIdObjectEntity {
    private String id1;
    private String id2;
}
