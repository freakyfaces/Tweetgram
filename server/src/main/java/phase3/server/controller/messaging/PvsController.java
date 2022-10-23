package phase3.server.controller.messaging;

import com.sun.jdi.connect.spi.TransportService;
import phase3.server.dataBase.Load;
import phase3.shared.model.User;
import phase3.shared.model.messaging.PvChatOverview;
import phase3.shared.model.messaging.pvChat;
import phase3.shared.response.messaging.GetPvsResponse;

import java.io.IOException;
import java.util.LinkedList;

public class PvsController {
    public static GetPvsResponse getPvs(String authToken) throws IOException {
        User user = User.getUserByAuthToken(authToken);
        LinkedList<PvChatOverview> pvChats = new LinkedList<>();

        for (String pv : user.pvs) {
            String shown = user.username + " : .... ";
            String name;
            String unseen = "";
            byte[] profile;
            pvChat pvChat = Load.loadPvChat(pv);
            if (pvChat.messages.size()>0){
            if (!pvChat.messages.getLast().giver.equals(user.Id)){
                shown = User.id2username(pvChat.messages.getLast().id) + " : " + pvChat.messages.getLast().text;
            }}
            if (user.Id.equals(pvChat.id1)){
                name = User.id2username(pvChat.id2);
                if (pvChat.unreadid1 > 0){
                    profile = Load.picToByte(pvChat.id1);
                    unseen = pvChat.unreadid1 + "";
                    pvChats.addFirst(new PvChatOverview(shown, name, unseen,pvChat.id, profile));
                }
                else {
                    profile = Load.picToByte(pvChat.id1);
                    pvChats.addLast(new PvChatOverview(shown, name, unseen, pvChat.id, profile));
                }
            }
            else {
                name = User.id2username(pvChat.id1);
                if (pvChat.unreadid2 > 0){
                    unseen = pvChat.unreadid2 + "";
                    profile = Load.picToByte(pvChat.id2);
                    pvChats.addFirst(new PvChatOverview(shown, name, unseen,pvChat.id, profile));
                }
                else {
                    profile = Load.picToByte(pvChat.id2);
                    pvChats.addLast(new PvChatOverview(shown, name, unseen,pvChat.id, profile));
                }
            }
        }
        return new GetPvsResponse(pvChats);
    }

}
