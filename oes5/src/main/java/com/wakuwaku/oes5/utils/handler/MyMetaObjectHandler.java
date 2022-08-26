package com.wakuwaku.oes5.utils.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author MisterDong
 * @date 2022/2/14 - 14:45
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("uRegiTime",new Date(),metaObject);
        this.setFieldValByName("uUpdateTime",new Date(),metaObject);
        this.setFieldValByName("inCreateTime", new Date(), metaObject);
        this.setFieldValByName("inCompleteTime",new Date(),metaObject);
        this.setFieldValByName("luTime",new Date(),metaObject);
        this.setFieldValByName("chTime",new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("uUpdateTime",new Date(),metaObject);
        this.setFieldValByName("inCompleteTime",new Date(),metaObject);

    }
}
