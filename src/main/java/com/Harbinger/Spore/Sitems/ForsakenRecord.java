package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Ssounds;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class ForsakenRecord extends RecordItem {
    public ForsakenRecord() {
        super(0, Ssounds.FORSAKEN_RECORD, new Properties().stacksTo(1).rarity(Rarity.EPIC).tab(ScreativeTab.SPORE_T), 1200);
    }
}
