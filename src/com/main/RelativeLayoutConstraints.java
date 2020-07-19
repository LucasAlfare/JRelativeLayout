package com.main;

import java.awt.*;

public class RelativeLayoutConstraints {

    public boolean
            centerInParent,
            centerVertical,
            centerHorizontal,
            parentTop,
            parentBottom,
            parentStart,
            parentEnd;

    public Component
            start,
            end,
            top,
            bottom,
            above,
            bellow,
            endOf,
            leftOf;

    public int
            marginTop,
            marginBottom,
            marginEnd,
            marginStart;

    public RelativeLayoutConstraints copyOf(RelativeLayoutConstraints constraints) {
        RelativeLayoutConstraints ret = new RelativeLayoutConstraints();
        ret.centerInParent = centerInParent;
        ret.parentTop = parentTop;
        ret.parentBottom = parentBottom;
        ret.parentStart = parentStart;
        ret.parentEnd = parentEnd;
        ret.centerVertical = centerVertical;
        ret.centerHorizontal = centerHorizontal;

        ret.start = start;
        ret.end = end;
        ret.top = top;
        ret.bottom = bottom;
        ret.above = above;
        ret.bellow = bellow;
        ret.endOf = endOf;
        ret.leftOf = leftOf;

        ret.marginBottom = marginTop;
        ret.marginEnd = marginEnd;
        ret.marginStart = marginStart;

        return ret;
    }
}
