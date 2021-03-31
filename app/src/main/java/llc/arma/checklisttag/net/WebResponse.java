package llc.arma.checklisttag.net;

public class WebResponse {

    public int status;
    public String msg;

    public WebResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public WebResponse() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
