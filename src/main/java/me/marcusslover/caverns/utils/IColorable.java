package me.marcusslover.caverns.utils;

import java.util.List;

public interface IColorable {
    default String toColor(String string) {
        return ColorUtil.toColor(string);
    }

    default List<String> toColor(List<String> list) {
        for (int i = 0, stringSize = list.size(); i < stringSize; i++) {
            String s = list.get(i);
            list.set(i, this.toColor(s));
        }
        return list;
    }
}
