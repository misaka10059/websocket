package com.mn.socketp1.component.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/30 14:27
 * DESC HTTP请求中需要使用的参数
 */
@Component
@Getter
@Setter
public class RunTimeData {
    /*
     * 数智开发的设备数据上传接口
     */
    public static final String DATA_TRAN_URL = "http://39.99.208.254:8080/proj/api/assetsAlarm/saveAssetsMonitorData";
    public static final String DATA_SOURCE_DTO_KEY = "dataSourceDto";
    public static final String HASH_KEY = "hash";

    /*
     * 查询数智数据库中指定型号设备的所有厂商编号
     */
    public static final String QUERY_DEVICE_LIST_URL = "http://39.99.208.254:9902/query/device_list";  //服务器上运行的地址
    public static final String QUERY_DEVICE_LIST_LOCAL_URL = "http://127.0.0.1:9902/query/device_list";  //本地测试用地址
    public static final String PRODUCT_MODEL = "product_model";

}
