package com.scathon.tech.rpc.common.init;

import com.scathon.tech.rpc.common.BeanScanFlag;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TODO Function The Class Is.
 *
 * @ClassName RpcCommonBeanConfig.
 * @Description TODO.
 * @Author linhd eng:ScathonLin
 * @Date 2019/5/1
 * @Version 1.0
 */
@Configuration
@ComponentScan(basePackageClasses = {BeanScanFlag.class})
public class RpcCommonBeanConfig {
}