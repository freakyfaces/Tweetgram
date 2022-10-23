package phase3.shared.model.messaging;

public class PvChatOverview {
    public String shown;
    public String name;
    public String unseen;
    public String pvId;
    public byte[] profile;

    public PvChatOverview(String shown, String name, String unseen, String pvId, byte[] profile) {
        this.shown = shown;
        this.name = name;
        this.unseen = unseen;
        this.pvId = pvId;
        this.profile = profile;
    }
}
