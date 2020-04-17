package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 19:10
 * DESC
 */
@Data
public class Reserved {
    private String reserved;

    public Reserved(String reserved) {
        this.reserved = reserved;
    }

    public String dataSep(String dataUnit) {
        return dataUnit.substring(4, 6);
    }
}
