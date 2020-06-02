package com.mn.socketp1.domain.dto.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/6/2 10:21
 * DESC
 */
@Data
@AllArgsConstructor
public class ComponentStateDto {
    private int isFireAlarm;  //是否有火警,0否1是

    private int isMalfunction;  //是否故障,0否1是
}
