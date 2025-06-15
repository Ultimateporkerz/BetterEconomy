package net.ultimporks.betterecon.configs;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec COMMON_SPEC;

    public static final Common COMMON;

    static {
        COMMON = new Common(BUILDER);
        COMMON_SPEC = BUILDER.build();
    }

    public static class Common {
        // Debugging
        public final ModConfigSpec.BooleanValue enableDebugging;
        // Currency Settings
        public final ModConfigSpec.IntValue startingAmount;
        public final ModConfigSpec.ConfigValue<String> currencyName;
        public final ModConfigSpec.ConfigValue<String> currencySymbol;
        // General Settings
        public final ModConfigSpec.BooleanValue sendGaveCurrencyMessage;

        public Common(ModConfigSpec.Builder builder) {
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

            builder.pop();
        }
    }




}
