package org.googlesearch;

import java.util.ArrayList;
import java.util.List;

public class httpLists {

    List<urlFormat> list404 = new ArrayList<>();
    List<urlFormat> list502 = new ArrayList<>();
    List<urlFormat> list501 = new ArrayList<>();
    List<urlFormat> list500 = new ArrayList<>();
    List<urlFormat> list200 = new ArrayList<>();
    List<urlFormat> list301 = new ArrayList<>();
    List<urlFormat> other = new ArrayList<>();

    public List<urlFormat> getList404() {
        return list404;
    }

    public void setList404(List<urlFormat> list404) {
        this.list404 = list404;
    }

    public List<urlFormat> getList200() {
        return list200;
    }

    public List<urlFormat> getList301() {
        return list301;
    }

    public void setList301(List<urlFormat> list301) {
        this.list301 = list301;
    }

    public List<urlFormat> getOther() {
        return other;
    }

    public void setOther(List<urlFormat> other) {
        this.other = other;
    }

    public void setList200(List<urlFormat> list200) {
        this.list200 = list200;
    }

    public List<urlFormat> getList502() {
        return list502;
    }

    public void setList502(List<urlFormat> list502) {
        this.list502 = list502;
    }

    public List<urlFormat> getList501() {
        return list501;
    }

    public void setList501(List<urlFormat> list501) {
        this.list501 = list501;
    }

    public List<urlFormat> getList500() {
        return list500;
    }

    public void setList500(List<urlFormat> list500) {
        this.list500 = list500;
    }
}
