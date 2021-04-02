package me.marcusslover.caverns.utils;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {
    public static final String LEGACY_DARK_RED = "&4";
    public static final String DARK_RED = "%hex(#FF0000)";
    public static final String LEGACY_RED = "&c";
    public static final String RED = "%hex(#FF4848)";
    public static final String PINK = "%hex(#FF67AD)";
    public static final String LEGACY_PINK = "&d";
    public static final String LIGHT_PINK = "%hex(#FFB2D6)";
    public static final String PURPLE = "%hex(#8400FF)";
    public static final String LEGACY_PURPLE = "&5";
    public static final String LIGHT_PURPLE = "%hex(#C484FF)";
    public static final String MAGENTA = "%hex(#FF0097)";
    public static final String LIGHT_MAGENTA = "%hex(#FF61BE)";
    public static final String FUCHSIA = "%hex(#D600C8)";
    public static final String DARK_ORANGE = "%hex(#FF4600)";
    public static final String ORANGE = "%hex(#FF8300)";
    public static final String LIGHT_ORANGE = "%hex(#FF956C)";
    public static final String YELLOW = "%hex(#FFFF00)";
    public static final String LEGACY_YELLOW = "&e";
    public static final String GOLD = "%hex(#FED23C)";
    public static final String LEGACY_GOLD = "&6";
    public static final String LIME = "%hex(#C9FF00)";
    public static final String LEGACY_LIME = "&a";
    public static final String LIGHT_LIME = "%hex(#E0FF6C)";
    public static final String OLIVE = "%hex(#82A400)";
    public static final String LIGHT_OLIVE = "%hex(#A0CA00)";
    public static final String GREEN = "%hex(#46FF00)";
    public static final String LEGACY_GREEN = "&2";
    public static final String LIGHT_GREEN = "%hex(#95FF6E)";
    public static final String DARK_GREEN = "%hex(#2B9F00)";
    public static final String MARINE = "%hex(#00FF97)";
    public static final String LEGACY_CYAN = "&3";
    public static final String LIGHT_MARINE = "%hex(#6FFFC4)";
    public static final String DARK_MARINE = "%hex(#00AF67)";
    public static final String AQUA = "%hex(#00FFE8)";
    public static final String LEGACY_AQUA = "&b";
    public static final String LIGHT_AQUA = "%hex(#76FFF3)";
    public static final String DARK_AQUA = "%hex(#00BCAC)";
    public static final String BLUE = "%hex(#00B9FF)";
    public static final String LEGACY_BLUE = "&9";
    public static final String LIGHT_BLUE = "%hex(#6ED7FF)";
    public static final String DARK_BLUE = "%hex(#0074A0)";
    public static final String LEGACY_DARK_BLUE = "&1";
    public static final String INDIGO = "%hex(#002EFF)";
    public static final String LIGHT_INDIGO = "%hex(#7790FF)";
    public static final String DARK_INDIGO = "%hex(#00146C)";
    public static final String WHITE = "&f";
    public static final String LIGHT_GRAY = "%hex(#CBCBCB)";
    public static final String LEGACY_LIGHT_GRAY = "&7)";
    public static final String GRAY = "%hex(#979797)";
    public static final String LEGACY_GRAY = "&8";
    public static final String BLACK = "&0";


    public static final Map<String, String> CACHED_FORMATIING = new HashMap<>();
    public static final Pattern STRIP_COLOR = Pattern.compile("(i?)§([0-9A-FK-ORa-fk-orXx])");
    public static final Pattern STRIP_HEX = Pattern.compile("(i?)&[xX]((&[0-9A-FK-ORa-fk-orXx]){6})");

    public static String stripText(String text) {
        return stripText(text, true);
    }

    public static String stripText(String text, boolean stripColors) {
        Matcher matcher = STRIP_COLOR.matcher(text);
        String color = text;

        while (matcher.find()) {
            String bukkitColor = matcher.group(2);
            color = color.replaceFirst(STRIP_COLOR.pattern(), "&" + bukkitColor);
        }

        if (stripColors) {
            Matcher matcher1 = STRIP_HEX.matcher(color);
            String hex = color;

            while (matcher1.find()) {
                String hexColor = matcher1.group(2).replaceAll("&", "");
                hex = hex.replaceFirst(STRIP_HEX.pattern(), "%hex(#" + hexColor + ")");
            }
            return hex;

        } else {
            color = color.replaceAll(STRIP_COLOR.pattern(), "");
            return color;
        }
    }

    public static String toColor(String s) {
        if (CACHED_FORMATIING.containsKey(s)) {
            return CACHED_FORMATIING.get(s);
        }

        String uni = unicodeConvert(s);
        String hex = colorHex(uni);
        String rgb = colorRgb(hex);
        String hsb = colorHsb(rgb);
        String cint = colorInt(hsb);
        String grad = colorGrad(cint);
        String s1 = ChatColor.translateAlternateColorCodes('&', grad);
        CACHED_FORMATIING.put(s, s1);

        return s1;
    }

    private static String colorHex(final String string) {
        final String[] spelling = {"color", "colour", "hex", "COLOR", "COLOUR", "HEX"};
        String copy = string;

        for (final String s1 : spelling) {
            if (!string.contains(s1)) {
                continue;
            }
            Pattern pattern = Pattern.compile("%" + s1 + "(\\()(#?[a-fA-F0-9]{6})(\\))");
            Matcher matcher = pattern.matcher(copy); // match to the string

            while (matcher.find()) {
                try {
                    String group = matcher.group(2);
                    if (!group.startsWith("#")) {
                        group = "#" + group;
                    }
                    ChatColor chatColor = ChatColor.of(group);
                    copy = copy.replaceFirst(pattern.pattern(), chatColor.toString());
                } catch (Exception ignored) {
                    return string;
                }
            }
        }
        return copy;
    }

    private static String colorRgb(final String string) {
        final String[] spelling = {"rgb", "RGB"};
        String copy = string;

        for (final String s1 : spelling) {
            if (!string.contains(s1)) {
                continue;
            }
            Pattern pattern = Pattern.compile("%" + s1 + "(\\()(\\d+),\\s?(\\d+),\\s?(\\d+)(\\))");
            Matcher matcher = pattern.matcher(copy); // match to the string

            while (matcher.find()) {
                try {
                    int red = Integer.parseInt(matcher.group(2));
                    int green = Integer.parseInt(matcher.group(3));
                    int blue = Integer.parseInt(matcher.group(4));

                    ChatColor chatColor = ChatColor.of(new Color(red, green, blue));
                    copy = copy.replaceFirst(pattern.pattern(), chatColor.toString());
                } catch (Exception ignored) {
                    return string;
                }
            }
        }
        return copy;
    }

    private static String colorHsb(final String string) {
        final String[] spelling = {"hsb", "HSB", "hsl", "HSL", "hsv", "HSV"};
        String copy = string;

        for (final String s1 : spelling) {
            if (!string.contains(s1)) {
                continue;
            }
            Pattern pattern = Pattern.compile("%" + s1 + "(\\()(\\d+(\\.\\d+)?)\\s?,\\s?(\\d+(\\.\\d+)?)\\s?,\\s?(\\d+(\\.\\d+)?)(\\))");
            Matcher matcher = pattern.matcher(copy); // match to the string

            while (matcher.find()) {
                try {
                    float hue = (float) (NumberUtil.fromFancyDouble(matcher.group(2)) / 360);
                    float sat = (float) (NumberUtil.fromFancyDouble(matcher.group(4)) / 100f);
                    float bri = (float) (NumberUtil.fromFancyDouble(matcher.group(6)) / 100f);

                    ChatColor chatColor = ChatColor.of(Color.getHSBColor(hue, sat, bri));
                    copy = copy.replaceFirst(pattern.pattern(), chatColor.toString());
                } catch (Exception ignored) {
                    return string;
                }
            }
        }
        return copy;
    }

    private static String unicodeConvert(final String string) {
        final String[] spelling = {"uni", "UNI", "unicode", "UNICODE", "char", "CHAR", "character", "CHARACTER"};
        String copy = string;

        for (final String s1 : spelling) {
            if (!string.contains(s1)) {
                continue;
            }
            Pattern pattern = Pattern.compile("%" + s1 + "(\\()(0x)([a-fA-F0-9]+)(\\))");
            Matcher matcher = pattern.matcher(copy); // match to the string

            while (matcher.find()) {
                try {
                    String index = matcher.group(3);
                    int i = Integer.parseInt(index, 16);
                    copy = copy.replaceFirst(pattern.pattern(), Character.toString((char) i));
                } catch (Exception ignored) {
                    return string;
                }
            }
        }
        return copy;
    }

    private static String colorInt(final String string) {
        final String[] spelling = {"cint", "CINT", "colorint", "COLORINT"};
        String copy = string;

        for (final String s1 : spelling) {
            if (!string.contains(s1)) {
                continue;
            }
            Pattern pattern = Pattern.compile("%" + s1 + "(\\()(\\d+)(\\))");
            Matcher matcher = pattern.matcher(copy); // match to the string

            while (matcher.find()) {
                try {
                    int colorIn = Integer.parseInt(matcher.group(2));
                    colorIn = colorIn % 16777216;
                    int i = (colorIn & 16711680) >> 16;
                    int j = (colorIn & 65280) >> 8;
                    int k = (colorIn & 255);
                    ChatColor chatColor = ChatColor.of(new Color(i, j, k));
                    copy = copy.replaceFirst(pattern.pattern(), chatColor.toString());
                } catch (Exception ignored) {
                    return string;
                }
            }
        }
        return copy;
    }

    private static String colorGrad(final String string) {
        final String[] spelling = {"gradient", "GRAD", "grad", "GRADIENT"};
        String copy = string;

        for (final String s1 : spelling) {
            if (!string.contains(s1)) {
                continue;
            }
            Pattern pattern = Pattern.compile("%" + s1 + "(\\()(#[a-fA-F0-9]{6}), (#[a-fA-F0-9]{6}), ([.]*)(\\))");
            Matcher matcher = pattern.matcher(copy); // match to the string

            while (matcher.find()) {
                try {
                    ChatColor pos1 = ChatColor.of(matcher.group(2));
                    ChatColor pos2 = ChatColor.of(matcher.group(3));
                    String gradientText = matcher.group(4);
                    String colorGradient = colorGradient(gradientText, pos1.getColor(), pos2.getColor(), new LinearInterpolator());
                    copy = copy.replaceFirst(pattern.pattern(), colorGradient);
                } catch (Exception ignored) {
                    return string;
                }
            }
        }
        return copy;
    }

    private static String colorGradient(final String string, Color from, Color to, IInterpolator IInterpolator) {
        double[] red = IInterpolator.interpolate(from.getRed(), to.getRed(), string.length());
        double[] green = IInterpolator.interpolate(from.getGreen(), to.getGreen(), string.length());
        double[] blue = IInterpolator.interpolate(from.getBlue(), to.getBlue(), string.length());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            builder.append(ChatColor.of(new Color(
                    (int) Math.round(red[i]),
                    (int) Math.round(green[i]),
                    (int) Math.round(blue[i]))))
                    .append(string.charAt(i));
        }

        return builder.toString();
    }

    public static List<String> toColor(List<String> s) {
        List<String> strings = new ArrayList<>();
        for (String ss : s) {
            strings.add(toColor(ss));
        }
        return strings;
    }

    public static String[] toColor(String[] s) {
        String[] strings = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            strings[i] = toColor(s1);
        }
        return strings;
    }
}
