package com.mn.socketp1.config;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/4/20 10:44
 * DESC 可连接至服务器的ip
 * 如需添加，新增即可
 */
@Getter
public enum IpList {
    IL_001("220.178.53.83"),  //我的主机在公司网络中对外ip
    IL_003("10.204.9.192"),   //我的主机在公司网络中内网ip
    IL_002("114.102.130.1");  //我的主机在公寓网络中对外ip


    private String ip;

    IpList(String ip) {
        this.ip = ip;
    }

    public static boolean containsIp(String address) {
        for (IpList ip : IpList.values()) {
            if (address.contains(ip.getIp()))
                return true;
        }
        return false;
    }
}

