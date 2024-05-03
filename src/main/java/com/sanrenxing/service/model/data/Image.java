package com.sanrenxing.service.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Image {
    final UUID id;
    final byte[] data;
}
