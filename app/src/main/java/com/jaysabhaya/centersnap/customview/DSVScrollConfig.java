package com.jaysabhaya.centersnap.customview;

public enum DSVScrollConfig {
    ENABLED {
        @Override
        boolean isScrollBlocked(com.jaysabhaya.centersnap.customview.Direction direction) {
            return false;
        }
    },
    FORWARD_ONLY {
        @Override
        boolean isScrollBlocked(com.jaysabhaya.centersnap.customview.Direction direction) {
            return direction == com.jaysabhaya.centersnap.customview.Direction.START;
        }
    },
    BACKWARD_ONLY {
        @Override
        boolean isScrollBlocked(com.jaysabhaya.centersnap.customview.Direction direction) {
            return direction == com.jaysabhaya.centersnap.customview.Direction.END;
        }
    },
    DISABLED {
        @Override
        boolean isScrollBlocked(com.jaysabhaya.centersnap.customview.Direction direction) {
            return true;
        }
    };

    abstract boolean isScrollBlocked(com.jaysabhaya.centersnap.customview.Direction direction);
}
