package org.vintsie.jcobweb.invoke;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 3:11 PM
 */
public class InvokeUtil {


    /**
     * get impl-class name from interface class name.
     *
     * @param itfClsName interface class name
     * @return implementation class name
     */
    public static String getImplClassName(String itfClsName) {
        String[] parts = itfClsName.split(Pattern.quote("."));

        parts[parts.length - 2] = "impl";
        parts[parts.length - 1] += "Impl";

        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (sb.length() != 0) sb.append(".");
            sb.append(part);
        }

        return sb.toString();
    }

    /**
     * 获取一个Object数据每个对象的对象类型，返回一个字符串，用
     * 逗号分隔。
     * @param args Object[]
     * @return arg1Type, arg2Type...
     */
    public static String getMethodParamsTypes(Object[] args){
        if(null == args || args.length < 1)
            return "void";
        StringBuilder sb = new StringBuilder();
        for(Object arg : args){
            if(sb.length() > 0)
                sb.append(", ");
            sb.append(arg.getClass().getName());
        }
        return sb.toString();
    }

}
