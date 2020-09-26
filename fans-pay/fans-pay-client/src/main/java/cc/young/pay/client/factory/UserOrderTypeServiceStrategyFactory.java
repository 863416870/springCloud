package cc.young.pay.client.factory;

import cn.hutool.core.lang.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过传过来的订单类型 : 诊所预约订单类型   商城购买订单类型
 * description: UserOrderTypeServiceStrategyFactory
 * date: 2020/9/23 16:11
 * author: faner
 */
public class UserOrderTypeServiceStrategyFactory {

    private static Map<Integer,UserOrderTypeService> services = new ConcurrentHashMap<Integer,UserOrderTypeService>();

    public  static UserOrderTypeService getOrderType(Integer type){
        return services.get(type);
    }

    public static void register(Integer orderType,UserOrderTypeService userOrderTypeService){
        Assert.notNull(orderType,"orderType can't be null");
        services.put(orderType,userOrderTypeService);
    }
}
