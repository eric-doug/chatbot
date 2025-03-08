package com.jiuzhou.common.token;

/**
 * 返回状态码
 * 第一位 1:商品；2:用户；3:交易,4:促销,5:店铺,6:页面,7:设置,8:其他
 *
 * @author Chopper
 * @since 2020/4/8 1:36 下午
 */
public enum ResultCode {

    /**
     * 成功状态码
     */
    SUCCESS(200, "成功"),
    USER_CONNECT_LOGIN_ERROR(21, "未找到登录信息"),
    /**
     * 活动
     */
    PROMOTION_GOODS_NOT_EXIT(40000, "当前促销商品不存在！"),
    PROMOTION_TIME_NOT_EXIST(40011, "活动起始时间和活动结束时间不能为空");

    private final Integer code;
    private final String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
