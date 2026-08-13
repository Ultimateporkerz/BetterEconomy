package net.ultimporks.betterecon.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfigs {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final Common COMMON;

    static {
        COMMON = new Common(BUILDER);
        COMMON_SPEC = BUILDER.build();
    }

    public static class Common {
        // Debugging
        public final ForgeConfigSpec.BooleanValue enableDebugging;
        // Currency Settings
        public final ForgeConfigSpec.IntValue startingAmount;
        public final ForgeConfigSpec.ConfigValue<String> currencyName;
        public final ForgeConfigSpec.ConfigValue<String> currencySymbol;
        // General Settings
        public final ForgeConfigSpec.BooleanValue sendGaveCurrencyMessage;
        public final ForgeConfigSpec.BooleanValue resetGiveCurrency;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Debugging").push("Development");

            enableDebugging = builder
                    .comment("Should debugging be enabled?")
                    .define("enableDebugging", false);

            builder.pop();

            builder.comment("Currency Settings").push("Currency");

            startingAmount = builder
                    .comment("How much currency should players start with?")
                    .defineInRange("Starting Amount:", 50, 0, Integer.MAX_VALUE);

            currencyName = builder
                    .comment("What is the name of your currency?")
                    .define("currencyName", "Dollars");

            currencySymbol = builder
                    .comment("What should the Currency symbol be?")
                    .define("currencySymbol","$");

            builder.pop();

            builder.comment("General Settings").push("General");

            sendGaveCurrencyMessage = builder
                    .comment("Should the player be told that currency was added to their account upon joining for the first time?")
                    .define("currencyMessage", true);

            resetGiveCurrency = builder
                    .comment("Should the data on whether or not players have been given their first time check be reset on next server start? ( THIS WILL RESET ALL PLAYERS BANK ACCOUNTS )")
                    .define("resetCurrencyMessage", false);

            builder.pop();
        }
    }




}
