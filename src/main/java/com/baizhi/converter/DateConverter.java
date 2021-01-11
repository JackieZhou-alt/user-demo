package com.baizhi.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class DateConverter implements Converter<String,Date> {
    private Set<String> patterns;

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }
    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        for (String pattern: patterns) {
            simpleDateFormat.applyPattern(pattern);
            try {
                Date date = simpleDateFormat.parse(source);
                return date;
            }catch(Exception e){

            }
        }
        throw new RuntimeException("必须提供合法的日期字符串数据");
    }


}
