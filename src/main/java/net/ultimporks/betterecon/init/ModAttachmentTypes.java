package net.ultimporks.betterecon.init;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.ultimporks.betterecon.Reference;
import net.ultimporks.betterecon.currency.Balance;

public class ModAttachmentTypes {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Reference.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Balance>> BALANCE =
            ATTACHMENT_TYPES.register(
                    "balance",
                    () -> AttachmentType.serializable(Balance::new)
                            .copyOnDeath()
                            .build()
            );



    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }


}
