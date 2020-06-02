package com.mn.socketp1.domain.dto.data;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/30 10:24
 * DESC
 */
@Data
@ToString
public class DataSourceDto implements Serializable {

    private String dataSourceId;        //数据源ID，依此查找该数据源的秘钥

    private long timestamp;             //数据产生的时间戳

    private String deviceType;          //产生数据的设备类型

    private String deviceCode;          //产生数据的设备的厂家编号

    private String deviceData;          //具体数据内容，依型号类型不同而异（json）

}
