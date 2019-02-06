package io.renren.common.utils;

/**
 * Created by IntelliJ IDEA.
 * @author : wsy
 * Date: 2019-02-03
 * Time: 23:52
 */
public class IntegerUtil {

   public static Integer integerNumber(int   a,int   b){
        return  (a%b==0)?a/b:(a/b+1);
    }
}
