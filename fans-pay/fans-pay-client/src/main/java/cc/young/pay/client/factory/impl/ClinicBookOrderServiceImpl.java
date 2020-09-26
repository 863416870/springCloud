//package cc.young.pay.client.factory.impl;
//
//import cc.young.pay.client.factory.UserOrderTypeService;
//import cc.young.pay.client.factory.UserOrderTypeServiceStrategyFactory;
//import com.yzy.common.core.constant.CommonConstants;
//import com.yzy.pay.client.factory.UserOrderTypeService;
//import com.yzy.pay.client.factory.UserOrderTypeServiceStrategyFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//
///**
// * 诊所线上预约订单支付
// * description: ClinicBookOrderServiceImpl
// * date: 2020/9/23 16:21
// * author: faner
// */
//@Service
//public class ClinicBookOrderServiceImpl implements UserOrderTypeService, InitializingBean {
//    //TODO 诊所订单类型
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
//        UserOrderTypeServiceStrategyFactory.register(CommonConstants.ORDER_CLINIC_BOOK_TYPE,this);
//    }
//}
