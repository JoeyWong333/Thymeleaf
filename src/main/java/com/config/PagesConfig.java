package com.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;
/**
 * 分页插件配置
 * @author wangll
 *
 */
@Configuration
public class PagesConfig {
	
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true"); 			// RowBounds参数offset作为PageNum使用 - 默认不使用
        p.setProperty("rowBoundsWithCount", "true");		//RowBounds是否进行count查询 - 默认不查询
        p.setProperty("reasonable", "true");				//分页合理化
        p.setProperty("dialect", "mysql");					
        p.setProperty("supportMethodsArguments", "false");	 //是否支持接口参数来传递分页参数，默认false
        p.setProperty("pageSizeZero", "true");				 //当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
        pageHelper.setProperties(p);	
        return pageHelper;
		
	}

}
