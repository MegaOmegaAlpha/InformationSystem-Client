package controller;

import model.Track;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ProxyUITrackList implements UITrackList{
    private Message message;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ProxyUITrackList(String trackfile) {
        try {
            socket = new Socket("localhost", 0066);
            message = new Message();
            message.trackfile = trackfile;
            message.genrefile = "genrefile.xml";
            message.page = 1;
            message.actionType = Message.ActionType.INIT;
            message = sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int currentPage(){
        return 0;
    }

    private Message sendMessage(Message message){
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
    @Override
    public int size() {
        message = new Message();
        message.actionType = Message.ActionType.SIZE;
        message = sendMessage(message);
        return message.size;
    }
    @Override
    public List<UITrack> getTracks(int page) {
        message = new Message();
        message.actionType = Message.ActionType.GET;
        message.page = page;
        message = sendMessage(message);
        return message.list;
    }

    @Override
    public List<UITrack> delete(UITrack track) {
        message = new Message();
        message.actionType = Message.ActionType.DELETE;
        message.track = track;
        message = sendMessage(message);
        return message.list;
    }

    @Override
    public void markAsChanged(UITrack track) {
        message = new Message();
        message.actionType = Message.ActionType.EDIT;
        message.track = track;
        sendMessage(message);
    }

    @Override
    public void markAsNew(UITrack track) {
        message = new Message();
        message.actionType = Message.ActionType.FIX_NEW;
        message.track = track;
        sendMessage(message);
    }

    @Override
    public UITrack newTrack() {
        message = new Message();
        message.actionType = Message.ActionType.NEW;
        message = sendMessage(message);
        return message.track;
    }

    @Override
    public void synchronize() {
        message = new Message();
        message.actionType = Message.ActionType.SAVE;
        sendMessage(message);
    }

    @Override
    public List<UITrack> find(String artists, String album, String name, String duration, String genre) {
        return null;
    }

    @Override
    public void setTracks(List<UITrack> tracks) {
        message = new Message();
    }

    @Override
    public List<UITrack> fixedDelete(UITrack track) {
        message = new Message();
        message.actionType = Message.ActionType.DELETE;
        message.track = track;
        message = sendMessage(message);
        return message.list;
    }


}
