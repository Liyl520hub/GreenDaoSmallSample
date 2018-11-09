package test.yjj.com.jsontest;

/**
 * @author lyl
 * <p>
 * created 2018/9/10
 * <p>
 * class use:
 */
public class user {


    /**
     * status : Success
     * msg : 登录成功！
     * code : 0
     * returnValue : {}
     */

    private String status;
    private String msg;
    private String code;
    private ReturnValueBean returnValue;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReturnValueBean getReturnValue() {
        return returnValue == null ? new ReturnValueBean() : returnValue;
    }

    public void setReturnValue(ReturnValueBean returnValue) {
        this.returnValue = returnValue;
    }

    public static class ReturnValueBean {
        private String code;


        public String getCode() {
            return code == null ? "" : code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
