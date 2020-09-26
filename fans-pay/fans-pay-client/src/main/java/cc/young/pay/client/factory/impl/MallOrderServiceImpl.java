//package cc.young.pay.client.factory.impl;
//
//import com.yzy.common.core.constant.CommonConstants;
//import com.yzy.pay.client.factory.UserOrderTypeService;
//import com.yzy.pay.client.factory.UserOrderTypeServiceStrategyFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//
///**
// * 商城订单支付
// * description: ClinicBookOrderServiceImpl
// * date: 2020/9/23 16:21
// * author: faner
// */
//@Service
//public class MallOrderServiceImpl implements UserOrderTypeService, InitializingBean {
//    //TODO 商城订单类型
//
//    @Override
//    public Integer searchOrderInfo(String orderId) {
//
//        return null;
//    }
//
//    @Override
//    public Integer updateOrderStatus(String orderId) {
//        return null;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        UserOrderTypeServiceStrategyFactory.register(CommonConstants.ORDER_MALL_BUY_TYPE,this);
//    }
//}
