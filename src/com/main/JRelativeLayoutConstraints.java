package com.main;

import java.awt.*;

public class JRelativeLayoutConstraints {

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

    public void resetFields() {
        centerInParent = false;
        parentTop = false;
        parentBottom = false;
        parentStart = false;
        parentEnd = false;
        centerVertical = false;
        centerHorizontal = false;

        start = null;
        end = null;
        top = null;
        bottom = null;
        above = null;
        bellow = null;
        endOf = null;
        leftOf = null;

        marginTop = 0;
        marginBottom = 0;
        marginEnd = 0;
        marginStart = 0;
    }

    public JRelativeLayoutConstraints newInstance() {
        JRelativeLayoutConstraints ret = new JRelativeLayoutConstraints();
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

        resetFields();
        return ret;
    }
}
