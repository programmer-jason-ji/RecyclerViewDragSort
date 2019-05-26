package drag_sort.dragsortrecyclerview.channel.entity;

/**
 * Created by Ji on 2019/4/30.
 */


public class ChannelInfo {

    String src;
    int typeView;

    public ChannelInfo(String src, int typeView) {
        this.src = src;
        this.typeView = typeView;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getTypeView() {
        return typeView;
    }

    public void setTypeView(int typeView) {
        this.typeView = typeView;
    }
}
