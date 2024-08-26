package com.devteria.identityservice.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImgResponse {
    private Long id;

    private String name;
    private String type;
    private byte[] imgData;
}
