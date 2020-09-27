package cc.young.common.util;

import java.math.BigDecimal;

public class PriceUtil {

    //    /**
//     * 金额为分的格式
//     */
//    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
//
//    /**
//     * 将分为单位的转换为元 （除100）
//     *
//     * @param amount
//     * @return
//     * @throws Exception
//     */
//    public static String fen2YuanStr(String amount) {
//        if (!amount.matches(CURRENCY_FEN_REGEX)) {
//            throw new RuntimeException("金额格式错误|"+amount);
//        }
//        return formatFen(BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)));
//    }
//
//    /**
//     * 格式化数字
//     * @param fen
//     * @return
//     */
//    private static String formatFen(BigDecimal fen){
//        DecimalFormat df1 = new DecimalFormat("0.00");
//
//        return df1.format(fen);
//    }
    /**
     * 分--转成--元
     * @param totalPrice 分
     * @return
     */
    public static String convertFen2Yuan(Integer totalPrice){

        BigDecimal f = new BigDecimal(totalPrice).divide(new BigDecimal(100));
        return String.format("%.2f", f);
    }

    /**
     * 元--转成--分
     * @param yuan
     * @return
     */
    public static String convertYuan2Fen(String yuan){
        BigDecimal fenBd = new BigDecimal(yuan).multiply(new BigDecimal(100));
        fenBd = fenBd.setScale(0, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(fenBd.intValue());
    }

    public static void main(String[] args) {
        System.out.println(convertFen2Yuan(9999999));
        System.out.println(convertYuan2Fen("101010"));
    }
}
