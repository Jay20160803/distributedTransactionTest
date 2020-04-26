package com.andy;

import com.andy.config.Ds0Properties;
import com.andy.config.Ds1Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Andy
 * @date 2020/4/24 9:32
 */
@EnableConfigurationProperties(value = {Ds0Properties.class, Ds1Properties.class})
@SpringBootApplication
public class XAApplicaiton {

    public static void main(String[] args){
        SpringApplication.run(XAApplicaiton.class,args);
    }
}
