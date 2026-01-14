package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.Print.APPrint;
import io.github.archipelagomw.Print.APPrintColor;
import io.github.archipelagomw.Print.APPrintPart;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.PrintJSONEvent;
import io.github.archipelagomw.flags.NetworkItem;
import net.deadlydiamond98.archipelago.common.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.deadlydiamond98.koalalib.util.ColorHelper;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.Map;

/**
 * This handles Text Client Messages from Archipelago, and sends them to players connected to the server
 */
public class APPrintJsonEvents {

    // Used for Coloring the priority similar to the Archipelago Text Client
    public static final Map<String, Integer> GRAB_TYPES = Map.of(
            "(priority)", 0xAF99EF,
            "(no priority)", 0x6D8BE8,
            "(avoid)", 0xE9786B
    );

    @ArchipelagoEventListener
    public void sendTextClientMessages(PrintJSONEvent event) {
        Archipelago.run(archipelago -> {
            if (event.apPrint.slot != archipelago.getSlot()) {
                APServerUtil.sendMessage(getText(archipelago, event.apPrint));
            }
        });
    }

    private static MutableText getText(Archipelago client, APPrint apPrint) {
        MutableText message = Text.empty();

        for (APPrintPart part : apPrint.parts) {
            boolean underlined = part.color == APPrintColor.underline;
            boolean bold = part.color == APPrintColor.bold;

            // Used for Coloring the priority similar to the Archipelago Text Client
            boolean bl = GRAB_TYPES.containsKey(part.text);
            int hex = bl ? GRAB_TYPES.get(part.text) : getTextColor(client, part);

            Style style = Style.EMPTY.withColor(hex).withUnderline(underlined).withBold(bold);
            message.append(Text.literal(part.text).setStyle(style));
        }
        return message;
    }

    private static int getTextColor(Archipelago client, APPrintPart part) {
        if (part.color == APPrintColor.none && part.type != null) {
            return switch (part.type) {
                case playerID -> client.getMyName().equals(part.text) ? 0xEE00EE : 0xFAFAD2;
                case locationID -> 0x00FF7F;
                case entranceName -> 0x6495ED;
                case itemID -> getHexFromFlag(part.flags);
                default -> 0xFFFFFF;
            };
        }
        Color color = part.color.color;
        return ColorHelper.ARGBToHex(255, color.getRed(), color.getBlue(), color.getGreen());
    }

    private static int getHexFromFlag(int flags) {
        if (flagCheck(flags, NetworkItem.ADVANCEMENT)) {
            return 0xAF99EF;
        } else if (flagCheck(flags, NetworkItem.TRAP)) {
            return 0xE9786B;
        }
        return 0x6D8BE8;
    }

    private static boolean flagCheck(int flags, int networkItem) {
        return (flags & networkItem) == networkItem;
    }
}
