package net.deadlydiamond98.archipelago.mixin.client;

import net.deadlydiamond98.archipelago.archipelago.ArchipelagoServerConnector;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CreateWorldScreen.GameTab.class)
public class GameTabMixin {

    @Unique private TextFieldWidget archipelagoServerField;
    @Unique private TextFieldWidget archipelagoPlayerField;
    @Unique private TextFieldWidget archipelagoPasswordField;

    @Inject(method = "<init>", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void archipelago$init(CreateWorldScreen createWorldScreen, CallbackInfo ci, GridWidget.Adder adder, Positioner positioner, GridWidget.Adder adder2) {
        GridWidget.Adder adderAP = new GridWidget().setRowSpacing(4).createAdder(2);
        adder2.add(adderAP.getGridWidget());

        adderAP.add(new TextWidget(Text.translatable("selectWorld.enterArchipelagoServer"), createWorldScreen.textRenderer), adderAP.copyPositioner().marginLeft(4).alignVerticalCenter());
        this.archipelagoServerField = adderAP.add(new TextFieldWidget(createWorldScreen.textRenderer,
                0, 0, 130, 18, Text.translatable("selectWorld.enterArchipelagoServer")),
                adderAP.copyPositioner().margin(4, 1)
        );
        ArchipelagoServerConnector.archipelagoServer = ArchipelagoServerConnector.lastConnectedServer;
        this.archipelagoServerField.setText(ArchipelagoServerConnector.lastConnectedServer);
        this.archipelagoServerField.setChangedListener(s -> ArchipelagoServerConnector.archipelagoServer = s);

        adderAP.add(new TextWidget(Text.translatable("selectWorld.enterPlayerName"), createWorldScreen.textRenderer), adderAP.copyPositioner().marginLeft(4).alignVerticalCenter());
        this.archipelagoPlayerField = adderAP.add(new TextFieldWidget(createWorldScreen.textRenderer,
                        0, 0, 130, 18, Text.translatable("selectWorld.enterPlayerName")),
                adderAP.copyPositioner().margin(4, 1)
        );
        ArchipelagoServerConnector.archipelagoPlayer = ArchipelagoServerConnector.lastConnectedPlayer;
        this.archipelagoPlayerField.setText(ArchipelagoServerConnector.lastConnectedPlayer);
        this.archipelagoPlayerField.setChangedListener(s -> ArchipelagoServerConnector.archipelagoPlayer = s);



        adderAP.add(new TextWidget(Text.translatable("selectWorld.enterServerPassword"), createWorldScreen.textRenderer), adderAP.copyPositioner().marginLeft(4).alignVerticalCenter());
        this.archipelagoPasswordField = adderAP.add(new TextFieldWidget(createWorldScreen.textRenderer,
                        0, 0, 130, 18, Text.translatable("selectWorld.enterServerPassword")),
                adderAP.copyPositioner().margin(4, 1)
        );
        this.archipelagoPasswordField.setChangedListener(s -> ArchipelagoServerConnector.archipelagoPassword = s);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void archipelago$tick(CallbackInfo ci) {
        this.archipelagoServerField.tick();
        this.archipelagoPlayerField.tick();
        this.archipelagoPasswordField.tick();
    }
}
