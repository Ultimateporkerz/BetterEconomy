package net.ultimporks.betterecon;

import java.util.Locale;

public class CurrencyTypes {


    
    public enum CurrencyType {
        COINS("Coins", "textures/gui/coins.png"),
        GEMS("Gems", "textures/gui/gems.png"),
        TOKENS("Tokens", "textures/gui/tokens.png"),
        DOLLARS("Dollars", "textures/gui/dollars.png"),
        CUSTOM("Custom", "");

        private final String displayName;
        private final String iconPath;

        CurrencyType(String displayName, String iconPath) {
            this.displayName = displayName;
            this.iconPath = iconPath;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getIconPath() {
            return iconPath;
        }

        public static CurrencyType fromString(String name) {
            try {
                return CurrencyType.valueOf(name.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                return CurrencyType.CUSTOM;
            }
        }
    }
}
