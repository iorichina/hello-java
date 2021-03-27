package iorichina.hellojava.helloface.constant;

public enum ViewEnum {
    FACE_DETECT("face/detect"),
    SERVER_ERROR("error/50x");

    public String viewName;

    ViewEnum(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
