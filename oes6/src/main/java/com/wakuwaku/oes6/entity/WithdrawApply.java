package com.wakuwaku.oes6.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WithdrawApply {

    /**
     * 金额，微信单位：分， 支付宝单位：元
     */
    @NotBlank(message = "amount金额不能为空")
    private String amount;

}
