package cc.young.pay.client.factory;

/**
 * description: UserOrderTypeService
 * date: 2020/9/23 16:13
 * author: faner
 */
public interface UserOrderTypeService {

    /**
     * 查询订单信息
     * @param orderId
     * @return
     */
    Integer searchOrderInfo(String orderId);

    /**
     * 更新订单状态
     * @param orderId
     * @return
     */
    Integer updateOrderStatus(String orderId);

}
