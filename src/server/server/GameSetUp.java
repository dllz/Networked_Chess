package server.server;

import general.models.Player;
import server.processing.GameHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Daniel on 2016/11/02.
 */
public class GameSetUp
{
    static ArrayList<Player> client = new ArrayList<>();
    static ArrayList<GameHandler> liveGames = new ArrayList<>();


    public static boolean addPlayer(Socket s, String yName, String oppName) {
        // TODO Auto-generated method stub
        boolean found = false;
        for(int i = 0; i < client.size(); i++){

            if(client.get(i).getOppName().equals(yName)){
                try {
                    liveGames.add(new GameHandler(client.get(i).getSocket(), s));
                    liveGames.get(liveGames.size()-1).run();
                    client.remove(i);
                    System.out.println("Players Matched");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!found){
            client.add(new Player(s, yName, oppName));
            return true;
        }else{
            return false;
        }
    }


}
