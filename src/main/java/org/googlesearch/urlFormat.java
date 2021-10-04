package org.googlesearch;

public class urlFormat {

    public String urlAdres;
    public int httpStatusCode;
    public String sonTarama;

    public urlFormat(String urlAdres, String sonTarama) {
        this.urlAdres = urlAdres;
        //this.httpStatusCode = httpStatusCode;
        this.sonTarama=sonTarama;
    }

    public String getUrlAdres() {
        return urlAdres;
    }

    public void setUrlAdres(String urlAdres) {
        this.urlAdres = urlAdres;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getSonTarama() {
        return sonTarama;
    }

    public void setSonTarama(String sonTarama) {
        this.sonTarama = sonTarama;
    }
}
