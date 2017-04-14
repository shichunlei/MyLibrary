package chingtech.library.utils;

public class ENUM {

    /**
     * 刷新列表类型
     */
    public enum Refresh {
        LOAD_MORE, // 加载更多
        REFRESH, // 刷新
        DEFAULT // 默认
    }

    public enum Week {
        WEEKS,
        WEEKS_CN,
        WEEKS_EN,
        WEEKS_EN_ABB,
        WEEKS_EN_LET
    }

    /**
     * View宽高比例
     */
    public enum Proportion {
        P_1_1,
        P_3_2,
        P_4_3,
        P_16_9,
        P_2_1,
        P_3_1,
        P_7_1
    }

    public enum SheetItemColor {
        Blue("#037BFF"),
        Red("#FD4A2E"),
        Yellow("#F8D411"),
        Orange("#FA9301"),
        Pink("#FF308E"),
        Purple("#6F26FA"),
        Green("#49CC56"),
        Cyan("#009ad6");

        private String name;

        SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
