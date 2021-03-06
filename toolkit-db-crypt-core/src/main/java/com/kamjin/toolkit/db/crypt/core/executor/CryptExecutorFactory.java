package com.kamjin.toolkit.db.crypt.core.executor;

import com.kamjin.toolkit.db.crypt.core.annotation.CryptField;
import com.kamjin.toolkit.db.crypt.core.bean.DbcryptProperties;
import com.kamjin.toolkit.db.crypt.core.exception.DbCryptRuntimeException;
import com.kamjin.toolkit.db.crypt.core.handler.DefaultAESCodecFieldValueHandler;

/**
 * 加解密执行者工厂类
 *
 * @author kamjin1996
 */
public class CryptExecutorFactory {

    private static final CryptExecutor DEFAULT_HANDLER = new DefaultCryptExecutor(new DefaultAESCodecFieldValueHandler(DbcryptProperties.buildDefault()));

    /**
     * 根据cryptField中不同的配置
     *
     * @param cryptField 加密的filed注解
     * @return CryptExecutor 加解密执行器
     */
    public static CryptExecutor getTypeHandler(CryptField cryptField) {
        CryptExecutor cryptExecutor;

        //TODO 支持其他的加密方式
        if (cryptField.value() == DefaultCryptExecutor.class) {
            cryptExecutor = DEFAULT_HANDLER;
        } else {
            throw new DbCryptRuntimeException("not support encrypt type [" + cryptField.value() + "]");
        }
        return cryptExecutor;
    }
}
