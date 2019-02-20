package controller;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    private UITrackList trackList;

    public Message handle(Message m){
        Message response = new Message();

        switch (m.actionID){
            case ID_INIT:
                UITrackListFactory factory = new UITrackListFactory();
                trackList = factory.getUITrackList(m.trackfile, m.genrefile);
                response.actionID = Message.ActionID.ID_INIT;
                if (trackList == null) {
                    response.success = false;
                } else {
                    response.success = true;
                }
                return response;

            case ID_NEW:
                response.track = trackList.newTrack();
                response.actionID = Message.ActionID.ID_NEW;
                response.success = true;
                return response;

            case ID_DELETE:
                trackList.delete(m.track);
                response.actionID = Message.ActionID.ID_DELETE;
                response.success = true;
                return response;

            case ID_EDIT:
                trackList.markAsChanged(m.track);
                response.actionID = Message.ActionID.ID_EDIT;
                response.success = true;
                return response;

            case ID_SAVE:
                trackList.synchronize();
                response.actionID = Message.ActionID.ID_SAVE;
                response.success = true;
                return response;

            case ID_GET:
                List<UITrack> list = trackList.getTracks();
                int page = m.page;
                response.actionID = Message.ActionID.ID_GET;
                int pages = list.size()/10 + ((list.size()%10 == 0)? 0 : 1);
                if ( pages < page || page < 0)
                    response.success = false;
                else {
                    List<UITrack> shortList = new ArrayList<>();
                    if ( pages == page )
                        response.end = true;
                    for ( int i = page - 1; i < page + 10; i++ ) {
                        shortList.add(list.get(i));
                    }
                    response.list = shortList;
                    response.success = true;
                }
                return response;
        }

        return null;
    }
}
