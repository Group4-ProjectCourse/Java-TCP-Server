package Model;

public class Window {
    int windowId ;
    boolean windowStatus;

    public Window() {
    }

    public Window(int windowId, boolean windowStatus) {
        this.windowId = windowId;
        this.windowStatus = windowStatus;
    }

    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public boolean isWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(boolean windowStatus) {
        this.windowStatus = windowStatus;
    }
}
