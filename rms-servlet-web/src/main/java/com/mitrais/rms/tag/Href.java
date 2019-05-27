package com.mitrais.rms.tag;

import com.mitrais.rms.helper.FileHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Href extends SimpleTagSupport {
    private String href="";
    private String className="";
    private String style="";
    private String value="";
    private boolean uriLink=false;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String fHref= FileHelper.getRouteLink(request,href);
        JspWriter out = getJspContext().getOut();
        if(uriLink && request.getAttribute("javax.servlet.forward.request_uri").equals(request.getContextPath()+'/'+href))
            className=className+" active";
        out.println(String.format("<a href='%s' style='%s' class='%s'>%s</a>",fHref,style,className,value));
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isUriLink() {
        return uriLink;
    }

    public void setUriLink(boolean uriLink) {
        this.uriLink = uriLink;
    }
}
