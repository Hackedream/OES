package com.wakuwaku.oes5.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WithdrawApply {

    /**
     * 金额，微信单位：分， 支付宝单位：元
     */
    @NotBlank(message = "amount金额不能为空")
    private String amount;

}
