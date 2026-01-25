package net.deadlydiamond98.archipelago.client.screens;

import net.deadlydiamond98.archipelago.util.tracker.TrackerScreenUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class LiteratureTrapScreen extends Screen {

    private static final List<String> LITERATURE = List.of(
            "A copypasta is a block of text copied and pasted to the Internet and social media. Copypasta containing controversial ideas or lengthy rants are often posted for humorous purposes, to provoke reactions from those unaware that the posted text is an Internet meme. \n\nThe term copypasta is derived from the computer interface term \"copy and paste\", the act of selecting a piece of text and copying it elsewhere. Usage of the word can be traced to an anonymous 4chan thread from 2006. Merriam-Webster recorded it as appearing on Usenet and Urban Dictionary for the first time that year.",
            "If music be the food of love, play on;\nGive me excess of it, that, surfeiting,\nThe appetite may sicken, and so die.\nThat strain again! it had a dying fall:\nO, it came o'er my ear like the sweet sound,\nThat breathes upon a bank of violets,\nStealing and giving odour! Enough; no more:\n'Tis not so sweet now as it was before.\nO spirit of love! how quick and fresh art thou,\nThat, notwithstanding thy capacity\nReceiveth as the sea, nought enters there,\nOf what validity and pitch soe'er,\nBut falls into abatement and low price,\nEven in a minute: so full of shapes is fancy\nThat it alone is high fantastical.\n\n - William Shakespeare",
            "I don’t like work–no man does–but I like what is in the work–the chance to find yourself. Your own reality–for yourself not for others–what no other man can ever know. They can only see the mere show, and never can tell what it really means.\n\n - Joseph Conrad",
            "No man can reveal to you aught but that which already lies half asleep in the dawning of your knowledge. The teacher who walks in the shadow of the temple, among his followers, gives not of his wisdom but rather of his faith and his lovingness. If he is indeed wise he does not bid you enter the house of his wisdom, but rather leads you to the threshold of your own mind.\n\n - Khalil Gibran",
            "Believing everything found online without scrutiny is dangerous because the internet mixes accurate information with errors, bias, and deliberate deception. Evaluate claims deliberately; do not treat online content as automatically trustworthy.\n\n - Abraham Lincoln",
            "'They're trying to kill me,' Yossarian told him calmly. 'No one's trying to kill you,' Clevinger cried. 'Then why are they shooting at me?' Yossarian asked. 'They're shooting at everyone,' Clevinger answered. 'They're trying to kill everyone.' And what difference does that make? \n\n - Heller",
            "However mean your life is, meet it and live it. Do not shun it and call it hard names. It is not so bad as you are. It looks poorest when you are richest. The fault-finder will find faults even in paradise. Love your life, poor as it is. You may perhaps have some pleasant, thrilling, glorious hours, even in a poorhouse. The setting sun is reflected from the windows of the almshouse as brightly as from the rich man's abode; the snow melts before its door as early in the spring. I do not see but a quiet mind may live as contentedly there, and have as cheering thoughts, as in a palace. \n\n - Thoreau",
            "Man is the only creature that consumes without producing. He does not give milk, he does not lay eggs, he is too weak to pull the plough, he cannot run fast enough to catch rabbits. Yet he is lord of all the animals. He sets them to work, he gives back to them the bare minimum that will prevent them from starving, and the rest he keeps for himself.\n\n - George Orwell",
            "That was as rough a thing as I ever heard tell of happening to a boy. And I'm mighty proud to learn how my boy stood up to it. You couldn't ask any more of a grown man... It's not a thing you can forget. I don't guess it's a thing you ought to forget. What I mean is, things like that happen. They may seem mighty cruel and unfair, but that's how life is part of the time. But that isn't the only way life is. A part of the time, it's mighty good. And a man can't afford to waste all the good part, worrying about the bad parts. That makes it all bad.\n\n -  Fred Gipson",
            "The koala (Phascolarctos cinereus), sometimes inaccurately called the koala bear, is an arboreal herbivorous marsupial native to Australia. It is the only extant representative of the family Phascolarctidae. Its closest living relatives are the wombats. The koala is found in coastal areas of the continent's eastern and southern regions, inhabiting Queensland, New South Wales, Victoria, and South Australia. It is easily recognisable by its stout, tailless body and large head with round, fluffy ears and large, dark nose. The koala has a body length of 60–85 cm (24–33 in) and weighs 4–15 kg (8.8–33.1 lb). Its fur colour ranges from silver grey to chocolate brown. Koalas from the northern populations are typically smaller and lighter in colour than their counterparts further south. These populations are possibly separate subspecies, but not all researchers accept this.",
            "What do you mean less than nothing? I don't think there is any such thing as less than nothing. Nothing is absolutely the limit of nothingness. It's the lowest you can go. It's the end of the line. How can something be less than nothing? If there were something that was less than nothing, then nothing would not be nothing, it would be something - even though it's just a very little bit of something. But if nothing is nothing, then nothing has nothing that is less than it is.\n\n - E.B. White",
            "\"You are the biggest fool of a boy I've ever known,\" Mott said. Then his tone softened. \"But you will serve Carthya well.\"\n\"I wish I felt ready to do this,\" I said. \"The closer we come to the moment, the more I see every defect in my character that caused my parents to send me away in the first place.\"\n\"From all I'm told, the prince they sent away was selfish, mischievous, and destructive. The king who returns is courageous, noble, and strong.\"\n\"And a fool,\" I added\nMott chuckled. \"You are that too.\"\n\n - Jennifer A. Nielsen",
            "Mario: \"Nice of the princess to invite us over for a picnic, eh, Luigi?\n\nLuigi: \"I hope she made lots of spaghetti!\n\nMario: \"Luigi! Look! *picks up note found on gate* It's from Bowser. *begins reading note*\n\nDear, Pesky Plumbers\n\nThe Koopalings and I have taken over the Mushroom Kingdom. The princess is now a permanent guest at one of my 7 Koopa Hotels. I dare you to find her if you can!\n\nMario: \"We got to find the princess!\n\nLuigi: *points at player* \"And YOU got to help us!\n\nMario: *talking to player* \"If you need instructions on how to get through the Hotels, please check out the Enclosed Instruction Book.",
            "The Carboniferous is a geologic period and system of the Paleozoic era that spans 60 million years, from the end of the Devonian Period 358.86 Ma (million years ago) to the beginning of the Permian Period, 298.9 Ma. It is the fifth period of the Phanerozoic eon. In North America, the Carboniferous is often treated as two separate geological periods, the earlier Mississippian and the later Pennsylvanian. The name Carboniferous means \"coal-bearing\", from the Latin carbō (\"coal\") and ferō (\"bear, carry\"), and refers to the many coal beds formed globally during that time. The first of the modern \"system\" names, it was coined by geologists William Conybeare and William Phillips in 1822, based on a study of the British rock succession.",
            "Calculus is the mathematical study of continuous change, in the same way that geometry is the study of shape and algebra is the study of generalizations of arithmetic operations. Originally called infinitesimal calculus or the calculus of infinitesimals, it has two major branches, differential calculus and integral calculus. Differential calculus analyses instantaneous rates of change and the slopes of curves; integral calculus analyses accumulation of quantities and areas under or between curves. These two branches are related to each other by the fundamental theorem of calculus. Calculus uses convergence of infinite sequences and infinite series to a well-defined mathematical limit.",
            "Mercury poisoning is a type of metal poisoning due to exposure to mercury. Symptoms depend upon the type, dose, method, and duration of exposure. They may include muscle weakness, poor coordination, numbness in the hands and feet, skin rashes, anxiety, memory problems, trouble speaking, trouble hearing, or trouble seeing. High-level exposure to methylmercury is known as Minamata disease. Methylmercury exposure in children may result in acrodynia (pink disease) in which the skin becomes pink and peels. Long-term complications may include kidney problems and decreased intelligence. The effects of long-term low-dose exposure to methylmercury are unclear. Forms of mercury exposure include metal, vapor, salt, and organic compound. Most exposure is from eating fish, amalgam-based dental fillings, or exposure at a workplace.",
            "According to all known laws of aviation, there is no way a bee should be able to fly.\nIts wings are too small to get its fat little body off the ground.\nThe bee, of course, flies anyway because bees don't care what humans think is impossible.\nYellow, black. Yellow, black. Yellow, black. Yellow, black.\nOoh, black and yellow!\nLet's shake it up a little.\nBarry! Breakfast is ready!\nComing!\nHang on a second.\nHello?\nBarry?\nAdam?\nCan you believe this is happening?\nI can't.\nI'll pick you up.\nLooking sharp.\nUse the stairs, Your father paid good money for those.\nSorry. I'm excited.\nHere's the graduate.\nWe're very proud of you, son.\nA perfect report card, all B's.\n",
            "It would be difficult for me to tell you what the moral of this story is. For some stories, it's easy. The moral of 'The Three Bears,' for instance, is 'Never break into someone else's house.' The moral of 'Snow White' is 'Never eat apples.' The moral of World War I is 'Never assassinate Archduke Ferdinand.\n\n - Snicket",
            "Say! I like green eggs and ham!\nI do! I like them, Sam-I-am!\nAnd I would eat them in a boat.\nAnd I would eat them with a goat...\nAnd I will eat them in the rain.\nAnd in the dark.\nAnd on a train.\nAnd in a car. And in a tree.\nThey are so good, so good, you see!\nSo I will eat them in a box.\nAnd I will eat them with a fox.\nAnd I will eat them in a house.\nAnd I will eat them with a mouse.\nAnd I will eat them here and there.\nSay! I will eat them anywhere!\nI do so like green eggs and ham!\nThank you! Thank you, Sam-I-am! \n\n - Dr. Seuss",
            "In the light of the moon a little egg lay on a leaf.\nOne Sunday morning the warm sun cam up...\nand POP, out of the egg came a tiny, very hungry caterpillar.\nHe started looking for some food.\nOn Monday he ate through one apple. But he was still hungry.\nOn Tuesday he ate through two pears, but he was still hungry.\nOn Wednesday he ate through three plums, but he was still hungry.\nOn Thursday he ate through four strawberries, but he was still hungry.\nOn Friday he ate through five oranges, but he was still hungry.\nOn Saturday he ate through one piece of chocolate cake, One ice-cream cone, One pickle, One slice of Swiss cheese, One slice of salami, One lollipop, One piece of cherry pie, One sausage, One cupcake, and one slice of watermelon.\nThat night he had a stomach ache!\n\n - Eric Carle"
    );

    public static final Identifier BOOK_TEXTURE = new Identifier("textures/gui/book.png");
    protected int backgroundWidth = 146;
    protected int backgroundHeight = 180;
    private int guiX, guiY;
    protected final String text;

    public LiteratureTrapScreen(Text title, PlayerEntity player) {
        super(title);
        this.text = LITERATURE.get(player.getRandom().nextInt(LITERATURE.size()));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        this.guiX = (this.width - this.backgroundWidth) / 2;
        this.guiY = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(BOOK_TEXTURE, this.guiX, this.guiY, 20, 1, this.backgroundWidth, this.backgroundHeight);
        TrackerScreenUtil.drawWithScale(context, this.guiX + 15, this.guiY + 15, 0.6f, (x, y) -> {
            context.drawTextWrapped(
                    this.textRenderer, StringVisitable.plain(text),
                    x, y, 195, 0x000000);
        });

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
